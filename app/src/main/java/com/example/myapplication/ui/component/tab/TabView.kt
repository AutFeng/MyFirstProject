package com.example.myapplication.ui.component.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author lovelycat
 * @version 1.0
 */
class TabView private constructor()

@Composable
fun TabView(
    modifier: Modifier = Modifier,
    edgePadding: Dp = 16.dp,
    items: List<TabViewItem>,
    defaultSelectedItemIndex: Int = 0,
    tabContent: @Composable (TabViewItem, Boolean) -> Unit = { it, selected ->
        Text(text = it.text, color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground)
    },
    onSelectedTabChanged: ((Int) -> Unit)? = null
) {
    var selectedTabIndex by remember { mutableIntStateOf(defaultSelectedItemIndex) }

    ScrollableTabRow(
        modifier = modifier,
        edgePadding = edgePadding,
        selectedTabIndex = selectedTabIndex,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary,
        indicator = { tabPositions ->
            Box(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .fillMaxWidth()
                    .height(3.5.dp)
                    .clip(shape = RoundedCornerShape(8.dp, 8.dp, 0.dp, 0.dp))
                    .background(color = MaterialTheme.colorScheme.primary)
            )
        }
    ) {
        items.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    onSelectedTabChanged?.invoke(index)
                    selectedTabIndex = index
                },
                text = {
                    tabContent.invoke(item, selectedTabIndex == index)
                }
            )
        }
    }

}