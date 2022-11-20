package com.example.cryptoinfocompose.Pages.Main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cryptoinfocompose.Classes.Crypto
import com.example.cryptoinfocompose.R


@Composable
fun ListCryptoItem(crypto: Crypto, possition:Int, change:(Int)->Unit) {
    Box(
        modifier = Modifier
            .clickable { change(possition) }
            .fillMaxWidth()
            .padding(3.dp)
            .clip(RoundedCornerShape(3.dp))
            .background(
                colorResource(
                    id = if (!crypto.selectable) {
                        R.color.green_mid
                    } else {
                        R.color.red_light
                    }
                )
            )
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Text(
                text = crypto.name,
                Modifier
                    .padding(3.dp)
                    .defaultMinSize(50.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = crypto.price.toString(),
                Modifier
                    .padding(3.dp)
                    .defaultMinSize(60.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = crypto.pctHour.toString(),
                Modifier
                    .defaultMinSize(45.dp)
                    .padding(3.dp)
                    .background(
                        if (crypto.pctHour >= 0) {
                            colorResource(id = R.color.green_light)
                        } else {
                            colorResource(id = R.color.red_light)
                        }
                    ),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = crypto.pctDay.toString(),
                Modifier
                    .defaultMinSize(45.dp)
                    .padding(3.dp)
                    .background(
                        if (crypto.pctDay >= 0) {
                            colorResource(id = R.color.green_light)
                        } else {
                            colorResource(id = R.color.red_light)
                        }
                    ),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = crypto.pctBTCDay.toString(),
                Modifier
                    .defaultMinSize(45.dp)
                    .padding(3.dp)
                    .background(
                        if (crypto.pctBTCDay >= 0) {
                            colorResource(id = R.color.green_light)
                        } else {
                            colorResource(id = R.color.red_light)
                        }
                    ),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = crypto.pctBTCHour.toString(),
                Modifier
                    .defaultMinSize(45.dp)
                    .padding(3.dp)
                    .background(
                        if (crypto.pctBTCHour >= 0) {
                            colorResource(id = R.color.green_light)
                        } else {
                            colorResource(id = R.color.red_light)
                        }
                    ),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}