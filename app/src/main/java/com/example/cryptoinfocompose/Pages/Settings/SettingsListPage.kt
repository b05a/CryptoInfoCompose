package com.example.cryptoinfocompose.Pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cryptoinfocompose.MainActivity
import com.example.cryptoinfocompose.NavControl.Screen
import com.example.cryptoinfocompose.R
import com.example.cryptoinfocompose.ViewModel.MainViewModel
import com.example.cryptoinfocompose.ui.theme.GreenLight
import com.example.cryptoinfocompose.ui.theme.GreenMid
import kotlinx.coroutines.launch

@Composable
fun SettingsListPage(vm: MainViewModel, context: MainActivity, navController: NavController){
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
                    onClick = { navController.popBackStack() },
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
            }

        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.green_light))
            ){
                LazyColumn(modifier = Modifier.fillMaxSize()){
                    itemsIndexed(vm.listCrypto){ num, it ->
                        SettingItemList(num, it, vm, navController)
                    }

                }
                FloatingActionButton(onClick = { navController.popBackStack() },
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
    )
}