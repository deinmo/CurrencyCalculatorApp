package com.deinmo.currencycalculator

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deinmo.currencycalculator.data.remote.dtos.conversionclass.ConversionClass
import com.deinmo.currencycalculator.presentation.CurrencyListScreen
import com.deinmo.currencycalculator.presentation.CurrencyViewModel
import com.deinmo.currencycalculator.presentation.conversion.ConversionScreen
import com.deinmo.currencycalculator.ui.theme.CurrencyCalculatorTheme
import com.deinmo.currencycalculator.utils.Screens
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyCalculatorTheme {
                // A surface container using the 'background' color from t
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = Screens.ConversionScreen.route){
                            composable(route = Screens.ConversionScreen.route){
                                ConversionScreen()
                            }
                            composable(route = Screens.CurrencyListScreen.route){
                                CurrencyListScreen()
                            }
                        }
                    }
            }
        }
    }
}

@Composable
fun toplevel(){
    var string: MutableList<String>? = mutableListOf<String>("AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN","BAM","BBD","BDT","BGN","BHD","BIF","BMD","BND","BOB","BRL","BSD","BTC","BTN","BWP","BYN","BYR","BZD","CAD","CDF","CHF","CLF","CLP","CNY","COP","CRC","CUC","CUP","CVE","CZK","DJF","DKK","DOP","DZD","EGP","ERN","ETB","EUR","FJD","FKP","GBP","GEL","GGP","GHS","GIP","GMD","GNF","GTQ","GYD","HKD","HNL","HRK","HTG","HUF","IDR","ILS","IMP","INR","IQD","IRR","ISK","JEP","JMD","JOD","JPY","KES","KGS","KHR","KMF","KPW","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD","LSL","LTL","LVL","LYD","MAD","MDL","MGA","MKD","MMK","MNT","MOP","MRO","MUR","MVR","MWK","MXN","MYR","MZN","NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR","RON","RSD","RUB","RWF","SAR","SBD","SCR","SDG","SEK","SGD","SHP","SLL","SOS","SRD","STD","SVC","SYP","SZL","THB","TJS","TMT","TND","TOP","TRY","TTD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VEF","VND","VUV","WST","XAF","XAG","XAU","XCD","XDR","XOF","XPF","YER","ZAR","ZMK","ZMW","ZWL")
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
            var srt1 = mydropdown(name)
            var str2 = mydropdown(name)
    /*        var string  = Button(from = srt1, to = str2, text = str)
            Text(text = string,color = Color.Green)*/

        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CurrencyCalculatorTheme {
        var string: MutableList<String>? = mutableListOf<String>("AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN","BAM","BBD","BDT","BGN","BHD","BIF","BMD","BND","BOB","BRL","BSD","BTC","BTN","BWP","BYN","BYR","BZD","CAD","CDF","CHF","CLF","CLP","CNY","COP","CRC","CUC","CUP","CVE","CZK","DJF","DKK","DOP","DZD","EGP","ERN","ETB","EUR","FJD","FKP","GBP","GEL","GGP","GHS","GIP","GMD","GNF","GTQ","GYD","HKD","HNL","HRK","HTG","HUF","IDR","ILS","IMP","INR","IQD","IRR","ISK","JEP","JMD","JOD","JPY","KES","KGS","KHR","KMF","KPW","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD","LSL","LTL","LVL","LYD","MAD","MDL","MGA","MKD","MMK","MNT","MOP","MRO","MUR","MVR","MWK","MXN","MYR","MZN","NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR","RON","RSD","RUB","RWF","SAR","SBD","SCR","SDG","SEK","SGD","SHP","SLL","SOS","SRD","STD","SVC","SYP","SZL","THB","TJS","TMT","TND","TOP","TRY","TTD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VEF","VND","VUV","WST","XAF","XAG","XAU","XCD","XDR","XOF","XPF","YER","ZAR","ZMK","ZMW","ZWL")
        MainScreen(name = string)
    }
}

@Composable
fun mytextview(): String {
    var text by remember{
        mutableStateOf("")
    }
    TextField(value = text, onValueChange = {text = it}, modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)
        .background(Color.Gray),placeholder = {
        Text(text = "enter currency")})
    return text
}

@Composable
fun mydropdown(items:List<String>?): String {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selected: String by remember {
        mutableStateOf(items?.get(0)!!)
    }
    Box(modifier = Modifier
        .fillMaxWidth(),contentAlignment = Alignment.Center) {
        Row(modifier = Modifier
            .padding(16.dp)
            .background(Color.Blue)
            .clickable { expanded != expanded }
            .padding(8.dp),horizontalArrangement = Arrangement.Center,verticalAlignment = Alignment.CenterVertically) {
            Text(text = selected,modifier = Modifier.padding(end = 8.dp))
            Icon(imageVector = Icons.Filled.ArrowDropDown,contentDescription = "")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier
            .background(color = Color.Gray)) {
            if (items != null) {
                items.forEach {
                    item -> DropdownMenuItem(onClick = {
                    expanded = false
                    selected = item
                }) {
                    Text(text = item)
                }
                }
            }

            }
        }
    return selected
    }
/*@Composable

fun Button(from: String,to: String,text: String ): String {
    var currencyViewModel = CurrencyViewModel()
    var conversionClass: ConversionClass
    var string = String()
    val coroutineScope = rememberCoroutineScope()
    androidx.compose.material.Button(onClick = {
        coroutineScope.launch { conversionClass = currencyViewModel.getconversion(from,to,text).value!!
            string = conversionClass.result.toString() }

    }){
        Text(text = "Convert")
    }
    return string
}*/
