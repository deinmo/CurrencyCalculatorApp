package com.deinmo.currencycalculator

import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deinmo.currencycalculator.ui.theme.CurrencyCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainScreen("Android")
                }
            }
        }
    }
}

@Composable
fun MainScreen(name: String) {
    Box(modifier = Modifier
        .background(color = Color.White)
        .fillMaxSize()) {
        Column {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_menu_24), contentDescription = "navigate",modifier = Modifier.background(
                    Color.Gray))
                Text(text = "Signup",color = Color.Green)
            }
            mytextview()
            mytextview()
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)){
                mydropdown()
                mydropdown()
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CurrencyCalculatorTheme {
        MainScreen("Android")
    }
}

@Composable
fun mytextview(){
    var text by remember{
        mutableStateOf("")
    }
    TextField(value = text, onValueChange = {text = it}, modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp).background(Color.Gray),placeholder = {
        Text(text = "enter currency")})
}

@Composable
fun mydropdown(){
    var expanded by remember {
        mutableStateOf(false)}
    val items = listOf("USA", "NIG","GHN","CHN","IND")
    var selected by remember {
        mutableStateOf(0)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopStart)) {
        Text(items.get(selected),modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true }
            .background(
                Color.DarkGray
            ))
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray)) {


            }
        }
    }
