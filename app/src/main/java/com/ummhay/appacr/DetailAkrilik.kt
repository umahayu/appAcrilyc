package com.ummhay.appacr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ummhay.appacr.database.DATABASE
import com.ummhay.appacr.databinding.ActivityDetailAkrilikBinding

class DetailAkrilik : AppCompatActivity() {

    private lateinit var find : ActivityDetailAkrilikBinding
    private val db by lazy { DATABASE.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        find= ActivityDetailAkrilikBinding.inflate(layoutInflater)
        setContentView(find.root)

        find.backAcr.setOnClickListener {
            startActivity(
                Intent(this, ActAkrilik::class.java)
            )
        }

        val id = intent.getStringExtra("idAcr").toString().toInt()
        val data = db.dao().getAcrByID(id)[0]

        find.dtNamaAcr.setText(data.nama_acr)
        find.hrgAcr.setText(data.harga_acr.toString())
        find.dtKtr.setText(data.ket_acr)
    }
}