package io.lemon.core.compose.util.modifier

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

/**
 * @param shape 그림자에 적용할 모양
 * @param color 그림자 색상
 * @param offset 그림자의 위치
 * @param blur 그림자 흐림 효과 (번짐의 정도)
 * @param spread 그림자의 확산효과이며, 그림자의 크기;
 * 음수로 할 경우 그림자의 크기가 기존 도형보다 줄어듭니다.
 */
fun Modifier.dropShadow(
    shape: Shape,
    color: Color,
    offset: DpOffset,
    blur: Dp = 1.dp,
    spread: Dp = 0.dp
) = drawBehind {

    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(dx = offset.x.toPx(), dy = offset.y.toPx()) // 캔버스 위치 이동
        canvas.drawOutline(
            outline = shape.createOutline(
                size = Size(
                    width = size.width + spread.toPx(),
                    height = size.height + spread.toPx()
                ),
                layoutDirection = layoutDirection,
                density = this
            ), // 그림자 사이즈 구성
            paint = Paint().apply {
                this.color = color
                blur.toPx().takeIf { it > 0f }?.let { radius ->
                    asFrameworkPaint().apply {
                        maskFilter = BlurMaskFilter(
                            /* radius = */ radius,
                            /* style = */ BlurMaskFilter.Blur.NORMAL
                        )
                    }
                }
            } // 그림자 스타일 구성
        ) // 그림자 생성
        canvas.restore()
    }
}

/**
 * @param shape 그림자에 적용할 모양
 * @param color 그림자 색상
 * @param yOffset 그림자의 y 오프셋
 * @param xOffset 그림자의 x 오프셋
 * @param blur 그림자 흐림 효과 (번짐의 정도)
 * @param spread 그림자의 확산효과이며, 그림자의 크기;
 * 음수로 할 경우 그림자의 크기가 기존 도형보다 줄어듭니다.
 */
fun Modifier.dropShadow(
    shape: Shape,
    color: Color,
    yOffset: Dp = 0.dp,
    xOffset: Dp = 0.dp,
    blur: Dp = 1.dp,
    spread: Dp = 0.dp
) = drawBehind {

    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(dx = xOffset.toPx(), dy = yOffset.toPx()) // 캔버스 위치 이동
        canvas.drawOutline(
            outline = shape.createOutline(
                size = Size(
                    width = size.width + spread.toPx(),
                    height = size.height + spread.toPx()
                ),
                layoutDirection = layoutDirection,
                density = this
            ), // 그림자 사이즈 구성
            paint = Paint().apply {
                this.color = color
                blur.toPx().takeIf { it > 0f }?.let { radius ->
                    asFrameworkPaint().apply {
                        maskFilter = BlurMaskFilter(
                            /* radius = */ radius,
                            /* style = */ BlurMaskFilter.Blur.NORMAL
                        )
                    }
                }
            } // 그림자 스타일 구성
        ) // 그림자 생성
        canvas.restore()
    }
}
