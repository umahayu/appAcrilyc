package com.ummhay.appacr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ummhay.appacr.adapter.AdptrPsn
import com.ummhay.appacr.database.DATABASE
import com.ummhay.appacr.database.Pesanan
import com.ummhay.appacr.databinding.ActivityActDashBinding
import com.ummhay.appacr.databinding.ActivityActPesananBinding

class ActDash : AppCompatActivity() {

    private lateinit var find : ActivityActDashBinding
    private val db by lazy { DATABASE.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        find= ActivityActDashBinding.inflate(layoutInflater)
        setContentView(find.root)

        find.btnAcrD.setOnClickListener {
            startActivity(
                Intent(this, ActAkrilik::class.java)
            )
        }

        find.btnPsnD.setOnClickListener {
            startActivity(
                Intent(this, ActPesanan::class.java)
            )
        }
    }
}