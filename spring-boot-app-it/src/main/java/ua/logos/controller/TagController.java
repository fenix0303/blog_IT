package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.domain.TagDTO;
import ua.logos.service.TagService;

import java.util.List;

@RestController
@RequestMapping("tags")
public class TagController {
    @Autowired
    private TagService tagService;


    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody TagDTO tagDTO) {
        tagService.saveTag(tagDTO);
        return new ResponseEntity<>("Tag created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getTags() {
        List<TagDTO> tags = tagService.findAllTag();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("{tagId:[0-9]{1,5}}")
    public ResponseEntity<?> getTagById(@PathVariable("tagId") Long id) {
        TagDTO tag = tagService.findById(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PutMapping("{tagId:[0-9]{1,5}}")
    public ResponseEntity<?> updateUser(@PathVariable("tagId") Long id, @RequestBody TagDTO tagToUpdate) {
        TagDTO tag = tagService.updateTag(id, tagToUpdate);
        return new ResponseEntity<>(tag, HttpStatus.OK);

    }

}
