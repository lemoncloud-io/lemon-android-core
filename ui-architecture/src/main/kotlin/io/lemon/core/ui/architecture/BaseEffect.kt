package io.lemon.core.ui.architecture

/**
 * [BaseEffect]
 *
 * 사이드 이펙트를 정의하는 인터페이스
 *
 * BaseEffect를 확장한 sealed interface 에 UI에서 사용할 이펙트들을 정의하여 사용합니다.
 *
 * UI에 사이드 이펙트는 존재하지 않을 수도 있습니다.
 *
 * [BaseViewModel.effect] 를 수집하여(collect) 이펙트를 처리할 수 있습니다.
 *
 * @author raine@lemoncloud.io
 */
interface BaseEffect
