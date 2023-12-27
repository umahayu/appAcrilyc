package com.ummhay.appacr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ummhay.appacr.database.Akrilik
import com.ummhay.appacr.database.DATABASE
import com.ummhay.appacr.databinding.ActivityInputAkrilikBinding

class InputAkrilik : AppCompatActivity() {

    private lateinit var find : ActivityInputAkrilikBinding
    private val db by lazy { DATABASE.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        find= ActivityInputAkrilikBinding.inflate(layoutInflater)
        setContentView(find.root)

        find.backAcr.setOnClickListener{onBackPressed()}

        var id = intent.getStringExtra("idAcr")

        if (id == null){
            modeTambah()

        }else {
            modeEdit(id.toString().toInt())
        }

        find.btnTmbhAcr.setOnClickListener {

            if (find.inpNmaAcr.text.isNotEmpty() &&
                find.inpHrgAcr.text.isNotEmpty()
            ){
                try {
                    db.dao().InsrtAcr(
                        Akrilik(0,
                            find.inpNmaAcr.text.toString(),
                            find.inpHrgAcr.text.toString().toInt(),
                            find.inpKtrAcr.text.toString()
                        )
                    )
                    onBackPressed()
                    alert("Data berhasil ditambahkan")

                } catch (e : Exception){
                    alert("Akrilik tersebut sudah tersedia")
                }

            }else{
                alert("Isi data terlebih dahulu")
            }
        }

        find.btnUpdateAcr.setOnClickListener {
            if (find.inpNmaAcr.text.isNotEmpty() &&
                find.inpHrgAcr.text.isNotEmpty()
            ){
                try {
                    db.dao().UpdteAcr(
                        Akrilik(
                        id.toString().toInt(),
                            find.inpNmaAcr.text.toString(),
                            find.inpHrgAcr.text.toString().toInt(),
                            find.inpKtrAcr.text.toString()
                    )
                    )
                    onBackPressed()
                    alert("Data berhasil diubah")

                } catch (e : Exception) {
                    alert("Nama tersebut sudah tersedia")
                }

            }else{
                alert("Ubah data terlebih dahulu")
            }

        }

    }

    private fun alert(pesan: String) {
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show()
    }

    private fun modeEdit(id:Int) {
        find.btnTmbhAcr.visibility = View.GONE
        find.hdngAcr.text="Edit Akrilik"

        val dataGanci = db.dao().getAcrByID(id)[0]
        find.inpNmaAcr.setText(dataGanci.nama_acr)
        find.inpHrgAcr.setText(dataGanci.harga_acr.toString())
    }

    private fun modeTambah() {
        find.btnUpdateAcr.visibility = View.GONE
        find.hdngAcr.text="Tambah Akrilik"
    }
}