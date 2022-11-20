package com.example.cryptoinfocompose.Repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.cryptoinfocompose.Classes.BotVariables
import com.example.cryptoinfocompose.Classes.Crypto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryRoom( var db: MainDb) {
    fun setCrypto(crypto: Crypto){
        CoroutineScope(Dispatchers.IO).launch {
            db.getDao().insertItem(crypto)
        }
    }

    fun delCrypto(crypto: Crypto){
        CoroutineScope(Dispatchers.IO).launch {
            db.getDao().remove(crypto)
        }
    }

    fun findCrypto(crypto: String):List<Crypto>{
        return db.getDao().find(crypto)
    }

    fun getCryptoList(): SnapshotStateList<Crypto> {
        val list = mutableStateListOf<Crypto>()
        list.add(Crypto("BTC"))
        for (i in db.getDao().getAllItem()){
            if (i.name == "BTC") {list[0] = i ; continue}
            list.add(i)
        }
        for (i in list){
            Log.d("hello", i.toString())
        }
        return list
    }

    fun setBotVariable(item: BotVariables){
        CoroutineScope(Dispatchers.IO).launch {
            db.getDao().insertBotVariables(item)
        }
    }

    fun delBotVariable(item: BotVariables){
        CoroutineScope(Dispatchers.IO).launch {
            db.getDao().removeBotVariables(item)
        }
    }

    fun getBotVariable(): MutableState<BotVariables> {
        var i = db.getDao().getVariable()
        if (i.isEmpty()) {
            db.getDao().insertBotVariables(BotVariables())
        }
        i = db.getDao().getVariable()
        return mutableStateOf(i[0])
    }
}