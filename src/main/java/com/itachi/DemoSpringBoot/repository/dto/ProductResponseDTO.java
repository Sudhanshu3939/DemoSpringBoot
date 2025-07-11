package com.itachi.DemoSpringBoot.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {

    private int id;
    private String name;
    private String description;
    private double price;
    private boolean availability;
}
