package com.itachi.DemoSpringBoot.repository.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Min(0)
    private double price;

    @NotNull
    private boolean availability;

    // Getters and Setters
}
