package com.example.cryptoinfocompose.Classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "botVariables")
data class BotVariables(
    @PrimaryKey
    var name:String = "variables",
    var botTimer:String = "120",
    var dayMod:Boolean = false,
    var nightMod:Boolean = false
)