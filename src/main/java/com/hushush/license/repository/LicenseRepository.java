package com.hushush.license.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hushush.license.model.License;

// jpa 레포지터리 인터페이스라고 스프링 부트에 알린다. 
@Repository // CrudRepository를 확장하면 이 애노테이션은 optional
public interface LicenseRepository extends CrudRepository<License, String>{
    public List<License> findByLicenseId(String licenseId); // 쿼리 메서드를 'select .. from' 쿼리로 구문 분석한다.
    public License findByOrganizationIdAndLicenses(String organizationId, String licenseId);
}