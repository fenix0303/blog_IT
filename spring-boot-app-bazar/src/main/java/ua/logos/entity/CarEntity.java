package ua.logos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "car")
public class CarEntity extends BaseEntity {
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "volume_of_engine", nullable = false)
    private String volumeOfEngine;
    @Column(name = "price", nullable = false)
    private String price;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private SellerEntity seller;

}
