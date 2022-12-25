package com.hushush.license.model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity // JPA 클래스라고 지정
@Table(name="licenses") // 데이터베이스 테이블에 맵핑
public class License extends RepresentationModel<License>{ // HATEOAS를 위한 확장 추가
    
    @Id // 기본키(primary key)로 지정
    @Column(name="license_id", nullable=false)
    private String licenseId;    
    private String description;
    @Column(name="organization_id", nullable=false)
    private String organizationId;
    @Column(name="product_name", nullable=false)
    private String productName;
    @Column(name="license_type", nullable=false)
    private String licenseType;
    @Column(name="comment")
    private String comment;

    public License withComment(String comment) {
        this.setComment(comment);
        return this;
    }
}
