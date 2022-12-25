package com.hushush.license.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hushush.license.model.License;
import com.hushush.license.service.LicenseService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; // 이 선언이 없으면 hateoas methodOn 메소드를 사용 못함


@RestController  // 스프링부트에서 이 서비스는 REST기반 서비스이며, 응답과 요청은은 JSON으로 자동으로 직렬/역직렬화 할 것이라고 지정하는 애노테이션
@RequestMapping(value = "v1/organization/{organizationId}/license") // 이 클래스의 모든 HTTP 엔드포인트가 이 URL로 시작하도록 노출(모든 엔드포인트의 최상위 URL)
public class LicenseController {
    @Autowired
    private LicenseService licenseService;

    @GetMapping(value="/{licenseId}")
    public ResponseEntity<License> getLicense(
        @PathVariable("organizationId") String organizationId,
        @PathVariable("licenseId") String licenseId) {

        License license = licenseService.getLicense(licenseId, organizationId);
        
        // API 가이드를 위한 HATEOAS 설정
        // add()는 RepresentationModel 클래스 메서드, linkTo()메서드는 LicenseController 클래스를 검사해서 루트 매핑을 얻고
        // methodOn() 메서드는 대상 메서드에 더미 호출을 수행하여 메서드 매핑을 가져옴
        //컨트롤러 클래스에 대한 링크를 생성하여 응답 내용에 포함시켜 보낸다.
        license.add(
            linkTo( methodOn(LicenseController.class).getLicense(licenseId, license.getLicenseId() )).withSelfRel(),
            linkTo(methodOn(LicenseController.class).createLicense(licenseId, license, null )).withRel("createLicense"),
            linkTo(methodOn(LicenseController.class).updateLicense(organizationId, license)).withRel("updateLicense"),
            linkTo(methodOn(LicenseController.class).deleteLicense(organizationId, license.getLicenseId())).withRel("deleteLicense")
        );


        return ResponseEntity.ok(license); // ResponseEntity는 상태 코드, 헤더, 바디를 포함한 모든 HTTP 응답을 나타냄(body에 License 객체와 200(ok) 상태코드를 반환)
    }

    @PutMapping 
    public ResponseEntity<License> updateLicense(
        @PathVariable("organizationId") String organizationId,
        @RequestBody License request) {

        return ResponseEntity.ok(licenseService.updateLicense(request));
    }

    @PostMapping
    public ResponseEntity<License> createLicense(
        @PathVariable("organizationId") String organizationId,
        @RequestBody License request,
        @RequestHeader(value = "Accept-Language", required=false) Locale locale) {

        return ResponseEntity.ok(licenseService.createLicense(request));
    }

    @DeleteMapping(value="/{licenseId}")
    public ResponseEntity<String> deleteLicense(
        @PathVariable("organizationId") String organizationId,
        @PathVariable("licenseId") String licenseId){

        return ResponseEntity.ok(licenseService.deleteLicense(licenseId));
    }

}
