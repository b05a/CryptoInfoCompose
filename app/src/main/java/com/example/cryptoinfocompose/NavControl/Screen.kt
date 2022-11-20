package com.example.cryptoinfocompose.NavControl

sealed class Screen(val route:String) {
    object MainScreen:Screen("Main")
    object SettingsListPage:Screen("Settings")
    object SettingCrypto:Screen("CryptoSettings")
    object BotPage:Screen("BotPage")
}