package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domain.PostDTO;
import ua.logos.domain.TagDTO;
import ua.logos.domain.UserDTO;
import ua.logos.entity.PostEntity;
import ua.logos.exceptions.NoAccessToEditException;
import ua.logos.exceptions.NoExistsException;
import ua.logos.repository.PostRepository;
import ua.logos.repository.UserRepository;
import ua.logos.service.PostService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public PostDTO savePost(PostDTO post) {

        boolean exists = userRepository.existsById(post.getUser().getId());
        if (!exists) {
           throw new NoExistsException("User with such id is not exists");
        }
        PostEntity postEntity = modelMapper.map(post, PostEntity.class);
        postRepository.save(postEntity);
        post.setId(postEntity.getId());
        return post;
    }

    @Override
    public List<PostDTO> findAll() {
        List<PostEntity> postsEntities = postRepository.findAll();
        List<PostDTO> postDTOS = modelMapper.mapAll(postsEntities, PostDTO.class);
        return postDTOS;
    }

    @Override
    public PostDTO findById(Long id) {
        boolean exists = postRepository.existsById(id);
        if (!exists) {
           throw new NoExistsException("Pos with such id is not exists");
        }
        PostEntity postEntity = postRepository.findById(id).get();
        PostDTO postDTO = modelMapper.map(postEntity, PostDTO.class);


        return postDTO;
    }


    @Override
    public List<PostDTO> findByUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new NoExistsException("User with such id is not exists");
        }
        List<PostEntity> postEntities = postRepository.findAll();
        List<PostEntity> postEntitiesUser = new ArrayList<>();
        for (int i = 0; i < postEntities.size(); i++) {
            if (postEntities.get(i).getUser().getId().equals(userId)) {
                postEntitiesUser.add(postEntities.get(i));
            }
        }

        List<PostDTO> postDTOS = modelMapper.mapAll(postEntitiesUser, PostDTO.class);
        return postDTOS;
    }

    @Override
    public PostDTO updatePost(Long idUser, Long idPost, PostDTO postToUpdate) {
        boolean exists = userRepository.existsById(idUser);
        boolean existsPost = postRepository.existsById(idPost);
        boolean userCreatePost = postToUpdate.getUser().getId().equals(idUser);
        if (!existsPost || !exists ) {
            throw new NoExistsException("Such user or post is not exists");
        }

        if (!userCreatePost) {
            throw new NoAccessToEditException("Such user is not create this post");
        }
        List<PostDTO> postDTOS = findByUser(idUser);
        PostDTO newPostDTO = new PostDTO();
        for (PostDTO postDTO : postDTOS) {
            if (postDTO.getId().equals(idPost)) {
                postDTO.setTitle(postToUpdate.getTitle());
                postDTO.setDescription(postToUpdate.getDescription());
                postDTO.setDateOfCreate(postToUpdate.getDateOfCreate());
                UserDTO userDTOUpdate = postToUpdate.getUser();
                UserDTO userDTO = new UserDTO();
                userDTO.setId(userDTOUpdate.getId());
                userDTO.setFirstName(userDTOUpdate.getFirstName());
                userDTO.setLastName(userDTOUpdate.getLastName());
                userDTO.setNickname(userDTOUpdate.getNickname());
                userDTO.setDateOfCreate(userDTOUpdate.getDateOfCreate());
                postDTO.setUser(userDTOUpdate);
                List<TagDTO> tagDTO = postToUpdate.getTag();
                List<TagDTO> tagDTOUpdate = new ArrayList<>();
                for (TagDTO tag : tagDTO) {
                    TagDTO tagDTO1 = new TagDTO();
                    tagDTO1.setId(tag.getId());
                    tagDTO1.setName(tag.getName());
                    tagDTOUpdate.add(tagDTO1);
                }
                postDTO.setTag(tagDTOUpdate);
                PostEntity postEntity = modelMapper.map(postDTO, PostEntity.class);
                postRepository.save(postEntity);
                postDTO.setId(postEntity.getId());
                newPostDTO = postDTO;

            }
        }
        return newPostDTO;
    }

}
