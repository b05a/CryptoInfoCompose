package com.example.cryptoinfocompose.Repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptoinfocompose.Classes.Crypto
import androidx.room.Dao
import com.example.cryptoinfocompose.Classes.BotVariables

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: Crypto)

    @Query("SELECT * FROM cryptoItems")
    fun getAllItem(): List<Crypto>

    @Query("SELECT * FROM cryptoItems WHERE name = :nameFind")
    fun find(nameFind:String):List<Crypto>

    @Delete
    fun remove(crypto: Crypto)


    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertBotVariables(item:BotVariables)

    @Query("SELECT * FROM botVariables")
    fun getAllBotVariables(): List<BotVariables>

    @Query("SELECT * FROM botVariables WHERE name = :name")
    fun getVariable(name :String = "variables"): List<BotVariables>

    @Delete
    fun removeBotVariables(item:BotVariables)
}