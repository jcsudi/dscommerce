package com.jcjrta.dscommerce.dto;

import com.jcjrta.dscommerce.entities.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ProductMinDTO {

    private Long id;
    private String name;
    private Double price;
    private String imgUrl;

    public ProductMinDTO(Long id, String name, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductMinDTO(Product p){
        this.id = p.getId();
        this.name = p.getName();
        this.price = p.getPrice();
        this.imgUrl = p.getImgUrl();

    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Double getPrice() {
        return price;
    }
    public String getImgUrl() {
        return imgUrl;
    }
}
