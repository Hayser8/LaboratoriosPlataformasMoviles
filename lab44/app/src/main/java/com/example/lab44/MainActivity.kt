package com.example.lab44
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab44.ui.theme.Lab44Theme
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab44Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeApp() // Cambio aquí
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab44Theme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeApp() {
    var itemList by remember { mutableStateOf(mutableStateListOf<String>()) }
    var recipeName by remember { mutableStateOf(TextFieldValue()) }
    var recipeImageUrl by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = recipeName,
            onValueChange = { recipeName = it },
            label = { Text("Recipe Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = recipeImageUrl,
            onValueChange = { recipeImageUrl = it },
            label = { Text("Recipe Image URL") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            itemList.add("${recipeName.text} - ${recipeImageUrl.text}")
            recipeName = TextFieldValue("")
            recipeImageUrl = TextFieldValue("")
        }) {
            Text(text = "Agregar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            itemsIndexed(itemList) { index, item ->
                CustomListItem(item)
            }
        }
    }
}



@Composable
fun CustomListItem(item: String) {
    val itemName = item.substringBefore(" - ")
    val imageUrl = item.substringAfter(" - ")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = "Recipe Image",
            modifier = Modifier
                .size(80.dp)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = itemName, style = MaterialTheme.typography.headlineLarge)
    }
}
