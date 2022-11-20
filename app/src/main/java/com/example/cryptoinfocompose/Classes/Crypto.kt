package com.example.cryptoinfocompose.Classes

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cryptoItems")
data class Crypto(
    @PrimaryKey() var name: String,
    @ColumnInfo(name = "price") var price: Double = 0.0,
    @ColumnInfo(name = "pctHour") var pctHour:Double = 0.0,
    @ColumnInfo(name = "pctDay") var pctDay:Double = 0.0,
    @ColumnInfo(name = "pctBTCDay") var pctBTCDay:Double = 0.0,
    @ColumnInfo(name = "pctBTCHour") var pctBTCHour:Double = 0.0,
    @ColumnInfo(name = "selectable") var selectable:Boolean = false,

    var notification:Boolean = false,

    var notUpB:Boolean = false,
    var notUp:String = "0",

    var notDownB:Boolean = false,
    var notDown:String = "0",

    var notUSDTB:Boolean = false,
    var notUSDT:String = "0",
    var banValue:String = "0",

    var notPrcntB:Boolean = false,
    var notPrcnt:String = "0",
    var banValuePrcnt:String = "0"
)