package com.ummhay.appacr.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "psn")
data class Pesanan(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "id_psn")
    val id_psn : Int,

    @ColumnInfo (name = "nama_psn")
    val nama_psn : String,

    @ColumnInfo (name = "jenis_psn")
    val jns_psn : String,

    @ColumnInfo (name = "jumlah_psn")
    val jmlh_psn : Int,

    @ColumnInfo (name = "alamat_psn")
    val almt_psn : String
)
