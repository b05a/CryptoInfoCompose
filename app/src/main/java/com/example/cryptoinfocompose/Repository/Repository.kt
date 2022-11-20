package com.example.cryptoinfocompose.Repository

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.cryptoinfocompose.Classes.Crypto

class Storage(context: Context) {
    var cryptoName: SharedPreferences = context.getSharedPreferences("ListCrypto", Context.MODE_PRIVATE)
    var cryptoNameValues: SharedPreferences = context.getSharedPreferences("ListCryptoValues", Context.MODE_PRIVATE)
    fun getListCrypto(): SnapshotStateList<Crypto> {
        val list = cryptoName.all


        var listCrypto = SnapshotStateList<Crypto>()
        if (!list.contains("BTC")){
            listCrypto.add(Crypto("BTC", 0.0))
            setCrypto("BTC")
        }
        for (i in list){
            listCrypto.add(Crypto(i.value.toString(), 0.0))
        }
        return listCrypto
    }

    fun setCrypto(name: String):Boolean{
        cryptoName.edit().putString(name,name).apply()
        return true
    }

    fun deleteCrypto(name: String):Boolean{
        cryptoName.edit().remove(name).apply()
        return true
    }

    fun setValueListCrypto(name:String, value:Any):Boolean{
        return true
    }

    fun deleteValueListCrypto(name: String, value: Any):Boolean{

        return true
    }
}