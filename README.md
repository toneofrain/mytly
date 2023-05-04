# Mytly

## ERD
https://www.erdcloud.com/d/z2BSaLEoubwegZTxz

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
