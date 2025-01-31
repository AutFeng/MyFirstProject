package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.component.HeaderView
import com.example.myapplication.ui.component.nav.BottomNavigationItem
import com.example.myapplication.ui.component.nav.BottomNavigationView
import com.example.myapplication.ui.component.page.FeedPage
import com.example.myapplication.ui.component.page.MainPage
import com.example.myapplication.ui.component.page.SettingPage
import com.example.myapplication.ui.component.page.ToolPage
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainPreview(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val backgroundColor = Color.Gray.copy(.05f)

    val bottomNavItems = listOf(
        BottomNavigationItem("首页", R.drawable.ic_twotone_toys_24, R.drawable.ic_twotone_toys_24),
        BottomNavigationItem("工具", R.drawable.ic_twotone_card_giftcard_24, R.drawable.ic_twotone_card_giftcard_24),
        BottomNavigationItem("公告", R.drawable.ic_twotone_widgets_24, R.drawable.ic_twotone_widgets_24),
        BottomNavigationItem("设置", R.drawable.ic_twotone_settings_24, R.drawable.ic_twotone_settings_24)
    )

    var currentSelectedPageIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(pageCount = {4})

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            currentSelectedPageIndex = pagerState.currentPage
        }
    }

    LaunchedEffect(key1 = currentSelectedPageIndex) {
        pagerState.animateScrollToPage(
            page = currentSelectedPageIndex,
            animationSpec = tween(
                durationMillis = 300, // Reduced duration for smoother transition
                easing = FastOutSlowInEasing
            )
        )
    }

    Column(modifier = modifier.background(backgroundColor)) {
        // Content
        HorizontalPager(
            modifier = Modifier
                .padding(top = if (currentSelectedPageIndex == 0) 0.dp else 1.dp)
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colorScheme.background),
            state = pagerState,
            key = { it },
            userScrollEnabled = false
        ) { page ->
            when(page) {
                0 -> MainPage().View(backgroundColor = backgroundColor)
                1 -> FeedPage().View(backgroundColor = backgroundColor)
                2 -> ToolPage().View(backgroundColor = backgroundColor)
                3 -> SettingPage().View(backgroundColor = backgroundColor)
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
            .background(MaterialTheme.colorScheme.background)
        ) {
            BottomNavigationView(
                items = bottomNavItems,
                selectedItemIndex = currentSelectedPageIndex,
                onSelectedItemChanged = {
                    Toast.makeText(context, "选中: ${bottomNavItems[it].text}", Toast.LENGTH_SHORT).show()
                    currentSelectedPageIndex = it
                }
            )
        }
    }
}

@Composable
fun MainHeader(
    headerTitle: String,
    headerSubTitle: String,
    showUnderLine: Boolean = true,
    showButton: Boolean = false
) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .drawWithContent {
            drawContent()
            if (showUnderLine) {
                drawIntoCanvas {
                    val paint = Paint().apply {
                        color = Color.Gray.copy(alpha = .25f)
                    }
                    it.drawRect(
                        0f,
                        size.height,
                        size.width,
                        size.height + 1.dp.toPx(),
                        paint
                    )
                }
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .background(MaterialTheme.colorScheme.background)
            )
            HeaderView(
                modifier = Modifier.padding(15.dp, 0.dp),
                title = headerTitle,
                subTitle = headerSubTitle,
                showButton = showButton,
                buttonContent = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer)
                },
                onButtonClick = {
                    Toast.makeText(context, "搜索按钮被点击", Toast.LENGTH_SHORT).show()
                }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(MaterialTheme.colorScheme.background)
            )
        }
    }
}