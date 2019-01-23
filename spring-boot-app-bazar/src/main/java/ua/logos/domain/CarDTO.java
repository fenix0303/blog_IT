package ua.logos.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarDTO {
    private Long id;
    private String brand;
    private String model;
    private String color;
    private String volumeOfEngine;
    private String price;
    private SellerDTO seller;

}
