package ua.logos.service;

import ua.logos.domain.TagDTO;

import java.util.List;

public interface TagService {
    TagDTO saveTag(TagDTO tagDTO);

    List<TagDTO> findAllTag();

    TagDTO findById(Long id);

    TagDTO updateTag(Long id, TagDTO tagToUpdate);
}
