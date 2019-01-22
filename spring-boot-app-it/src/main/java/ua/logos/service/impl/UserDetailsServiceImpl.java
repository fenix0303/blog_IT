package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domain.UserDTO;
import ua.logos.domain.UserDetailsDTO;
import ua.logos.entity.UserDetailsEntity;
import ua.logos.entity.UserEntity;
import ua.logos.exceptions.NoExistsException;
import ua.logos.repository.UserDetailsRepository;
import ua.logos.repository.UserRepository;
import ua.logos.service.UserDetailsService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapperUtils modelmapper;

    @Override
    public UserDetailsDTO saveDetails(UserDetailsDTO userDetailsDTO) {
        boolean exists = userRepository.existsById(userDetailsDTO.getUser().getId());
        if (!exists) {
            throw new NoExistsException("User with such id is not exists");
        }

        UserDetailsEntity userDetailsEntity = modelmapper.map(userDetailsDTO, UserDetailsEntity.class);
        userDetailsRepository.save(userDetailsEntity);
        userDetailsEntity.setId(userDetailsEntity.getId());
        return userDetailsDTO;
    }

    @Override
    public List<UserDetailsDTO> findAll() {
        List<UserDetailsEntity> userDetailsEntities = userDetailsRepository.findAll();
        List<UserDetailsDTO> userDetailsDTOS = modelmapper.mapAll(userDetailsEntities, UserDetailsDTO.class);

        return userDetailsDTOS;
    }

    @Override
    public UserDetailsDTO findById(Long id) {
        boolean exists = userDetailsRepository.existsById(id);
        if (!exists) {
            throw new NoExistsException("Userdetails with such id is not exists");
        }
        UserDetailsEntity userDetailsEntity = userDetailsRepository.findById(id).get();
        UserDetailsDTO userDetailsDTO = modelmapper.map(userDetailsEntity, UserDetailsDTO.class);
        return userDetailsDTO;
    }

    @Override
    public UserDetailsDTO updateDetails(Long id, UserDetailsDTO userDetailsToUpdate) {
        boolean exists = userDetailsRepository.existsById(id);
        if (!exists) {
            throw new NoExistsException("Userdetails with such id is not exists");
        }
        UserDetailsEntity userDetailsFromDB = userDetailsRepository.findById(id).get();
        userDetailsFromDB.setHobby(userDetailsToUpdate.getHobby());
        userDetailsFromDB.setMaritalStatus(userDetailsToUpdate.getMaritalStatus());
        userDetailsFromDB.setListOfTechnologies(userDetailsToUpdate.getListOfTechnologies());
        userDetailsFromDB.setDateOfBirth(userDetailsToUpdate.getDateOfBirth());
        userDetailsFromDB.setEmail(userDetailsToUpdate.getEmail());

        UserDTO userDTO = userDetailsToUpdate.getUser();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setNickname(userDTO.getNickname());
        userEntity.setDateOfCreate(userDTO.getDateOfCreate());

        userDetailsFromDB.setUser(userEntity);
        UserDetailsDTO userDetailsDTO = modelmapper.map(userDetailsFromDB, UserDetailsDTO.class);
        userDetailsRepository.save(userDetailsFromDB);
        userDetailsDTO.setId(userDetailsFromDB.getId());
        return userDetailsDTO;
    }



}
