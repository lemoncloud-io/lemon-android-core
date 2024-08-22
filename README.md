# Lemon Android Core

안드로이드에서 공통적으로 사용될 모듈 저장소

##### 초기화
- 최초로 프로젝트를 다운받은 후 `./init_lint_settings.sh` 를 실행해주세요. commit changes 에 대한 lint를 자동적으로 수행합니다.

## Module

| module                                      | description               |
|---------------------------------------------|---------------------------|
| lemon-core-ui:architecture                  | mvi 기반 architecture 인터페이스 |
| lemon-core-android:component(Not implement) | android component 관련 유틸리티 |


### UI-Architecture
안드로이드 UI 구조를 효과적으로 빌딩하기 위한 아키텍처 라이브러리. MVI 기반의 아키텍처로 State Event Effect를 제어하여 사용자와 UI간의 상태 및 이벤트 흐름과 사이에 발생하는 이펙트를 효과적으로 처리할 수 있습니다.


### Android-Component
안드로이드 컴포넌트 제어 라이브러리 안드로이드 컴포넌트 초기화, 설정 및 컴포넌트간의 통신과 같은 작업을 수행합니다.
