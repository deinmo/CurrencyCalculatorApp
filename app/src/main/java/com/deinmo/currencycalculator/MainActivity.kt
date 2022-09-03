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
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyCalculatorTheme {
                // A surface container using the 'background' color from t
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = Screens.ConversionScreen.route){
                            composable(route = Screens.ConversionScreen.route){
                                ConversionScreen(navController)
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



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CurrencyCalculatorTheme {
    }
}

