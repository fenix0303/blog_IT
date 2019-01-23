package ua.logos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="seller")
public class SellerEntity extends  BaseEntity {

    @Column(name="first_name", nullable = false)
    private String firstName;
    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="phone_number", nullable = false)
    private String phoneNumber;

}
