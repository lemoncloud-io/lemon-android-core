# Lemon Android Core

안드로이드에서 공통적으로 사용될 모듈 저장소

**Authors** : raine@lemoncloud.io

## Module

| module                       | description               |
|------------------------------|---------------------------|
| android-component            | android component 유틸리티    |
| ui-architecture              | mvi 기반 architecture 인터페이스 |
| util-compose (Not Implement) | compose 유틸리티              |

### UI-Architecture

안드로이드 UI 구조를 효과적으로 빌딩하기 위한 아키텍처 라이브러리. MVI 기반의 아키텍처로 State Event Effect를 제어하여 사용자와 UI간의 상태 및 이벤트 흐름과 사이에 발생하는 이펙트를 효과적으로
처리할 수 있습니다.

### Android-Component

안드로이드 컴포넌트 제어 라이브러리 안드로이드 컴포넌트 초기화, 설정 및 컴포넌트간의 통신과 같은 작업을 수행합니다.

## 초기화

최초로 프로젝트를 다운받은 후 `./init_lint_settings.sh` 를 실행해주세요. commit changes 에 대한 lint를 자동적으로 수행합니다.

## AAR 배포

Lemon Android Core Module에서 사용되는 라이브러리를 배포해야 할 상황이 존재할 경우 루트 디렉터리에 존재하는 `assemble_aar.sh` 스크립트 파일을 실행하면 됩니다.
이때 생성되는 AAR들은 난독화가 적용되어 있습니다. 배포되는 aar들의 난독화 여부와 flavor 구성들을 수정하고 싶을 경우, `build-system` 모듈의 `Config` 를 확인하세요.

