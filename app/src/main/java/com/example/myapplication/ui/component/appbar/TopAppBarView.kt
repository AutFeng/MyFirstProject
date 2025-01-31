package com.example.myapplication.ui.component.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author lovelycat
 * @since 2025-01-25 17:21
 * @version 1.0
 */
class TopAppBarView private constructor()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(
    title: String,
    titleFontColor: Color = Color.Black,
    subtitle: String? = null,
    subTitleFontColor: Color = Color.Gray,
    containerColor: Color = Color.White,
    showBackButton: Boolean = true,
    onBackPressed: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(0.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(Color.Transparent, Color.Gray),
                    start = Offset(0f, 0f),
                    end = Offset(0f, 10f)
                )
            ),
        title = {
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(title, color = titleFontColor, maxLines = 1)
                subtitle?.let {
                    Text(it, fontSize = 12.sp, color = subTitleFontColor, maxLines = 1, lineHeight = 12.sp)
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor
        ),
        navigationIcon = {
            if (showBackButton) {
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = { onBackPressed?.invoke() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }
        }
    )
}