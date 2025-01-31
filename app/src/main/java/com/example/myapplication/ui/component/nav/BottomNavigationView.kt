package com.example.myapplication.ui.component.nav

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * @author lovelycat
 * @version 1.0
 */
class BottomNavigationView private constructor()

@Composable
fun BottomNavigationView(
    modifier: Modifier = Modifier,
    items: List<BottomNavigationItem>,
    selectedItemIndex: Int = 0,
    containerColor: Color = MaterialTheme.colorScheme.background,
    itemContainerColor: Color = MaterialTheme.colorScheme.background,
    itemContainerActiveColor: Color = MaterialTheme.colorScheme.primaryContainer,
    itemContentColor: Color = MaterialTheme.colorScheme.onBackground,
    itemContentActiveColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    padding: PaddingValues = PaddingValues(16.dp, 16.dp),
    onSelectedItemChanged: ((Int) -> Unit)? = null
) {
    var currentSelectedItemIndex by remember { mutableIntStateOf(selectedItemIndex) }

    LaunchedEffect(key1 = selectedItemIndex) {
        currentSelectedItemIndex = selectedItemIndex
    }

    LazyRow(
        modifier = modifier
            .padding(padding)
            .fillMaxWidth()
            .background(containerColor),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        itemsIndexed(items) { index, item ->
            val animatedColor by animateColorAsState(
                targetValue = if (currentSelectedItemIndex == index)
                    itemContainerActiveColor
                else
                    itemContainerColor,
                label = "color"
            )

            val width = 100.dp
            val animatedWidth by animateDpAsState(
                targetValue = if (currentSelectedItemIndex == index) width else 64.dp,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = FastOutSlowInEasing
                ),
                label = "width"
            )

            Box(
                modifier = Modifier
                    .width(animatedWidth)
                    .clip(shape = CircleShape)
                    .background(animatedColor)
                    .clickable {
                        onSelectedItemChanged?.invoke(index)
                        currentSelectedItemIndex = index
                    },
            ) {
                Row(
                    modifier = Modifier
                        .background(animatedColor)
                        .padding(12.dp, 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    with(
                        if (index == currentSelectedItemIndex)
                            item.activeIcon to itemContentActiveColor
                        else
                            item.icon to itemContentColor
                    ) {
                        when (this.first) {
                            is Int -> Icon(painter = painterResource(id = this.first as Int), tint = this.second, contentDescription = null)
                            is Painter -> Icon(painter = this.first as Painter, tint = this.second, contentDescription = null)
                            is ImageVector -> Icon(imageVector = this.first as ImageVector, tint = this.second, contentDescription = null)
                        }
                    }

                    if (index == currentSelectedItemIndex && animatedWidth >= width * 0.95f) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = item.text, color = itemContentActiveColor)
                    }
                }
            }
        }
    }
}