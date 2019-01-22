package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domain.CommentDTO;
import ua.logos.domain.PostDTO;
import ua.logos.domain.TagDTO;
import ua.logos.domain.UserDTO;
import ua.logos.entity.CommentEntity;
import ua.logos.entity.PostEntity;
import ua.logos.entity.TagEntity;
import ua.logos.entity.UserEntity;
import ua.logos.exceptions.NoExistsException;
import ua.logos.repository.CommentRepository;
import ua.logos.repository.PostRepository;
import ua.logos.repository.UserRepository;
import ua.logos.service.CommentService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public CommentDTO saveComment(CommentDTO commentDTO) {
        for (PostDTO post : commentDTO.getPost()
        ) {
            Long idPost = post.getId();
            boolean exists = postRepository.existsById(idPost);
            boolean existsUser = userRepository.existsById(post.getUser().getId());
            if (!existsUser || !exists) {
                throw new NoExistsException("Such user or post is not exists");
            }
        }
        CommentEntity commentEntity = modelMapper.map(commentDTO, CommentEntity.class);
        commentRepository.save(commentEntity);
        commentDTO.setId(commentEntity.getId());
        return commentDTO;
    }

    @Override
    public List<CommentDTO> findAll() {
        List<CommentEntity> commentsEntities = commentRepository.findAll();
        List<CommentDTO> commentDTOS = modelMapper.mapAll(commentsEntities, CommentDTO.class);
        return commentDTOS;
    }

    @Override
    public CommentDTO findById(Long id) {
        boolean exists = commentRepository.existsById(id);
        if (!exists) {
            throw new NoExistsException("Comment with such id is not exists");
        }
        CommentEntity commentEntity = commentRepository.findById(id).get();
        CommentDTO commentDTO = modelMapper.map(commentEntity, CommentDTO.class);
        return commentDTO;

    }

}