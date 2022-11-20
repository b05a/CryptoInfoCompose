package com.example.cryptoinfocompose.Pages.BotPage

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cryptoinfocompose.MainActivity
import com.example.cryptoinfocompose.NavControl.Screen
import com.example.cryptoinfocompose.Services.BotService
import com.example.cryptoinfocompose.ViewModel.MainViewModel
import com.example.cryptoinfocompose.ui.theme.GreenLight
import com.example.cryptoinfocompose.ui.theme.GreenMid

@Composable
fun BotPage(vm: MainViewModel, context: MainActivity, navController: NavController) {
    val focus = LocalFocusManager.current
    Scaffold(backgroundColor = GreenLight, modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(Modifier.padding(horizontal = 10.dp, vertical = 60.dp)) {
                Text(text = "Период проверки крипты,\n по умолчанию 2 минуты", Modifier.padding(7.dp))
                TextField(
                    value = vm.botVariables.value.botTimer,
                    onValueChange = {vm.botTimerChange(it)},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { focus.clearFocus() })
                )
            }
            Button(
                onClick = { navController.navigate(Screen.SettingsListPage.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text(text = "Настройки оповещений", fontSize = 20.sp)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Дневной режим", fontSize = 18.sp, textAlign = TextAlign.Center)
                Switch(
                    checked = vm.botVariables.value.dayMod,
                    onCheckedChange = { vm.changeDayMod() })
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Ночной режим", fontSize = 18.sp, textAlign = TextAlign.Center)
                Switch(
                    checked = vm.botVariables.value.nightMod,
                    onCheckedChange = { vm.changeNightMod() })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { vm.stopBot() }, Modifier.padding(vertical = 50.dp, horizontal = 20.dp)) {
                    Text(text = "СТОП СЕРВИС")
                }
                Button(onClick = { vm.startBot() }, Modifier.padding(20.dp)) {
                    Text(text = "СТАРТ СЕРВИС")
                }
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingActionButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(7.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomStart),
                backgroundColor = GreenMid
            ) {
                Text(text = "Назад", fontSize = 20.sp)
            }
        }
    }
}