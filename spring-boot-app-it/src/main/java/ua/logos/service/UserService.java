package ua.logos.service;

import ua.logos.domain.UserDTO;
import ua.logos.entity.UserEntity;

import java.util.List;

public interface UserService {


    UserDTO saveUser(UserDTO user);

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO updateUser(Long id, UserDTO userToUpdate);
}
