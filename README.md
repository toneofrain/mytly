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



## Architecture

## ERD
<img src=https://user-images.githubusercontent.com/45251314/236216959-f754fc27-c19e-4f77-b4eb-d4a93fabc60f.png width="500"/>

## Java Package
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

## 트러블슈팅 및 회고
[회고](https://velog.io/@saintho/URL%EB%8B%A8%EC%B6%95-00)
