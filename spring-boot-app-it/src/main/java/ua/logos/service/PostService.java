package ua.logos.service;

import ua.logos.domain.PostDTO;
import ua.logos.domain.UserDTO;
import ua.logos.entity.PostEntity;


import java.util.List;

public interface PostService {

    PostDTO savePost(PostDTO post);

    List<PostDTO> findAll();

    PostDTO findById(Long id);

    List<PostDTO> findByUser(Long userId);

    PostDTO updatePost(Long idUser, Long idPost, PostDTO postToUpdate);
}
