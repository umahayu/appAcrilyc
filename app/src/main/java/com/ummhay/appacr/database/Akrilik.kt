package com.ummhay.appacr.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "acr")
data class Akrilik(

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "id_acr")
    val id_acrcr : Int,

    @ColumnInfo (name = "nama_acr")
    val nama_acr : String,

    @ColumnInfo (name = "harga_acr")
    val harga_acr : Int,

    @ColumnInfo (name = "ket_acr")
    val ket_acr : String
)
