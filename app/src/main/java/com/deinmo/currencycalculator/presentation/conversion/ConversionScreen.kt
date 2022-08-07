package com.deinmo.currencycalculator.presentation.conversion

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deinmo.currencycalculator.R
import com.deinmo.currencycalculator.presentation.CurrencyViewModel


@Composable
fun ConversionScreen(
    viewModel: ConversionViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val listKeys = mutableListOf<String>()
    if(state.counrtyModels != null){
    state.counrtyModels.forEach {
        it.symbol?.let { it1 -> listKeys.add(it1) }
    }
        Log.d("Message",state.counrtyModels[6].countryName ?: "No name")
    }
    Box(modifier = Modifier
        .background(color = Color.White)
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
                Icon(painter = painterResource(id = R.drawable.ic_baseline_menu_24), contentDescription = "navigate",modifier = Modifier.background(
                    Color.Gray))
                Text(text = "Signup",color = Color.Green)
            }
            var amount = myTextView()
            var fromCurrency = myDropDown(listKeys)
            var toCurrency = myDropDown(listKeys)
            Button(onClick = {
                text = viewModel.convert(fromCurrency,toCurrency,amount.toDouble()).toString()
            }) {
                Text(text = "Convert")
            }
            Text(text = text,color = Color.Green)
        }

    }
}


@Composable
fun myDropDown(items:List<String>?): String {
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
            items?.forEach { item ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    selected = item
                }) {
                    Text(text = item)
                }
            }

        }
    }
    return selected
}

@Composable
fun myTextView(): String {
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