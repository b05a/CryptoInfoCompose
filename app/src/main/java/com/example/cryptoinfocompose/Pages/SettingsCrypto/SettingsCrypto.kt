package com.example.cryptoinfocompose.Pages.SettingsCrypto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cryptoinfocompose.MainActivity
import com.example.cryptoinfocompose.ViewModel.MainViewModel
import com.example.cryptoinfocompose.ui.theme.GreenLight
import com.example.cryptoinfocompose.ui.theme.GreenMid

@Composable
fun SettingsCrypto(navController: NavController, vm: MainViewModel, context: MainActivity) {
    val crypto = vm.listCrypto[vm.getCryptoChangeSetting()]
    val focus = LocalFocusManager.current
    Scaffold() {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .background(GreenLight)
                    .fillMaxSize()
            ) {
                Card(
                    backgroundColor = GreenMid, modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = crypto.name)
                        Switch(
                            checked = crypto.notification,
                            onCheckedChange = { vm.changeNotification(vm.getCryptoChangeSetting()) })
                    }
                }
                Card(
                    backgroundColor = GreenMid, modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "Оповещение при изменении цены")
                    }
                }
                Card(
                    backgroundColor = GreenLight, modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = crypto.name)
                        Text(text = "<")
                        Switch(
                            checked = crypto.notUpB,
                            onCheckedChange = { vm.changeNotUpB(vm.getCryptoChangeSetting()) })
                        TextField(
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            value = crypto.notUp,
                            onValueChange = { vm.changeNotUp(it) },
                            maxLines = 1,
                            keyboardActions = KeyboardActions(onDone = { focus.clearFocus() })
                        )
                    }
                }
                Card(
                    backgroundColor = GreenLight, modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = crypto.name)
                        Text(text = ">")
                        Switch(
                            checked = crypto.notDownB,
                            onCheckedChange = { vm.changeDownB(vm.getCryptoChangeSetting()) })
                        TextField(
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            value = crypto.notDown,
                            onValueChange = { vm.changeNotDown(it) },
                            maxLines = 1,
                            keyboardActions = KeyboardActions(onDone = { focus.clearFocus() })
                        )
                    }
                }
                Card(
                    backgroundColor = GreenMid, modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "Условия оповещения в USDT")
                    }
                }
                Card(
                    backgroundColor = GreenLight, modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "Шаг в USDT")
                        Switch(
                            checked = crypto.notUSDTB,
                            onCheckedChange = { vm.changeNotUSDTB(vm.getCryptoChangeSetting()) })
                        TextField(
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            value = crypto.notUSDT,
                            onValueChange = { vm.notUSDT(it) },
                            maxLines = 1,
                            keyboardActions = KeyboardActions(onDone = { focus.clearFocus() })
                        )
                    }
                }
                Card(
                    backgroundColor = GreenMid, modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "Условия оповещения в процентах")
                    }
                }
                Card(
                    backgroundColor = GreenLight, modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "Шаг в %")
                        Switch(
                            checked = crypto.notPrcntB,
                            onCheckedChange = { vm.changeNotPrcntB(vm.getCryptoChangeSetting()) })
                        TextField(
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            value = crypto.notPrcnt,
                            onValueChange = { vm.notPrcnt(it) },
                            maxLines = 1,
                            keyboardActions = KeyboardActions(onDone = { focus.clearFocus() })
                        )
                    }
                }
            }
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