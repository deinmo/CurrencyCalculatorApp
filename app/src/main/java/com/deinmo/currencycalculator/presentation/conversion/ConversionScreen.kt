package com.deinmo.currencycalculator.presentation.conversion

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deinmo.currencycalculator.R
import com.deinmo.currencycalculator.domain.models.CountryModel
import com.deinmo.currencycalculator.presentation.CurrencyViewModel
import com.deinmo.currencycalculator.utils.Screens

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ConversionScreen(
    navController: NavController,
    viewModel: ConversionViewModel = hiltViewModel()
) {
    val state = viewModel.state
    if (state.counrtyModels == null){
        Log.d("message","null string")
    }else{
        Log.d("Message","Successful")
    }
    Box(modifier = Modifier
        .fillMaxSize()) {
        Column {
            var text:String by remember {
                mutableStateOf("")
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)) {

            }
            var amount = myTextView()
            Spacer(modifier = Modifier.height(6.dp))
            var fromCurrency = myDropDown(state.counrtyModels,"fromCurrency")
            Spacer(modifier = Modifier.height(6.dp))
            var toCurrency = myDropDown(state.counrtyModels, "toCurrency")
            Spacer(modifier = Modifier.height(6.dp))
            Button(onClick = {
                text = viewModel.convert(fromCurrency,toCurrency,amount.toDouble()).toString()
            }) {
                Text(text = "Convert")
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = text,color = Color.Green)
            Spacer(modifier = Modifier.height(5.dp))
            Button(onClick = {
                navController.navigate(Screens.CurrencyListScreen.route)
            }) {
                Text(text = "Get the rates")
            }
        }

    }
}


@Composable
fun myDropDown(items:List<CountryModel>?,amountText: String): String {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selected: String by remember {
        mutableStateOf( "Nigerian Naira NGN")
    }

    var selectedString by remember {
        mutableStateOf("NGN")
    }
    Box(modifier = Modifier
        .fillMaxWidth(),contentAlignment = Alignment.Center) {
        Row(modifier = Modifier
            .padding(16.dp)
            .background(Color.Blue)
            .clickable { expanded = !expanded }
            .padding(8.dp),horizontalArrangement = Arrangement.Center,verticalAlignment = Alignment.CenterVertically) {
            Text(text = selected
            )
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
            Text(text = amountText,color = Color.DarkGray)
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier
            .background(color = Color.Gray)
            .padding(4.dp)) {
            items?.forEach { item ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    selected = item.countryName!! + "  " + item.symbol!!
                    selectedString = item.symbol!!
                }) {
                    Text(text = item.countryName!! + "  " + item.symbol!!)
                }
            }
        }

        }
    }
    return selectedString
}

@Composable
fun myTextView(): String {
    var text by remember{
        mutableStateOf("")
    }
    OutlinedTextField(value = text, onValueChange = {text = it}, modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp),placeholder = {
        Text(text = "enter currency")})
    return text
}