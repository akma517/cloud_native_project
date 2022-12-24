package com.hsuhush.license.model;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class License extends RepresentationModel<License>{ // HATEOAS를 위한 확장 추가
    
    private int id;
    private String licenseId;
    private String description;
    private String organizationId;
    private String productName;
    private String licenseType;
}
