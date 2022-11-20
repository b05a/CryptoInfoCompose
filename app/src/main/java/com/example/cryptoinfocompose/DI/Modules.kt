package com.example.cryptoinfocompose.DI

import android.os.Handler
import android.os.Looper
import com.example.cryptoinfocompose.Cases.AlertDialogCrypto
import com.example.cryptoinfocompose.Cases.RequestCrypto
import com.example.cryptoinfocompose.Cases.RequestCryptoBot
import com.example.cryptoinfocompose.Repository.MainDb
import com.example.cryptoinfocompose.Repository.RepositoryRoom
import com.example.cryptoinfocompose.Repository.Storage
import com.example.cryptoinfocompose.ViewModel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get(),get(),get(), get(), get(), get()) }
    single <Storage> { Storage(androidContext()) }
    single <AlertDialogCrypto> { AlertDialogCrypto() }
    single <Handler> { Handler(Looper.getMainLooper()) }
    single <RequestCrypto> { RequestCrypto(get()) }
    single <MainDb> { MainDb.getDb(androidContext()) }
    single <RepositoryRoom>{ RepositoryRoom(get()) }
    single <RequestCryptoBot>{ RequestCryptoBot(androidContext()) }
}