package com.example.cryptoinfocompose.Cases

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.cryptoinfocompose.Classes.Crypto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.json.JSONObject
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.URL

class RequestCrypto (var context: Context) {
    suspend fun getCryptoPrice(list: SnapshotStateList<Crypto>): SnapshotStateList<Crypto> {
        var btcPctHour = 0.0
        var btcPctDay = 0.0
        var listRequest = ""
        for (i in list){
            listRequest+="${i.name},"
        }
        Log.d("hello", listRequest)
        val result = CoroutineScope(Dispatchers.IO).async {
            try {
                return@async URL("https://min-api.cryptocompare.com/data/pricemultifull?fsyms=$listRequest&tsyms=USDT").readText()
            }catch (e:Exception){return@async ""}
        }.await()
        if (result == "") {
            Toast.makeText(context, "Нет интернета", Toast.LENGTH_LONG).show()
            return list
        }

        for (i in list){
            i.price =
                JSONObject(result).getJSONObject("RAW").getJSONObject(i.name).getJSONObject("USDT").getString("PRICE").let {  BigDecimal(it).setScale(2, RoundingMode.HALF_EVEN) }.toDouble()
            i.pctHour = JSONObject(result).getJSONObject("RAW").getJSONObject(i.name).getJSONObject("USDT").getDouble("CHANGEPCTHOUR").let {  BigDecimal(it).setScale(2, RoundingMode.HALF_EVEN) }.toDouble()
            i.pctDay = JSONObject(result).getJSONObject("RAW").getJSONObject(i.name).getJSONObject("USDT").getDouble("CHANGEPCT24HOUR").let {  BigDecimal(it).setScale(2, RoundingMode.HALF_EVEN) }.toDouble()
            if (i.name == "BTC") {
                btcPctHour = i.pctHour
                btcPctDay = i.pctDay
                continue
            }
            i.pctBTCDay = (i.pctDay - btcPctDay).let {  BigDecimal(it).setScale(2, RoundingMode.HALF_EVEN) }.toDouble()
            i.pctBTCHour = (i.pctHour - btcPctHour).let {  BigDecimal(it).setScale(2, RoundingMode.HALF_EVEN) }.toDouble()
        }

        return list
    }
}