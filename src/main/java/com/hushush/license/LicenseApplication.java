package com.hushush.license;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class LicenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenseApplication.class, args);
	}

	// 다국어 서비스를 위한 로케일 설정
	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.KOREAN); // 한국을 기본 로케일로 설정 
		return localeResolver;
	} // 메세지 조회 시 로케일이 설정되지 않았다면, 기본 로케일을 사용

	// 다국어 서비스를 위한 메세지소스 설정
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true); // 메세지가 발견되지 않아도 에러를 던지지 않고 메시지 코드를 반환
		messageSource.setBasenames("messages"); // 언어 프로퍼티 파일의 기본 이름 설정
		messageSource.setDefaultEncoding("UTF-8"); // 기본 인코딩 설정(한글 사용 위함)
		return messageSource;
	} // 메세지 소스 파일의 기본 이름은 message가 되며 한국일 경우 message_ko.properties라는 대응 파일을 가지게 될 것이며,
	  // 대응 메세지가 없을 경우 메세지 소스는 message.properties라는 디폴트 메시지 파일을 참조한다.

	




}
