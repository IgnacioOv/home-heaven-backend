package com.homeheaven.backend.entity;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Category {
    private int id;
    private String name;
}
