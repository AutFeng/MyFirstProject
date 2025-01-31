package com.example.myapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author lovelycat
 * @version 1.0
 */
class HeaderView private constructor()

@Composable
fun HeaderView(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    titleFontSize: TextUnit = 24.sp,
    subTitleFontSize: TextUnit = 12.sp,
    showButton: Boolean = true,
    buttonSize: Dp = 64.dp,
    buttonContainerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    buttonContainerBorderColor: Color = MaterialTheme.colorScheme.background,
    buttonContent: @Composable () -> Unit = {
        Icon(imageVector = Icons.Filled.Search, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer)
    },
    onButtonClick: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title, fontSize = titleFontSize, fontWeight = FontWeight.Bold)
            Text(text = subTitle, fontSize = subTitleFontSize)
        }

        Spacer(modifier = Modifier.weight(1f))

        if (showButton) {
            val outerButtonSize = buttonSize.times(.95f)
            val innerButtonSize = buttonSize.times(.9f)
            Box {
                Box(modifier = Modifier
                    .width(buttonSize)
                    .height(buttonSize)
                    .clip(shape = CircleShape)
                    .background(buttonContainerColor)
                    .padding(
                        buttonSize
                            .minus(outerButtonSize)
                            .div(2)
                    )
                ) {
                    Box(modifier = Modifier
                        .width(outerButtonSize)
                        .height(outerButtonSize)
                        .clip(shape = CircleShape)
                        .background(buttonContainerBorderColor)
                        .padding(
                            buttonSize
                                .minus(innerButtonSize)
                                .div(2)
                        )
                    ) {
                        IconButton(
                            modifier = Modifier
                                .size(innerButtonSize)
                                .clip(shape = CircleShape)
                                .background(buttonContainerColor),
                            onClick = { onButtonClick?.invoke() }
                        ) {
                            buttonContent.invoke()
                        }
                    }
                }
            }
        }
    }
}