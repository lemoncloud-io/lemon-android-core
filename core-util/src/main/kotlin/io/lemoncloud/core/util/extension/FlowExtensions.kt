package io.lemoncloud.core.util.extension

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit

object FlowExtensions {

    /**
     * 일정 시간 간격으로 틱을 발생시키는 Flow를 생성합니다.
     * @param interval 시간 간격
     * @param timeUnit 시간 단위 (ex. MILLISECONDS, SECONDS, MINUTES)
     * @param tickCount 틱 발생 개수 (기본값: Long.MAX_VALUE)
     * @return current Timestamp and current Count
     */
    fun tickFlow(
        interval: Long, timeUnit: TimeUnit, tickCount: Long = Long.MAX_VALUE
    ): Flow<Pair<Long, Long>> = flow {
        var currentCount = 0L
        while (tickCount > currentCount) {
            emit(System.currentTimeMillis() to currentCount)
            delay(timeUnit.toMillis(interval))
            currentCount++
        }
    }

    /**
     * 다수의 Flow를 결합하는 익스텐션
     */
    @Suppress("UNCHECKED_CAST")
    fun <T1, T2, T3, T4, T5, T6, R> combine(
        flow: Flow<T1>,
        flow2: Flow<T2>,
        flow3: Flow<T3>,
        flow4: Flow<T4>,
        flow5: Flow<T5>,
        flow6: Flow<T6>,
        transform: suspend (T1, T2, T3, T4, T5, T6) -> R,
    ): Flow<R> = combine(flow, flow2, flow3, flow4, flow5, flow6) { args: Array<*> ->
        transform(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
            args[3] as T4,
            args[4] as T5,
            args[5] as T6,
        )
    }

    /**
     * 다수의 Flow를 결합하는 익스텐션
     */
    @Suppress("UNCHECKED_CAST")
    fun <T1, T2, T3, T4, T5, T6, T7, R> combine(
        flow: Flow<T1>,
        flow2: Flow<T2>,
        flow3: Flow<T3>,
        flow4: Flow<T4>,
        flow5: Flow<T5>,
        flow6: Flow<T6>,
        flow7: Flow<T7>,
        transform: suspend (T1, T2, T3, T4, T5, T6, T7) -> R,
    ): Flow<R> = combine(flow, flow2, flow3, flow4, flow5, flow6, flow7) { args: Array<*> ->
        transform(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
            args[3] as T4,
            args[4] as T5,
            args[5] as T6,
            args[6] as T7,
        )
    }

    /**
     * [Result] 타입의 플로우를 결합하는 익스텐션
     */
    fun <T1, T2> combineResultFlow(
        flow: Flow<Result<T1>>,
        flow2: Flow<Result<T2>>,
    ) = combineTransform(
        flow = flow,
        flow2 = flow2,
        transform = { a: Result<T1>, b: Result<T2> ->
            emit(runCatching { a.getOrThrow() to b.getOrThrow() })
        }
    )

    /**
     * [Result] 타입의 플로우를 결합하는 익스텐션
     */
    fun <T1, T2, T3> combineResultFlow(
        flow: Flow<Result<T1>>,
        flow2: Flow<Result<T2>>,
        flow3: Flow<Result<T3>>,
    ) = combineTransform(
        flow = flow,
        flow2 = flow2,
        flow3 = flow3,
        transform = { a: Result<T1>, b: Result<T2>, c: Result<T3> ->
            emit(runCatching { Triple(a.getOrThrow(), b.getOrThrow(), c.getOrThrow()) })
        }
    )
}
