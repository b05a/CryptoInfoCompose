package com.example.cryptoinfocompose.NavControl

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptoinfocompose.MainActivity
import com.example.cryptoinfocompose.Pages.BotPage.BotPage
import com.example.cryptoinfocompose.Pages.Main.MainPage
import com.example.cryptoinfocompose.Pages.SettingsCrypto.SettingsCrypto
import com.example.cryptoinfocompose.Pages.SettingsListPage
import com.example.cryptoinfocompose.ViewModel.MainViewModel

@Composable
fun NavStart(navController: NavHostController, vm: MainViewModel, context: MainActivity){
    NavHost(navController = navController, startDestination = Screen.MainScreen.route ){
        composable(Screen.MainScreen.route){
            MainPage(vm, context, navController)
        }
        composable(Screen.SettingsListPage.route){
            SettingsListPage(vm,context,navController)
        }
        composable(Screen.SettingCrypto.route){
            SettingsCrypto(navController,vm,context)
        }
        composable(Screen.BotPage.route){
            BotPage(vm = vm, context = context, navController = navController)
        }
    }
}