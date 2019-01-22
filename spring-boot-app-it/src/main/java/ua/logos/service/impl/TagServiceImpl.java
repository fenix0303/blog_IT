package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domain.TagDTO;
import ua.logos.entity.TagEntity;
import ua.logos.exceptions.NoExistsException;
import ua.logos.repository.TagRepository;
import ua.logos.service.TagService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public TagDTO saveTag(TagDTO tagDTO) {

        TagEntity tagEntity = modelMapper.map(tagDTO, TagEntity.class);
        tagRepository.save(tagEntity);
        tagDTO.setId(tagEntity.getId());
        return tagDTO;
    }

    @Override
    public List<TagDTO> findAllTag() {
        List<TagEntity> tags = tagRepository.findAll();
        List<TagDTO> tagDTOS = modelMapper.mapAll(tags, TagDTO.class);

        return tagDTOS;
    }

    @Override
    public TagDTO findById(Long id) {
        boolean exists = tagRepository.existsById(id);
        if (!exists) {
            throw new NoExistsException("Such tag is not exists");
        }
        TagEntity tag = tagRepository.findById(id).get();
        TagDTO tagDTO = modelMapper.map(tag, TagDTO.class);
        return tagDTO;
    }

    @Override
    public TagDTO updateTag(Long id, TagDTO tagToUpdate) {
        boolean exists = tagRepository.existsById(id);
        if (!exists) {
            throw new NoExistsException("Such tag is not exists");
        }
        TagEntity tagFromDB = tagRepository.findById(id).get();
        tagFromDB.setName(tagToUpdate.getName());

        TagDTO tagDTO = modelMapper.map(tagFromDB, TagDTO.class);
        tagRepository.save(tagFromDB);
        tagDTO.setId(tagFromDB.getId());
        return tagDTO;
    }


}
