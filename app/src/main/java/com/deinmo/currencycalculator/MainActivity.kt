package com.deinmo.currencycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                // A surface container using the 'background' color from t
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                        toplevel()
                    }
            }
        }
    }
}

@Composable
fun toplevel(){
    var currencyViewModel = CurrencyViewModel()
    var string: MutableList<String>? = mutableListOf()

    LaunchedEffect(key1 ="key") {
        var symbols = currencyViewModel.getsymbols().value
        symbols?.symbols?.AED?.let { string?.set(0, it) }
        symbols?.symbols?.AFN?.let { string?.set(1, it) }
        symbols?.symbols?.ALL?.let { string?.set(2, it) }
        symbols?.symbols?.AMD?.let { string?.set(3, it) }
    }
    MainScreen(name = string)
}

@Composable
fun MainScreen(name: List<String>?) {
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
            var str = mytextview()
            Text(text = str,color = Color.Green)
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)){
                mydropdown(name)
                mydropdown(name)
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CurrencyCalculatorTheme {
        var currencyViewModel = CurrencyViewModel()
        var string: MutableList<String>? = mutableListOf()
        LaunchedEffect(key1 ="key") {
            var symbols = currencyViewModel.getsymbols().value
            symbols?.symbols?.AED?.let { string?.set(0, it) }
            symbols?.symbols?.AFN?.let { string?.set(1, it) }
            symbols?.symbols?.ALL?.let { string?.set(2, it) }
            symbols?.symbols?.AMD?.let { string?.set(3, it) }
        }
        MainScreen(name = string)
    }
}

@Composable
fun mytextview(): String {
    var text by remember{
        mutableStateOf("")
    }
    var currencyViewModel = CurrencyViewModel()
    var conversionClass = ConversionClass()
    var string = String()
    TextField(value = text, onValueChange = {text = it}, modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)
        .background(Color.Gray),placeholder = {
        Text(text = "enter currency")})
    LaunchedEffect(key1 = "key2", block ={
        conversionClass = currencyViewModel.getconversion(text.toInt()).value!!
        string = conversionClass.result.toString()
    })
    return string
}

@Composable
fun mydropdown(items:List<String>?){
    var expanded by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableStateOf(0)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopStart)) {
        items?.let {
            Text(it?.get(selected),modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .background(
                    Color.DarkGray
                ))
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier
            .wrapContentSize()
            .background(color = Color.Gray)) {

            }
        }
    }
