# Mytly

## ERD
![erd](https://user-images.githubusercontent.com/45251314/236216959-f754fc27-c19e-4f77-b4eb-d4a93fabc60f.png)

## Package
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
