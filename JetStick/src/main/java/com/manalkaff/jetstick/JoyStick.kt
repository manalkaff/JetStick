package com.manalkaff.jetstick

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun JoyStick(size: Int = 100, dotSize: Int = (size/3.3).roundToInt(), maxX: Int = size/2, maxY: Int = size/2, centerX: Float = (size/1.886).toFloat(), centerY: Float = (size/1.886).toFloat(), moved: (x: Float, y: Float) -> Unit) {
    Box(
        modifier = Modifier.size(
            width = size.dp,
            height = size.dp
        )
    ) {
        Image(
            painterResource(id = R.drawable.joystick_background_1),
            "aa",
            modifier = Modifier.size(size.dp),
        )
        var offsetX by remember { mutableStateOf(centerX) }
        var offsetY by remember { mutableStateOf(centerY) }

        Image(
            painterResource(id = R.drawable.joystick_dot_1),
            "JoyStickDot",
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .size(dotSize.dp)
                .pointerInput(Unit) {
                    detectDragGestures(onDragEnd = {
                        offsetX = centerX
                        offsetY = centerY
                    }) { pointerInputChange: PointerInputChange, offset: Offset ->
                        val x: Float = offsetX + offset.x-centerX
                        val y: Float = offsetY + offset.y-centerY
                        pointerInputChange.consume()
                        if(x > maxX || x < -maxX){
                            if(x > maxX){
                                offsetX = centerX + maxX
                            }
                            if(x < -maxX){
                                offsetX = centerX + -maxX
                            }
                        }else{
                            offsetX += offset.x
                        }

                        if(y > maxY || y < -maxY){
                            if(y > maxY){
                                offsetY = centerY + maxY
                            }
                            if(y < -maxY){
                                offsetY = centerY + -maxY
                            }
                        }else{
                            offsetY += offset.y
                        }

                    }
                }.onGloballyPositioned { coordinates->
                    moved(coordinates.positionInParent().x-centerX, -(coordinates.positionInParent().y-centerY))
                },
        )
    }
}