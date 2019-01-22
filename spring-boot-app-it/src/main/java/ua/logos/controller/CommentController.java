package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.domain.CommentDTO;
import ua.logos.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentDTO comment) {
        CommentDTO commentDTO = commentService.saveComment(comment);
        return new ResponseEntity<>("Comment added", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getComments() {
        List<CommentDTO> comments = commentService.findAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("{commentId:[0-9]{1,5}}")
    public ResponseEntity<?> getCommentById(@PathVariable("commentId") Long id) {
        CommentDTO commentDTO = commentService.findById(id);
             return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }
}
