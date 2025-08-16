package com.example.welcomeapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.welcomeapp.model.UserData
import com.example.welcomeapp.network.ApiService
import com.example.welcomeapp.repository.UserRepository
import com.example.welcomeapp.ui.theme.WelcomeAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup repository and API service
        val userRepository = UserRepository()
        val apiService = userRepository.getApiService()

        setContent {
            WelcomeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingInput(modifier = Modifier.padding(innerPadding), apiService = apiService)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun GreetingInput(modifier: Modifier = Modifier, apiService: ApiService) {
    var name by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Greeting(name = name)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            // Example API call when button clicked
            val call: Call<List<UserData>> = apiService.getUsers()
            call.enqueue(object : Callback<List<UserData>> {
                override fun onResponse(call: Call<List<UserData>>, response: Response<List<UserData>>) {
                    if (response.isSuccessful && response.body() != null) {
                        val users = response.body()!!
                        Toast.makeText(context, "Fetched ${users.size} users", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "API error: ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<List<UserData>>, t: Throwable) {
                    Toast.makeText(context, "Network error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }) {
            Text("Fetch Users")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WelcomeAppTheme {
        GreetingInput(apiService = UserRepository().getApiService())
    }
}
