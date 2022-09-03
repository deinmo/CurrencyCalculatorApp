package com.deinmo.currencycalculator.presentation

import android.os.Build
import android.util.Log
import android.widget.Space
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deinmo.currencycalculator.domain.models.CountryModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CurrencyListScreen(
    viewModel: CurrencyViewModel = hiltViewModel()
){
    val state = viewModel.state
    if (state.counrtyModels == null){
        Log.d("message","null string")
    }else{
        Log.d("Message","Successful")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.counrtyModels){ countryModel ->
                    CurrencyListItem(countryModel = countryModel)
            }
        }
    }
}


@Composable
fun CurrencyListItem(
    countryModel: CountryModel,
    modifier: Modifier = Modifier
    ){
    Row(
        modifier = modifier.padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = countryModel.countryName!!,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = countryModel.rate.toString(),
                    modifier = Modifier,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = countryModel.symbol!!, color = MaterialTheme.colors.onBackground)
            }
        }
    }
