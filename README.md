# 클라우드 네이티브 앱 만들기

스프링부트를 사용해서 클라우드 네이티브 앱을 구축해보기

# 사용 툴
* java 17
* springboot 3.0.2.SNAPSHOT
* VSC
* MAVEN 3.8.6

# E.T.C
* 스프링부트 3.x.x 는 스프링 6.x.x에 대응한다.
* 스프링 진영에서 cloud 생태계에 대응하기 위한 버전 빌드업으로 여겨진다.
* 메이저 버전이 올라가면서 java 버전도 17로 변경되었다.

## 스프링 5.x.x -> 6.x.x 변경점
* Java 17기반으로 변경
* 일부 Java EE API 지원 종료
* XML이 점차적으로 Spring에서는 사라지게 될 것
* RPC 지원 종료
* 새로운 AOT 엔진 도입 (밑에 설명 추가)
* @Inject 같은 JSR에서 지원하던 어노테이션들이 jakarta.annotation 패키지의 어노테이션으로 변경
* HttpMethod가 enum에서 class로 변경
* Jakarta EE 9+로의 마이그레이션으로 인한 변경
  * Hibernate ORM 5.6.x 버전부터 hibernate-core-jakarta 사용
  * javax.persistence에서 jakarta.persistence로 변경
  * Tomcat 10, Jetty 11, Undertow 2.2.14 (undertow-servlet-jakarta도 포함)으로 업그레이드 필요
  * javax.servlet에서 jakarta.servlet으로 변경 필요 (import)
* Commons FileUpload, Tiles, FreeMarker JSP support 같은 서블릿 기반 기능이 지원 종료됩니다.
  * multipart file 업로드 혹은 FreeMarker template view는 StandardServletMultipartResolver 사용을 권장
  * 이외에는 Rest 기반 웹 아키텍처 사용
* Spring MVC와 Spring WebFlux에서 더 이상 type 레벨에서의 @RequestMapping을 자동 탐색하지 않음
  * interface의 경우에는 @RequestMapping을 붙여도 더 이상 탐색되지 않음
  * 따라서 Class에 붙이거나 interface에도 사용하고 싶으면 @Controller도 붙여야 함
  * spring-cloud-openfeign에서도 이것 때문에 interface레벨 @RequestMapping 지원 종료(Git Issue)
* URL에서 마지막으로 나오는 / 매칭해주는 trailing slash matching configuration 기본적으로 지원하지 않음 (옵션 추가 시 사용 가능)

## 스프링부트 2.x.x -> 3.x.x 변경점
* 최소 요구사항 변경 (M4 기준)
  * Gradle 7.5
  * Groovy 4.0
  * Jakarta EE 9
  * Java 17
  * Kotlin 1.6
  * Hibernate 6.1
  * Spring Framework 6
* AOT maven, gradle 플러그인 제공
* native 지원 기능 확대


## spring AOT(Ahead Of Time)이란?
* 빌드 시 스프링 애플리케이션을 분석하고 최적화하는 도구
* GraalVM Native Configuration이 필요로 하는 reflection configuration을 생성
* eflection configuration은 Spring native 실행 파일로 컴파일 하는데 사용되고 이후에 애플리케이션의 시작 시간과 메모리 사용량을 낮춤

## AOT 적용 효과
* 런타임 시 Spring 인프라를 적게 사용
* 런타임 시 검증 조건 수 감소
* 리플렉션을 줄이고 프로그래밍적 Bean 등록 방식 사용
