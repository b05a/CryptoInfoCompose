package com.example.cryptoinfocompose.ViewModel

import android.os.Handler
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoinfocompose.Cases.AlertDialogCrypto
import com.example.cryptoinfocompose.Cases.RequestCrypto
import com.example.cryptoinfocompose.Cases.RequestCryptoBot
import com.example.cryptoinfocompose.Classes.BotVariables
import com.example.cryptoinfocompose.Classes.Crypto
import com.example.cryptoinfocompose.Repository.RepositoryRoom
import com.example.cryptoinfocompose.Repository.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(var storage: Storage, var alertDialogAdd: AlertDialogCrypto, var handler : Handler, var requestCrypto: RequestCrypto, var db: RepositoryRoom, var requestCryptoBot: RequestCryptoBot):
    ViewModel() {

    private var cryptoChangeSetting:Int = 0
    var listCrypto = mutableStateListOf<Crypto>()
    var botVariables = mutableStateOf(BotVariables())
    var testValue: MutableLiveData<String> = MutableLiveData()
    init {
//        listCrypto = storage.getListCrypto()
        viewModelScope.launch(Dispatchers.IO){
            listCrypto = db.getCryptoList()
            refreshListCrypto()
            botVariables = db.getBotVariable()
            Log.d("hello", botVariables.toString())
        }
    }
    fun setCryptoChangeSetting(name:Int){
        cryptoChangeSetting = name
    }
    fun getCryptoChangeSetting():Int{
        return cryptoChangeSetting
    }
    fun refreshListCrypto(){
        viewModelScope.launch {
            listCrypto = requestCrypto.getCryptoPrice(listCrypto)
            listCrypto[0] = listCrypto[0].copy(price = listCrypto[0].price+1)
        }
    }
    fun replacement(){
        viewModelScope.launch {
            listCrypto = requestCrypto.getCryptoPrice(listCrypto)
            listCrypto[0] = listCrypto[0].copy(price = listCrypto[0].price+1)

        }
    }

    fun changeSelectable(pos:Int) {
        listCrypto[pos] = listCrypto[pos].copy(selectable = !listCrypto[pos].selectable)
    }


    fun addCryptoToList(crypto: String) {
        for (i in listCrypto){
            if (i.name == crypto.toUpperCase()) return
        }
        viewModelScope.launch {
            val result = alertDialogAdd.alertDialogAddCrypto(crypto)
            if (result) {
                handler.post { listCrypto.add(Crypto(crypto.toUpperCase(), 0.0)) }
                testValue.value = "Добавлено"
//                if (storage.setCrypto(crypto.toUpperCase())) println("Set ${crypto.toUpperCase()}")
                db.setCrypto(Crypto(crypto.toUpperCase()))
            }
            if (!result) {
                handler.post { testValue.value = "Такой крипты нет" }
            }
        }

    }

    fun  delCryptoFromList(){
        val listDel = mutableListOf<Int>()
        for (i in 0 until listCrypto.count()) {
            Log.d("hello", i.toString())
            if (listCrypto[i].name == "BTC" && listCrypto[i].selectable) {
                Log.d("hello", "BTC")
                listCrypto[i] = listCrypto[i].copy(selectable = false)
            }
            if (listCrypto[i].selectable) {
                listDel.add(i)
            }
        }
        listDel.sortDescending()
        for (i in listDel){
//            storage.deleteCrypto(listCrypto[i].name)
            db.delCrypto(listCrypto[i])
            listCrypto.removeAt(i)
        }
    }

    fun changeNotification(num:Int) {
        listCrypto[num]=listCrypto[num].copy(notification = !listCrypto[num].notification)
        db.setCrypto(listCrypto[num])
    }

    fun changeNotUpB(num: Int) {
        listCrypto[num] = listCrypto[num].copy(notUpB = !listCrypto[num].notUpB)
        db.setCrypto(listCrypto[num])
    }

    fun changeDownB(num: Int) {
        listCrypto[num] = listCrypto[num].copy(notDownB = !listCrypto[num].notDownB)
        db.setCrypto(listCrypto[num])
    }

    fun changeNotUSDTB(num: Int) {
        listCrypto[num] = listCrypto[num].copy(notUSDTB = !listCrypto[num].notUSDTB)
        db.setCrypto(listCrypto[num])
    }

    fun changeNotPrcntB(num: Int) {
        listCrypto[num] = listCrypto[num].copy(notPrcntB = !listCrypto[num].notPrcntB)
        db.setCrypto(listCrypto[num])
    }

    fun changeNotUp(it: String) {
        try {
            it.toDouble()
        } catch (e: Exception) {
            if (it != "") return
        }
        viewModelScope.launch {
            listCrypto[getCryptoChangeSetting()] = listCrypto[getCryptoChangeSetting()].copy(notUp = it)
            db.setCrypto(listCrypto[getCryptoChangeSetting()])
        }
    }

    fun changeNotDown(it: String) {
        try {
            it.toDouble()
        } catch (e: Exception) {
            if (it != "") return
        }
        viewModelScope.launch {
            listCrypto[getCryptoChangeSetting()] = listCrypto[getCryptoChangeSetting()].copy(notDown = it)
            db.setCrypto(listCrypto[getCryptoChangeSetting()])
        }
    }

    fun notUSDT(it: String) {
        try {
            it.toDouble()
        } catch (e: Exception) {
            if (it != "") return
        }
        viewModelScope.launch {
            listCrypto[getCryptoChangeSetting()] = listCrypto[getCryptoChangeSetting()].copy(notUSDT = it)
            db.setCrypto(listCrypto[getCryptoChangeSetting()])
        }
    }

    fun notPrcnt(it: String) {
        try {
            it.toDouble()
            if (it.toDouble() > 100) return
        } catch (e: Exception) {
            if (it != "") return
        }
        viewModelScope.launch {
            listCrypto[getCryptoChangeSetting()] = listCrypto[getCryptoChangeSetting()].copy(notPrcnt = it)
            db.setCrypto(listCrypto[getCryptoChangeSetting()])
        }
    }

    fun botTimerChange(it: String) {
        var timer = it
        try {
            it.toDouble()
            if (it.toDouble() > 86400) timer = "86400"
            if (it.toDouble() < 20) timer = "20"
        } catch (e: Exception) {
            if (it != "") return
        }
        Log.d("hello", timer)
        botVariables.value = botVariables.value.copy(botTimer = timer)
        viewModelScope.launch {
            db.setBotVariable(botVariables.value)
        }
    }

    fun changeDayMod() {
        botVariables.value = botVariables.value.copy(dayMod = !botVariables.value.dayMod)
        viewModelScope.launch {
            db.setBotVariable(botVariables.value)
        }
    }

    fun changeNightMod() {
        botVariables.value = botVariables.value.copy(nightMod = !botVariables.value.nightMod)
        viewModelScope.launch {
            db.setBotVariable(botVariables.value)
        }
    }

    fun startBot(){
        requestCryptoBot.startBot(listCrypto, botVariables.value)
    }
    fun stopBot(){
        requestCryptoBot.stopBot()
    }
}