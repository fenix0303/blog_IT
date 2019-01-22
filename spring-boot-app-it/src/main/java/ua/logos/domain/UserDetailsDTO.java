package ua.logos.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailsDTO {
    private Long id;
    private String email;
    private String dateOfBirth;
    private String maritalStatus;
    private String hobby;
    private String listOfTechnologies;
    private UserDTO user;

}
