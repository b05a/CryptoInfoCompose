package com.example.cryptoinfocompose.Cases

import android.content.Context
import android.media.MediaPlayer
import com.example.cryptoinfocompose.Classes.BotVariables
import com.example.cryptoinfocompose.Classes.Crypto
import com.example.cryptoinfocompose.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

class RequestCryptoBot(var context: Context) {
    // Переменные для выхода из цикла программы
    var d = ""
    var e = "{\"BTC\":{\"USD\":41284.5},\"LTC\":{\"USD\":115.35},\"ETH\":{\"USD\":2893.35}}"

    var listRequestCrypto=""

    // Создаем веб-клиент
    lateinit var i: URL

    var delenie:Double=0.0

    // Создаем переменные отслеживающие значения банов в циклах поиска
    var banValueRUB = 0.0

    // Переменная подсчета цикла рубля
    var cycleRUB = 0

    // Запрос цены рубля
    var responseRUB:String=""

    // Цена рубля
    var priceRUB=0.0

    // Шаг цены при котором происходит уведомление
    var stepValueRUB = 4.0

    // переменная первого старта
    var firstStart=true


    // создеем ответ боту
    var botResponse= URL("https://api.telegram.org/bot1164009224:AAEnk7ZM3ckaBZGLLC33X7ngQzjbLisPqEI/sendMessage?chat_id=771071943&text=Hello")
    private var mediaPlayer: MediaPlayer =MediaPlayer.create(context, R.raw.budinik)

    fun startBot(listCrypto: List<Crypto>, variables: BotVariables){


        d = ""
        listRequestCrypto = ""
        for (i in listCrypto){
            if (i.notification){
                listRequestCrypto += "${i.name},"
            }
        }
        listRequestCrypto = listRequestCrypto.substring(0, listRequestCrypto.length - 1)
        // Создаем веб-клиент
        i= URL("https://min-api.cryptocompare.com/data/pricemulti?fsyms=$listRequestCrypto&tsyms=USD")

        CoroutineScope(Dispatchers.IO).launch {

            // отправляем в телеграмм сообщение о запуске приложения
            try {
                URL("https://api.telegram.org/bot1164009224:AAEnk7ZM3ckaBZGLLC33X7ngQzjbLisPqEI/sendMessage?chat_id=771071943&text=Phone start Apps for $listRequestCrypto").readText()
            }
            catch (e:Exception){
                println("Нет интернета")
            }
        }

        println("Служба запущена")

        // метод запросов и отправки сообщений в чат
        messageToChatAndRequest(listCrypto, variables)
    }
    // метод запросов и отправки сообщений в чат
    private fun messageToChatAndRequest(listCrypto: List<Crypto>, botVariable: BotVariables) {

        CoroutineScope(Dispatchers.IO).launch {

            // повторяется цикл пока переменная d не будет равна "Выход"
            while (d != "Выход") {
                // отправляем запрос о цене крипты
                try {
                    e = i.readText()
                } catch (e: Exception) {
                    // если запрос не получился останавливаем корутину на 2 секунди и переходим к следующему циклу
                    delay(2000)
                    continue
                }

                // считываем из json цену крипты
                for (i in listCrypto){
                    if (i.notification){
                        i.price= JSONObject(e).getJSONObject(i.name).getDouble("USD")
                    }
                }

                // если это первый старт, то в любом случае отправляем цену в чат
                if (firstStart) {
                    try {
                        for (i in listCrypto){
                            if (i.price!=null){
                                URL("https://api.telegram.org/bot1164009224:AAEnk7ZM3ckaBZGLLC33X7ngQzjbLisPqEI/sendMessage?chat_id=771071943&text=${i.name} ${i.price} phone").readText()
                            }
                        }

                    } catch (e: Exception) {

                    }
                    // отмечаем в переменной, что первый запуск произошел
                    firstStart = false
                }

                // перебираем крипту и отправляем сообщения
                for (i in listCrypto){
                    // если цена равна нулю то пропускаем этот цикл
                    if (i.price == null) continue
                    println("i.price ${i.price}")
                    println("i.notDown ${i.notDown}")
                    println("i.notDownB ${i.notDownB}")
                    println("i.notification ${i.notification}")
                    println("botVariable.nightMod ${botVariable.nightMod}")
                    // выполняем условия для ночного режима при условии что цена ниже минимальной цены оповещения
                    if (i.price>i.notDown.toDouble() && i.notDownB && i.notification && botVariable.nightMod){
                        println("Цена ниже")
                        // запускаем будильник
                        launch {
                            mediaPlayer.start()
                            delay(10000)
                            mediaPlayer.stop()
                            println("Hello")
                            mediaPlayer.prepareAsync()
                        }

                        // отправляем сообщение
                        try {
                            URL("https://api.telegram.org/bot1164009224:AAEnk7ZM3ckaBZGLLC33X7ngQzjbLisPqEI/sendMessage?chat_id=771071943&text=${i.name} ${i.price} ниже ${i.notDown} phone").readText()
                        }
                        catch (e:Exception){
                            delay(2000)
                            continue
                        }
                    }
                    println("i.price ${i.price}")
                    println("i.notUp ${i.notUp}")
                    println("i.notUpB ${i.notUpB}")
                    println("i.notification ${i.notification}")
                    println("botVariable.nightMod ${botVariable.nightMod}")
                    // выполняем условия для ночного режима при условии что цена выше максимальной цены оповещения
                    if (i.price<i.notUp.toDouble() && i.notUpB && i.notification && botVariable.nightMod){
                        println("цена выше")
                        // запускаем будильник
                        launch {
                            mediaPlayer.start()
                            delay(10000)
                            mediaPlayer.stop()
                            mediaPlayer.prepareAsync()
                        }

                        // отправляем сообщение
                        try {
                            URL("https://api.telegram.org/bot1164009224:AAEnk7ZM3ckaBZGLLC33X7ngQzjbLisPqEI/sendMessage?chat_id=771071943&text=${i.name} ${i.price} превысил ${i.notUp} phone").readText()
                        }
                        catch (e:Exception){
                            delay(2000)
                            continue
                        }
                    }

                    // получаем коэфициент для сравнения с предидущим значением бана
                    delenie=Math.floor(i.price/i.notUSDT.toDouble())
                    // проверяем условия для дневного режима с фиксированным шагом цены
                    if (i.banValue.toDouble() != delenie && botVariable.dayMod && i.notUSDTB && i.notification){
                        try {
                            // Выводим сообщение в телеграм
                            URL("https://api.telegram.org/bot1164009224:AAEnk7ZM3ckaBZGLLC33X7ngQzjbLisPqEI/sendMessage?chat_id=771071943&text=${i.name} ${i.price} phone").readText()
                        } catch (e: Exception) {
                            delay(2000)
                            continue
                        }
                        // присваиваем значение коэф. деления значению переменной бана
                        i.banValue=delenie.toString()
                        println("${i.name} Day mod")
                    }

                    // получаем коэфициент для сравнения с предидущим значением бана
                    delenie=Math.abs(1.0 - (i.price/i.banValuePrcnt.toDouble()))*100
                    // проверяем условия для дневного режима с фиксированным шагом цены
                    if (delenie>i.notPrcnt.toDouble() && i.notPrcntB && i.notification && botVariable.dayMod){
                        try {
                            // Выводим сообщение в телеграм
                            URL("https://api.telegram.org/bot1164009224:AAEnk7ZM3ckaBZGLLC33X7ngQzjbLisPqEI/sendMessage?chat_id=771071943&text=${i.name} ${i.price} phone prcnt").readText()
                        } catch (e: Exception) {
                            delay(2000)
                            continue
                        }
                        // присваиваем значение коэф. деления значению переменной бана
                        i.banValuePrcnt=i.price.toString()
                    }
                }



                // Запрос рубля раз в 30 минут
                if (cycleRUB == 15 || cycleRUB == 0)
                {
                    try {
                        // Http запрос
                        responseRUB= URL("https://min-api.cryptocompare.com/data/pricemulti?fsyms=USDT&tsyms=RUB").readText()
                    } catch (e: Exception) {
                        delay(2000)
                        continue
                    }

                    // Серилизация запроса в класс
                    priceRUB = JSONObject(responseRUB).getJSONObject("USDT").getDouble("RUB")

                    // Вычисление коэфициента бана
                    delenie = Math.floor(priceRUB / stepValueRUB)

                    // Если результат деления не равен величине бана и есть видимость рубля
                    if (banValueRUB!=delenie)
                    {
                        try {
                            // Отправляем цену рубля в чат
                            URL("https://api.telegram.org/bot1164009224:AAEnk7ZM3ckaBZGLLC33X7ngQzjbLisPqEI/sendMessage?chat_id=771071943&text=RUB $priceRUB phone").readText()

                        }catch (e: Exception) {
                            delay(2000)
                            continue
                        }

                        // Присвоение значения бана
                        banValueRUB = delenie;

                    }

                    // Обнуляем цикл
                    cycleRUB = 0;
                    println("Цена рубля $priceRUB")
                }

                cycleRUB++;

                println(botVariable.botTimer)

                // приостановка корутины на период запроса
                delay(botVariable.botTimer.toLong() * 1000)
            }

            // отправляем сообщение о том что сервис приостанавливает работу
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    URL("https://api.telegram.org/bot1164009224:AAEnk7ZM3ckaBZGLLC33X7ngQzjbLisPqEI/sendMessage?chat_id=771071943&text=Phone stop Apps").readText()
                } catch (e: Exception) {
                }

            }
        }
    }
    fun stopBot(){
        d = "Выход"
    }
}