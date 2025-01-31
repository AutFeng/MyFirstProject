package com.example.myapplication.ui.component.page

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.MainHeader
import com.example.myapplication.R
import com.example.myapplication.ui.component.tab.TabView
import com.example.myapplication.ui.component.tab.TabViewItem

class MainPage {
    @Composable
    fun View(backgroundColor: Color) {
        val context = LocalContext.current

        val tabItems = listOf(
            TabViewItem("测试1"),
            TabViewItem("测试2"),
            TabViewItem("测试3"),
            TabViewItem("测试4"),
            TabViewItem("测试5")
        )

        Column(modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)) {
            MainHeader(headerTitle = "abc", headerSubTitle = "123", showUnderLine = false, showButton = true)

            Row(modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp)
                        .height(128.dp)
                        .background(MaterialTheme.colorScheme.background)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.aa3d095e21d62e82cc2fe71125d9efe2),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .scale(1.5f)
                    )
                }

            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .background(MaterialTheme.colorScheme.background))

            TabView(
                modifier = Modifier,
                items = tabItems,
                onSelectedTabChanged = {
                    Toast.makeText(context, "选中: ${tabItems[it].text}", Toast.LENGTH_SHORT).show()
                }
            )

            Text(text = "Page1")
        }
    }
}