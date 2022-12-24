package com.hushush.license.service;

import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.hushush.license.model.License;


@Service
public class LicenseService {
    @Autowired
    MessageSource messages;

    public License getLicense(String licenseId, String organizationId) {
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("full");
        return license;
    }

    public String createLicense(
        License license, 
        String organizationId,
        Locale locale){
        String responseMessage = null;

        if (license != null){
            license.setOrganizationId(organizationId);
            responseMessage = String.format(
                messages.getMessage("license.create.message", null, locale)
                , license.toString()); // 나라별 메세지를 조회하기 위해 전달된 로케일 설정
        }
        System.out.println(responseMessage);

        return responseMessage;
    }

    public String updateLicense(License license, String organizationId) {
        String responseMessage = null;

        if (license != null){
            license.setOrganizationId(organizationId);
            responseMessage = String.format(
                messages.getMessage("license.create.message", null, null)
                , license.toString()); // 로케일 설정 없이 나라별 메세지 조회
        }

        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId) {
        String responseMessage = null;
        responseMessage = String.format(
            "Deleting license with id %s for the organization %s"
            , licenseId, organizationId);

        return responseMessage;
    }
}
