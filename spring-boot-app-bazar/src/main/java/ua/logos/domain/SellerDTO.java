package ua.logos.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SellerDTO {
    private Long id;
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}
