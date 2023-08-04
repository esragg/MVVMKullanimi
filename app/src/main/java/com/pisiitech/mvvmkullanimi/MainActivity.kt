package com.pisiitech.mvvmkullanimi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pisiitech.mvvmkullanimi.ui.theme.MVVMKullanimiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMKullanimiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Sayfa()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sayfa() {
    val tfSayi1 =  remember {mutableStateOf("") }
    val tfSayi2 =  remember {mutableStateOf("") }
    val viewModel:SayfaViewModel = viewModel()
    val sonuc =  remember {mutableStateOf("0") }
    Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = sonuc.value, fontSize = 50.sp)
        TextField(
            value = tfSayi1.value,
            onValueChange = {tfSayi1.value = it},
            label = { Text(text = "Sayi 1")}
        )
        TextField(
            value = tfSayi2.value,
            onValueChange = {tfSayi2.value = it},
            label = { Text(text = "Sayi 2")}
        )
        Button(onClick = {
            viewModel.toplamaYap(tfSayi1.value,tfSayi2.value)

            sonuc.value = viewModel.sonuc
        }) {
            Text(text = "TOPLAMA")
        }
        Button(onClick = {
            viewModel.carpmaYap(tfSayi1.value, tfSayi2.value)

            sonuc.value = viewModel.sonuc
        }) {
            Text(text = "CARPMA")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MVVMKullanimiTheme {
        Sayfa()
    }
}