# Lemon Android Build System Template

- 의존성 관리 및 플러그인 모듈 템플릿
- 멀티 모듈 기반 프로젝트 구성 및 관리를 효과적으로 수행할 수 있습니다.
- ```Kotlin DSL``` 기반 ```build logic``` 적용
- ```Version catalog``` 통한 의존성 관리
- ```Compose``` 기반 기본 플러그인 구성
- ```Kotlin 2.0.0``` 사용


#### 사용방법
-  일반 모듈 수준 ```build.gradle.kts```의 ```plugins``` 블록으로 이동하여 ```alias(libs.plguins.플러그인 이름)``` 형식으로 플러그인을 추가합니다.
-  ```Android```에서 기본적으로 사용되는  ```Plugin``` 은 구현이 되어있습니다. 사용 목적에 맞도록 적절하게 수정하세요.
-  ```app``` 모듈 수준의 ```build.gradle.kts```에 플러그인을 정의하는 방법이 작성되어 있습니다.
-  플러그인을 추가하였을 경우, 플러그인에 정의된 의존성 및 빌드 설정 정보들은 즉시 적용이 됩니다.
-  추가적인 플러그인을 적용하고 싶을경우, 하단 ```새로운 플러그인 추가 방법``` 섹션을 참고해주세요.


#### 주의사항
- ```AndroidApplicationPlugin```과 ```AndroidLibraryPlugin```은 동시에 사용할 수 없습니다. app 모듈의 경우 ```AndroidApplicationPlugin``` 을 사용하고, 그 외 모듈들은 ```AndroidLibraryPlugin```를 사용하세요.
- ```Compose``` 를 사용할 경우, ```Application``` 과 ```Library```에 따른 ```Compose``` 플러그인을 적용해주세요 (ex ```Application``` 의 경우 ```AndroidApplicationComposePlugin``` 사용)

#### 설정
- 모든 기본 설정 정보들은 템플릿 생성 기준```io.lemon.android.buildSystem.Config```에 존재합니다.
- ```Config```의 정보들을 수정하여 빌드 정보를 변경할 수 있습니다.
- **```Config```의 ```APPLICATION_ID```는 반드시 변경해주세요.**
- ```app``` 모듈의 패키지 경로 또한 본인의 설정에 적합하도록 수정해주세요.
- ```buildSystem``` 패키지 경로를 수정할 경우, ```build.gradle.kts``` 내 plugins 블록의 implementationClass 경로 또한 수정해주어야 합니다.

- ```BuildFlavor```의 기본값은 dev, qa, release 입니다. ```Config``` 파일에서 사용 목적에 맞도록 변경할 수 있습니다.
- 스토어 배포 목적 또는 ```KeyStore``` 기반 서명정보를 사용하고 싶을 경우 ```AndroidApplicationPlugin``` 클래스의 주석처리된 부분을 제거한 뒤 형식에 따른 적절한 값을 추가해주세요.


#### 새로운 플러그인 추가 방법
1. 사용하고자 하는 의존성을 ```version catalog```에 정의합니다. 파일 위치 : ```./gradle/libs.versions.toml```
2. plugin 패키지에 ```Plugin<Project>``` 를 구현한 클래스를 추가합니다.
   1. 사용하고자 하는 플러그인은 해당 클래스에서```pluginManager``` 블록을 생성하여 추가합니다.
   2. 사용하고자 하는 의존성은 해당 클래스에서```depdendencies``` 블록을 추가하고 그 안에 정의합니다.
3. ```buildSystem``` 모듈의```build.gradle.kts```로 이동하여 ```plugins``` 블록에 생성한 플러그인을 형식에 맞도록 작성합니다.
4. 다시 ```./gradle/libs.versions.toml``` 의 ```[plugins]``` 부분으로 이동하여 ```build.gradle.kts```에서 정의한 id를 바탕으로  플러그인을 추가 작성합니다.
5. 일반 모듈 수준 ```build.gradle.kts```의 ```plugins``` 블록으로 이동하여 ```alias(libs.plguins.플러그인 이름)``` 형식으로 추가합니다. 이때 구분자 '-' 는 '.'로 치환하여 작성합니다.

#### CI (Git Action)
- ```./github/workflows/``` 에 기본 workflow가 작성되어 있습니다.
- ```Build type```에 따른 workflow 추가 구성을 하여 확장하실 수 있습니다.
- PR을 요청할 때에 대한 템플릿을 변경하고 싶다면, ```./github/pull_request_template.md``` 파일을 변경하세요.