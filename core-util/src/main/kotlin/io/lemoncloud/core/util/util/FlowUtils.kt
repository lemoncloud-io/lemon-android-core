package io.lemoncloud.core.util.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit

object FlowUtils {

    /**
     * [generateTickFlow]
     * - 일정 시간 간격으로 틱을 발생시키는 Flow를 생성합니다.
     * @param interval 시간 간격
     * @param timeUnit 시간 단위 (ex. MILLISECONDS, SECONDS, MINUTES)
     * @param tickCount 틱 발생 개수 (기본값: Long.MAX_VALUE)
     * @return current Timestamp and current Count
     */
    fun generateTickFlow(
        interval: Long, timeUnit: TimeUnit, tickCount: Long = Long.MAX_VALUE
    ): Flow<Pair<Long, Long>> = flow {
        var currentCount = 0L
        while (tickCount > currentCount) {
            emit(System.currentTimeMillis() to currentCount)
            delay(timeUnit.toMillis(interval))
            currentCount++
        }
    }
}
