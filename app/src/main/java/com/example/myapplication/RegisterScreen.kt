import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun RegisterScreen() {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Register",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = username.value,
            onValueChange = { username.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(16.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (username.value.isEmpty()) {
                    Text(text = "Username", color = Color.Gray)
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(16.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            decorationBox = { innerTextField ->
                if (password.value.isEmpty()) {
                    Text(text = "Password", color = Color.Gray)
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(16.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            decorationBox = { innerTextField ->
                if (confirmPassword.value.isEmpty()) {
                    Text(text = "Confirm Password", color = Color.Gray)
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle registration */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register")
        }
    }
}