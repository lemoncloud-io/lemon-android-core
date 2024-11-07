# Lemon Android Core
![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/apache/maven.svg?label=License)

Lemon Android Core는 안드로이드에서 공통적으로 사용될 모듈 저장소입니다.


## Libraries

| module            | description        | version                                                                                          |
|-------------------|--------------------|--------------------------------------------------------------------------------------------------|
| core-android      | android 제어 및 유틸리티  | ![Maven Central Version](https://img.shields.io/maven-central/v/io.lemoncloud/core-android)      |
| core-architecture | architecture 인터페이스 | ![Maven Central Version](https://img.shields.io/maven-central/v/io.lemoncloud/core-architecture) |
| core-compose      | compose 유틸리티       | ![Maven Central Version](https://img.shields.io/maven-central/v/io.lemoncloud/core-compose)      |
| core-util         | 유틸리티 및 익스텐션        | ![Maven Central Version](https://img.shields.io/maven-central/v/io.lemoncloud/core-util)         |


### Core-Android

안드로이드 컴포넌트 제어 라이브러리 안드로이드 컴포넌트 초기화, 설정 및 컴포넌트간의 통신과 같은 작업을 수행합니다.
```
implementation("io.lemoncloud:core-android:<version>")
```

### Core-Architecture

안드로이드 프로젝트 구조를 효과적으로 빌딩하기 위한 아키텍처 모듈
```
implementation("io.lemoncloud:core-architecture:<version>")
```

### Core-Compose

안드로이드 `Compose` 에서 사용되는 유틸리티 집합
```
implementation("io.lemoncloud:core-compose:<version>")
```

### Core-Util

유틸리티 및 익스텐션 집합
```
implementation("io.lemoncloud:core-util:<version>")
```



## 가이드
아래부터는 해당 라이브러리 개발 및 배포에 대한 지침을 작성하였습니다.

### 초기화

최초 프로젝트를 다운받은 후 `./init_lint_settings.sh` 를 반드시 실행해주세요. 해당 명령어는 commit changes 에 대한 lint를 자동 수행하도록 설정합니다.
```
sh init_lint_settings.sh
```

### AAR 배포

Lemon Android Core 모듈에서 사용되는 라이브러리를 AAR 패키지로 배포해야 할 상황이 존재할 경우 루트 디렉터리에 존재하는 `assemble_aar.sh` 스크립트 파일을 실행하면 됩니다.

```
sh assemble_aar.sh
```

### Maven-Central 배포

라이브러리를 Maven-Central 저장소에 배포를 하기 위해서는 아래와 같은 절차를 수행해야 합니다.
1. Maven Central 계정 로그인
2. Account 메뉴로 이동 후 Generate User Token 을 통해 토큰 발행
3. gpg key 발급 및 공개키 서버 업로드 수행 (외부 자료 참고)
4. gpg key 에 대한 secret key 생성 (외부 자료 참고)
5. `./gradle.properties` 내 토큰 정보 및 signing 에 대한 public key,secret 기입 (`mavenCentralUsername` , `mavenCentralPassword`, `signing.keyId`, ...)
6. 배포 버전 확인 후 아래 명령어 실행 (project 수준에 존재하는 gradle.kts의 version 필드를 수정하면, 통합적으로 모든 라이브러리 버전 업데이트 가능)
    ```
    ./gradlew publishAllPublicationsToMavenCentralRepository
    ```
7. `VALIDATED` 될 경우 퍼블리싱 진행
8. Maven Central Account 메뉴에서 Revoke User Token 실행
