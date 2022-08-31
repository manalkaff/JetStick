package com.manalkaff.jetstick

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.*

/**
 * Returns the absolute value of the given number.
 * @param size Joystick size
 * @param dotSize Joystick Dot size
 * @param backgroundImage Joystick Image Drawable
 * @param dotImage Joystick Dot Image Drawable
 */
@Composable
fun JoyStick(
    modifier: Modifier = Modifier,
    size: Dp = 170.dp,
    dotSize: Dp = 40.dp,
    backgroundImage: Int = R.drawable.joystick_background_1,
    dotImage: Int = R.drawable.joystick_dot_1,
    moved: (x: Float, y: Float) -> Unit = { _, _ -> }
) {
    Box(
        modifier = modifier
            .size(size)
    ) {
        val maxRadius = with(LocalDensity.current) { (size / 2).toPx() }
        val centerX = with(LocalDensity.current) { ((size - dotSize) / 2).toPx() }
        val centerY = with(LocalDensity.current) { ((size - dotSize) / 2).toPx() }

        var offsetX by remember { mutableStateOf(centerX) }
        var offsetY by remember { mutableStateOf(centerY) }

        Image(
            painterResource(id = backgroundImage),
            "JoyStickBackground",
            modifier = Modifier.size(size),
        )

        Image(
            painterResource(id = dotImage),
            "JoyStickDot",
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .size(dotSize)
                .pointerInput(Unit) {
                    detectDragGestures(onDragEnd = {
                        offsetX = centerX
                        offsetY = centerY
                    }) { pointerInputChange: PointerInputChange, offset: Offset ->
                        val x = offsetX + offset.x - centerX
                        val y = offsetY + offset.y - centerY
                        pointerInputChange.consume()
                        val theta = atan(-y / x)
                        if ((x.pow(2)) + (y.pow(2)) > maxRadius.pow(2)) {
                            if (x > 0 && y > 0) {
                                offsetX = centerX + (maxRadius * cos(theta))
                                offsetY = centerY + (maxRadius * -sin(theta))
                            } else if (x > 0 && y < 0) {
                                offsetX = centerX + (maxRadius * cos(theta))
                                offsetY = centerY + (maxRadius * -sin(theta))
                            } else if (x < 0 && y > 0) {
                                offsetX = centerX + (maxRadius * -cos(theta))
                                offsetY = centerY + (maxRadius * sin(theta))
                            } else {
                                offsetX = centerX + (maxRadius * -cos(theta))
                                offsetY = centerY + (maxRadius * sin(theta))
                            }
                        } else {
                            offsetX += offset.x
                            offsetY += offset.y
                        }
                    }
                }
                .onGloballyPositioned { coordinates ->
                    moved(
                        coordinates.positionInParent().x - centerX,
                        -(coordinates.positionInParent().y - centerY)
                    )
                },
        )
    }
}
