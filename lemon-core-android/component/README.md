# Android-Component
android component 유틸리티

### Intent
`Intent` 설정을 빌드하는 모듈입니다. `IntentBuilder`를 사용하여 Intent를 구성할 수 있으며, 이는 `Component` 의 `Launcher`와 연동하여 사용할 수 있습니다.
또한 특수한 목적으로 사용되는 `Intent`를 빠르게 구성하는 확장 람다 함수가 존재합니다. (예를 들어, URL에 대한 사이트를 빠르게 불러오는 `getUrlIntent`, Application 설정으로 빠르게 이동하는 `getSettingIntent`)
`Intent`를 컴포넌트 목적에 따른 `PendingIntent`로 변환하는 함수는 `PendingIntent` Object 내에 존재합니다.

### Launcher
`Android Component` 설정을 빠르게 구성하는 모듈입니다.
`Launcher` 생성 시 `Intent` 가 구성되며, 컴포넌트를 수행할 수 있는 함수가 존재합니다. 내부 `Intent` 정보는 `IntentBuilder`를 사용하여 수정할 수 있습니다. 이를 통해 extra,data 그리고 특수한 flag등을 설정할 수 있습니다.







