package com.example.cryptoinfocompose.Pages.Main

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cryptoinfocompose.MainActivity
import com.example.cryptoinfocompose.NavControl.Screen
import com.example.cryptoinfocompose.ViewModel.MainViewModel
import com.example.cryptoinfocompose.ui.theme.GreenLight
import com.example.cryptoinfocompose.ui.theme.GreenMid
import com.example.cryptoinfocompose.ui.theme.RedLight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun MainPage(vm: MainViewModel, context: MainActivity, navController: NavController) {
    val dialog = remember { mutableStateOf(false) }
    val state = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = state,
        topBar = {
            TopAppBar(
                title = {
                    IconButton(onClick = {
                        scope.launch {
                            state.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                    Text(text = "CryptoInfo")
                }
            )

        },
        drawerBackgroundColor = GreenLight,
        drawerContent = {
            Column(Modifier.padding(7.dp)) {
                Button(
                    onClick = { scope.launch { state.drawerState.close() } },
                    Modifier.fillMaxWidth()
                ) {
                    Text(text = "Главная")
                }
                Button(onClick = {
                    navController.navigate(Screen.SettingsListPage.route)
                    scope.launch { state.drawerState.close() }
                }, Modifier.fillMaxWidth()) {
                    Text(text = "Настройки уведомлений")
                }
                Button(
                    onClick = {
                        navController.navigate(Screen.BotPage.route)
                        scope.launch { state.drawerState.close() }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Крипто бот")
                }
            }

        },
        content = {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(GreenLight)
            ) {
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .clickable { }) {
                    itemsIndexed(vm.listCrypto) { possition, it ->
                        ListCryptoItem(crypto = it, possition) { vm.changeSelectable(possition) }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                ) {
                    FloatingActionButton(
                        onClick = {
                            vm.replacement()
                        },
                        backgroundColor = GreenMid,
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(2f)

                    ) {
                        Text(text = "Обновить", Modifier.padding(10.dp))
                    }
                    FloatingActionButton(
                        onClick = {
                            if (vm.listCrypto.isEmpty()) return@FloatingActionButton;

                            vm.delCryptoFromList()
                        },
                        backgroundColor = RedLight,
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(1f)

                    ) {
                        Text(text = "Удалить", Modifier.padding(10.dp))
                    }
                    FloatingActionButton(
                        onClick = { dialog.value = true },
                        backgroundColor = GreenMid,
                        modifier = Modifier
                            .padding(5.dp)
                            .width(intrinsicSize = IntrinsicSize.Min)
                    ) {
                        Text(text = "Добавить", Modifier.padding(10.dp), maxLines = 1)
                    }

                    if (dialog.value) {
                        val message = remember { mutableStateOf("") }
                        vm.testValue.observe(context) {
                            message.value = it
                        }
                        AlertDialog(
                            onDismissRequest = { dialog.value = false },
                            title = { Text(text = "Введите название крипты в формате BTC") },
                            confirmButton = {
                                Column(Modifier.fillMaxWidth(), Arrangement.Center) {
                                    BasicTextField(
                                        value = message.value,
                                        onValueChange = { message.value = it },
                                        Modifier
                                            .border(
                                                BorderStroke(
                                                    1.dp,
                                                    GreenLight
                                                )
                                            )
                                            .fillMaxWidth()
                                            .height(45.dp),
                                        textStyle = TextStyle(fontSize = 25.sp)
                                    )
                                    Row(
                                        Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Button(
                                            onClick = {
                                                vm.addCryptoToList(message.value)
                                            },
                                            Modifier
                                                .fillMaxWidth()
                                                .weight(1f)
                                        ) {
                                            Text(text = "Добавить")
                                        }
                                        Button(
                                            onClick = {
                                                dialog.value = false; vm.testValue.value = ""
                                            },
                                            Modifier
                                                .fillMaxWidth()
                                                .weight(1f)
                                        ) {
                                            Text(text = "Отмена")
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        })
}