package ua.logos.service;

import ua.logos.domain.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO saveComment(CommentDTO commentDTO);

    List<CommentDTO> findAll();

    CommentDTO findById(Long id);

}
