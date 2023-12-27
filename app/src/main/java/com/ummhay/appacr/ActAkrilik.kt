package com.ummhay.appacr

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ummhay.appacr.adapter.AdptrAcr
import com.ummhay.appacr.database.Akrilik
import com.ummhay.appacr.database.DATABASE
import com.ummhay.appacr.databinding.ActivityActAkrilikBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActAkrilik : AppCompatActivity() {

    private lateinit var find : ActivityActAkrilikBinding
    private lateinit var adapter : AdptrAcr
    private val db by lazy { DATABASE.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        find= ActivityActAkrilikBinding.inflate(layoutInflater)
        setContentView(find.root)

        adapter = AdptrAcr(arrayListOf(),
            object : AdptrAcr.onAcr{
                override fun hapusacr(akrilik: Akrilik) {
                    hpsAcrilik(akrilik)
                }
            }
        )

        find.btnDashA.setOnClickListener {
            startActivity(
                Intent(this, ActDash::class.java)
            )
        }

        find.btnPsnA.setOnClickListener {
            startActivity(
                Intent(this, ActPesanan::class.java)
            )
        }

        find.tmbhAcr.setOnClickListener {
            startActivity(
                Intent(this, InputAkrilik::class.java)
            )
        }
    }

    private fun hpsAcrilik(akrilik: Akrilik) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("konfirmasi hapus data")
            setMessage("Apakah anda yakin akan menghapus data ${akrilik.nama_acr}?")
            setNegativeButton("Batal") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            setPositiveButton("hapus") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()

                CoroutineScope(Dispatchers.IO).launch {
                    db.dao().DltAcr(akrilik)
                }
            }
            dialog.show()
        }
    }

    override fun onResume() {
        super.onResume()
        tampilDataAcr()
    }

    fun tampilDataAcr() {
        find.listAcr.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val dataA = db.dao().getAllAcr()
            adapter.setDataAcr(dataA)
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
                find.txtSearchAcr.visibility = View.GONE
            }
        }
        find.listAcr.adapter = adapter
    }

}