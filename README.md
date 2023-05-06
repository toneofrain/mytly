# Mytly

Mytly는 BASE62 기반 단축 URL 관리 REST API 서비스입니다.


### 프로젝트 기간
- 2023.04.21 ~

### 환경
- Java 11 (openjdk 11.0.17)
- Spring Boot 2.7.12
- Mysql 8.0.33
- Ubuntu 22.04.1 i5-8250U 8gb ram

### 배포
- ~~https://mytly.saintho.dev (일시중단)~~

---

## 실행

### java 버전
```
java --version
```

<u>java 11 이상</u>

### mysql 버전
```
mysql --version
```

<u>mysql 8</u>

### 빌드
- github에서 fork
- mytly/src/main/resources/application-prod.yml 수정
```
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: ${your_db_url}
        username: {your_db_username}
        password: ${your_db_user_pwd}
      
      host:
        name: {your_hostname}
        path: /api/v1/urls/
```

본인의 db 정보와 hostname에 맞게 수정
- gradle로 빌드
```
    ./gradlew build -x test
```

### jar 실행
- build/libs로 이동
- java -jar 생성된jar파일명.jar

### Swagger 주소
- {your-hostname}/swagger-ui/index.html

---

## 핵심기능 - 단축 URL 관리

### 원본 URL 조회(리다이렉트)
- 단축 URL과 연결된 원본 URL로 리다이렉트
- 만료/삭제된 단축 URL에 대한 요청에 응답하지 않는다.

### 단축 URL 생성
- 입력받은 원본 URL과 만료 옵션에 따른 URL 생성

### 단축 URL 통계 조회
- 해당 단축 URL에 대한 총조회수/리퍼러별 조회수/최근 일주일간 일별 조회수 통계를 제공한다.

### 단축 URL 삭제
- 이후에 이용가능하지 않도록 단축 URL을 삭제한다.
- 삭제처리된 URL에 대한 리다이렉트/통계 조회 요청에 응답하지 않는다.

---

## 아키텍처
### 운영

### ERD
<img src=https://user-images.githubusercontent.com/45251314/236216959-f754fc27-c19e-4f77-b4eb-d4a93fabc60f.png width="500"/>

[스키마](https://github.com/toneofrain/mytly/blob/main/src/main/resources/sql/schema.sql)

### 자바 패키지 구조
```
.
└── dev
    └── saintho
        └── mytly
            ├── MytlyApplication.java
            ├── api
            │   └── v1
            │       └── urls
            │           ├── controller
            │           │   └── UrlController.java
            │           └── dto
            │               ├── command
            │               │   ├── UrlDeleteByShortenedCommand.java
            │               │   └── UrlShortCommand.java
            │               ├── query
            │               │   ├── Referer.java
            │               │   └── UrlRedirectQuery.java
            │               ├── request
            │               │   ├── ExpirationPeriod.java
            │               │   └── UrlPostRequest.java
            │               ├── response
            │               │   ├── UrlPostResponse.java
            │               │   ├── UrlStatisticDaily.java
            │               │   ├── UrlStatisticReferer.java
            │               │   ├── UrlStatisticResponse.java
            │               │   └── UrlStatisticUrlInfo.java
            │               └── result
            │                   └── UrlShortResult.java
            ├── config
            │   ├── JpaConfig.java
            │   ├── OpenApiConfig.java
            │   ├── QueryDSLConfig.java
            │   └── UrlGeneratorConfig.java
            ├── domain
            │   └── entity
            │       ├── DailyEngagement.java
            │       ├── RefererEngagement.java
            │       ├── Url.java
            │       └── UrlStatus.java
            ├── event
            │   ├── dto
            │   │   ├── UrlCreateEvent.java
            │   │   └── UrlRedirectEvent.java
            │   └── listener
            │       └── UrlEventListener.java
            ├── exception
            │   ├── ExceptionType.java
            │   ├── GlobalExceptionAdvice.java
            │   └── MytlyException.java
            ├── generator
            │   ├── source
            │   │   ├── RandomSourceGenerator.java
            │   │   └── SourceGenerator.java
            │   └── url
            │       ├── Base62UrlGenerator.java
            │       ├── EncodingScheme.java
            │       └── UrlGenerator.java
            ├── repository
            │   └── jpa
            │       ├── dailyengagement
            │       │   └── DailyEngagementRepository.java
            │       ├── refererengagement
            │       │   └── RefererEngagementRepository.java
            │       ├── url
            │       │   ├── QueryDslUrlRepository.java
            │       │   ├── QueryDslUrlRepositoryImpl.java
            │       │   └── UrlRepository.java
            │       └── urlstatistic
            │           ├── QueryDSLUrlStatisticRepository.java
            │           └── UrlStatisticRepository.java
            ├── service
            │   ├── DailyEngagementService.java
            │   ├── RefererEngagementService.java
            │   ├── UrlService.java
            │   └── UrlStatisticService.java
            └── validation
                ├── annotation
                │   └── ValidOriginalUrl.java
                └── validator
                    └── OriginalUrlValidator.java
```

## 고민한 것

### 1. BASE62 
- Encoding Scheme은 URL Safe한 BASE62를 선택했습니다
- 단축 URL의 길이는 6~7로 약 3억5천만개의 단축 URL 생성 가능합니다
- 추후 단축 URL의 길이를 확장할 수 있습니다.

### 2. Source
- 단축 URL로 Encoding할 Source를 선택해야 합니다.
- pk값의 경우 단축 URL의 길이가 계속 변하고 Decoding시 pk값이 노출되는 문제가 있다고 생각했습니다.
- 원본 URL의 해시값이나 UUID의 경우 단축 URL의 길이가 길어지고, 일부를 잘라서 인코딩할 경우 충돌의 가능성이 높아집니다.
- 임의의 6~7자리 62진수를 생성후 이를 BASE62로 인코딩하는 것으로 결정했습니다.
- db에 exist 쿼리를 던져 중복 시에 단축 URL을 재생성하는데, 저장된 단축 URL이 많아질수록 충돌의 가능성이 높아져 exist 쿼리의 횟수가 증가하는 문제가 있습니다.

### Soft Delete vs Hard Delete
- 초기엔 단축 URL 재사용이 가능하도록 단축 URL 삭제시 Hard Delete로 구현했습니다.
- 단축 URL 재사용시 연결된 원본 URL이 변해 이로 인한 혼동이나 악용 가능성이 있다고 생각하여 Soft Delete하는 것으로 변경했습니다.
- 삭제처리된 단축 URL에 대한 리다이렉트, 통계 조회 요청에는 응답하지 않습니다.
- 삭제처리된 단축 URL에 연결된 원본 URL 생성 요청시에는 새로 단축 URL을 생성하여 연결합니다.

---

## 추후 작업

### 1. Source
- 저장된 단축 URL이 많아질수록 충돌의 가능성이 높아지는 문제 때문에 미리 Source를 생성해서 관리하는 것으로 변경
- Source와 사용 여부 flag를 저장
- 생성/삭제가 없으므로 NoSQL 사용하고자 함

### 2. Spring REST Docs와 Swagger 연동

### 3. 비동기 처리

### 4. 로깅

### 5. 원본 URL 검증 로직 보강

---

## 트러블슈팅 및 회고

자세한 트러블슈팅과 회고는 [블로그](https://velog.io/@saintho/URL%EB%8B%A8%EC%B6%95-00)에서 보실 수 있습니다.
