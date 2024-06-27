package com.amazone_clone.backend.amazone_clone_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name="product")
public class Product   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productDetails;
    private Long rating;
    private int price;
    private String stockAvailableDetails;
    private String Color;
    private String image;


}
