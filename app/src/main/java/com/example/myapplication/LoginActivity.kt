package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ImageLoader
import coil3.compose.rememberAsyncImagePainter
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import com.example.myapplication.ui.component.appbar.TopAppBarView
import com.example.myapplication.ui.theme.MyApplicationTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun LoginScreen(modifier: Modifier = Modifier) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBarView(title = "登录", subtitle = "没有账号将自动为你注册")

        val loader = ImageLoader.Builder(LocalContext.current)
            .components {}.build()

        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .components {
                add(GifDecoder.Factory())
            }
            .build()

        Image(
            modifier = Modifier
                .padding(top = 2.dp)
                .width(255.dp)
                .height(168.dp),
            painter = rememberAsyncImagePainter(
                model = R.drawable.ic_login_image,
                imageLoader = imageLoader
            ),
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            UserNameInputField(value = "") {

            }

            Spacer(modifier = Modifier.height(16.dp))

            PasswordInputField(value = "", onValueChange = {}, isPasswordVisible = false) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Password must be at least 8 characters",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Handle login */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors().copy(containerColor = Color(0xFF5086F3))
            ) {
                Text(text = "登录")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { /* Navigate to RegisterScreen */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors().copy(containerColor = Color(0xFF5086F3))
            ) {
                Text(text = "注册")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(54.dp),
                    painter = painterResource(id = R.drawable.qq),
                    tint = Color(0xFF5086F3),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun UserNameInputField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("请输入QQ号") },
        leadingIcon = {
            Icon(modifier = Modifier.size(24.dp), painter = painterResource(id = R.drawable.ic_twotone_account_circle_24), contentDescription = null)
        },
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun PasswordInputField(
    value: String,
    onValueChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit
) {
    var isPasswordVisibleState by remember { mutableStateOf(isPasswordVisible) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("请输入密码") },
        leadingIcon = {
            Icon(modifier = Modifier.size(24.dp), painter = painterResource(id = R.drawable.ic_twotone_lock_24), contentDescription = null)
        },
        trailingIcon = {
            val icon = if (isPasswordVisibleState) {
                painterResource(id = R.drawable.ic_action_visibility_off)
            } else {
                painterResource(id = R.drawable.ic_action_visibility)
            }
            IconButton(onClick = {
                isPasswordVisibleState = !isPasswordVisibleState
                onPasswordVisibilityChange()
            }) {
                Icon(icon, contentDescription = null)
            }
        },
        visualTransformation = if (isPasswordVisibleState) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(12.dp)
    )
}