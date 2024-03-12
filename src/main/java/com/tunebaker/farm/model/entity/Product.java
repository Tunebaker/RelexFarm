package com.tunebaker.farm.model.entity;

import com.tunebaker.farm.model.enums.ActivityStatus;
import com.tunebaker.farm.model.enums.MeasureUnit;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private MeasureUnit measureUnit;
}

