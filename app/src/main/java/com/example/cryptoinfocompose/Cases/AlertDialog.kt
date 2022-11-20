package com.example.cryptoinfocompose.Cases

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

class AlertDialogCrypto{
    suspend fun alertDialogAddCrypto(name:String):Boolean {
        return CoroutineScope(Dispatchers.IO).async {
            try {
                val request = URL("https://min-api.cryptocompare.com/data/price?fsym=$name&tsyms=USD").readText()
                var result = JSONObject(request).get("USD")
                return@async true
            } catch (e: Exception) {
                return@async false
            }
        }.await()
    }
}
