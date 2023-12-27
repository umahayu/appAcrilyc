package com.ummhay.appacr

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.ummhay.appacr.adapter.AdptrAcr
import com.ummhay.appacr.adapter.AdptrPsn
import com.ummhay.appacr.database.Akrilik
import com.ummhay.appacr.database.DATABASE
import com.ummhay.appacr.database.Pesanan
import com.ummhay.appacr.databinding.ActivityActAkrilikBinding
import com.ummhay.appacr.databinding.ActivityActPesananBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActPesanan : AppCompatActivity() {
    private lateinit var find : ActivityActPesananBinding
    private lateinit var adapter : AdptrPsn
    private val db by lazy { DATABASE.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        find= ActivityActPesananBinding.inflate(layoutInflater)
        setContentView(find.root)

        adapter = AdptrPsn(arrayListOf(),
            object : AdptrPsn.onPsn{
                override fun hapusPsn(pesanan: Pesanan) {
                    hpsPesanan(pesanan)
                }
            }
        )

        find.btnAcrP.setOnClickListener {
            startActivity(
                Intent(this, ActAkrilik::class.java)
            )
        }

        find.btnDashP.setOnClickListener {
            startActivity(
                Intent(this, ActPesanan::class.java)
            )
        }

        find.tmbhPsnan.setOnClickListener {
            startActivity(
                Intent(this, InputPesanan::class.java)
            )
        }
    }

    private fun hpsPesanan(pesanan: Pesanan) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("konfirmasi hapus data")
            setMessage("Apakah anda yakin akan menghapus data ${pesanan.nama_psn}?")
            setNegativeButton("Batal") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            setPositiveButton("hapus") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()

                CoroutineScope(Dispatchers.IO).launch {
                    db.dao().DeletePesanan(pesanan)
                }
            }
            dialog.show()
        }
    }

    override fun onResume() {
        super.onResume()
        tampilDataPsn()
    }

    fun tampilDataPsn() {
        find.lisPsn.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.dao().getAllPesanan()
            adapter.setDataPsn(data)
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
                find.txtSearchPsn.visibility = View.GONE
            }
        }
        find.lisPsn.adapter = adapter
    }
}