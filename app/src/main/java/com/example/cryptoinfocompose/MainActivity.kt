package com.example.cryptoinfocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cryptoinfocompose.NavControl.NavStart
import com.example.cryptoinfocompose.ViewModel.MainViewModel
import com.example.cryptoinfocompose.ui.theme.CryptoInfoComposeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    val vm: MainViewModel by viewModel()
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            CryptoInfoComposeTheme {
                NavStart(navController = navController, vm, this@MainActivity)
            }
        }
    }
}