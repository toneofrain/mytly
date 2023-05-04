# Mytly

Mytly는 BASE62 기반 단축 URL 관리 서비스입니다.


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
### 운영배포

### ERD
<img src=https://user-images.githubusercontent.com/45251314/236216959-f754fc27-c19e-4f77-b4eb-d4a93fabc60f.png width="500"/>

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

## 

## 트러블슈팅 및 회고
[회고](https://velog.io/@saintho/URL%EB%8B%A8%EC%B6%95-00)
