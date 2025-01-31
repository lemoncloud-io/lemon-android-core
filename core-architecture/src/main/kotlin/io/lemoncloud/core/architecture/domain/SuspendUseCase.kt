package io.lemoncloud.core.architecture.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * - 도메인 레이어의 유즈 케이스를 처리합니다.
 */
abstract class SuspendUseCase<In, Out>(private val coroutineDispatcher: CoroutineDispatcher) {

    protected abstract suspend fun execute(params: In): Result<Out>

    suspend operator fun invoke(parameters: In): Result<Out> {
        return withContext(coroutineDispatcher) { execute(parameters) }
    }
}
