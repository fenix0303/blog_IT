package ua.logos.service;

import ua.logos.domain.UserDetailsDTO;

import java.util.List;

public interface UserDetailsService {
    UserDetailsDTO saveDetails(UserDetailsDTO userDetailsDTO);

    List<UserDetailsDTO> findAll();

    UserDetailsDTO findById(Long id);

    UserDetailsDTO updateDetails(Long id, UserDetailsDTO userDetailsToUpdate);
}
