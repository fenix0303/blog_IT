package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domain.UserDTO;
import ua.logos.entity.UserEntity;
import ua.logos.exceptions.AlreadyExistsException;
import ua.logos.exceptions.NoExistsException;
import ua.logos.repository.UserRepository;
import ua.logos.service.UserService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public UserDTO saveUser(UserDTO user) {
        boolean exists = userRepository.existsByNicknameIgnoreCase(user.getNickname());
        if (exists) {
           throw new AlreadyExistsException("This user has already exists");
        }
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userRepository.save(userEntity);

        user.setId(userEntity.getId());
        return user;
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDTO> userDTOS = modelMapper.mapAll(users, UserDTO.class);
        return userDTOS;
    }

    @Override
    public UserDTO findById(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new NoExistsException("User with such id is not exists");
        }
        UserEntity user = userRepository.findById(id).get();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userToUpdate) {
        UserEntity userFromBD = userRepository.findById(id).get();
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new NoExistsException("User with such id is not exists");
        }
        boolean existsNickName = userRepository.existsByNicknameIgnoreCase(userToUpdate.getNickname());
        if (existsNickName && !userToUpdate.getNickname().equals(userFromBD.getNickname())) {
           throw new AlreadyExistsException("Such nickname has already used");
        }

        userFromBD.setFirstName(userToUpdate.getFirstName());
        userFromBD.setLastName(userToUpdate.getLastName());
        userFromBD.setNickname(userToUpdate.getNickname());
        userFromBD.setDateOfCreate(userToUpdate.getDateOfCreate());
        UserDTO userDTO = modelMapper.map(userFromBD, UserDTO.class);
        userRepository.save(userFromBD);
        userDTO.setId(userFromBD.getId());
        return userDTO;
    }



}
