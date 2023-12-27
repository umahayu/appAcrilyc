package com.ummhay.appacr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ummhay.appacr.database.DATABASE
import com.ummhay.appacr.databinding.ActivityDetailAkrilikBinding
import com.ummhay.appacr.databinding.ActivityDetailPesananBinding

class DetailPesanan : AppCompatActivity() {

    private lateinit var find : ActivityDetailPesananBinding
    private val db by lazy { DATABASE.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        find= ActivityDetailPesananBinding.inflate(layoutInflater)
        setContentView(find.root)

        find.backPsn.setOnClickListener {
            startActivity(
                Intent(this, ActPesanan::class.java)
            )
        }

        var id = intent.getStringExtra("idPsn").toString().toInt()
        val data = db.dao().getIDPesanan(id)[0]
        val dataAcr = db.dao().getAcrByID(data.id_psn)[0]
        val total = data.jmlh_psn * dataAcr.harga_acr

        find.dtNama.setText(data.nama_psn)
        find.dtJnsAcr.setText(data.jns_psn)
        find.dtJmlh.setText(data.jmlh_psn.toString())
        find.dtAlmt.setText(data.almt_psn)
        find.hrgTtl.setText(total.toString())
    }
}