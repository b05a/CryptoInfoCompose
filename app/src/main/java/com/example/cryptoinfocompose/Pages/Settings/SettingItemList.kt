package com.example.cryptoinfocompose.Pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cryptoinfocompose.Classes.Crypto
import com.example.cryptoinfocompose.NavControl.Screen
import com.example.cryptoinfocompose.R
import com.example.cryptoinfocompose.ViewModel.MainViewModel

@Composable
fun SettingItemList(num:Int, crypto: Crypto, vm:MainViewModel, navController: NavController){
    Box(modifier = Modifier
        .clickable { }
        .fillMaxWidth()
        .padding(3.dp)
        .clip(RoundedCornerShape(3.dp))
        .background(colorResource(id = R.color.green_mid))) {
        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = crypto.name,

                Modifier
                    .padding(3.dp)
                    .defaultMinSize(50.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(3.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(colorResource(id = R.color.green_dark)),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green_dark))
            ) {
                Text(text = "Ссылка Binance", fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = { navController.navigate(Screen.SettingCrypto.route); vm.setCryptoChangeSetting(num)},
                modifier = Modifier
                    .padding(3.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(colorResource(id = R.color.green_dark)),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.green_dark))

            ) {
                Column() {
                    Text(text = "Настройки", fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center )
                    Text(text = "оповещений", fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center)
                }
            }
        }

    }
}