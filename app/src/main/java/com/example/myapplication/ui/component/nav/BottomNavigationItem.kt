package com.example.myapplication.ui.component.nav

/**
 * @author lovelycat
 * @version 1.0
 */
data class BottomNavigationItem(
    // 选项文本
    val text: String,
    // 选项图标 支持 DrawableId Painter ImageVector
    val icon: Any,
    // 选项选中图标 同上
    val activeIcon: Any
)