package com.ummhay.appacr

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ummhay.appacr.database.Akrilik
import com.ummhay.appacr.database.DATABASE
import com.ummhay.appacr.database.Pesanan
import com.ummhay.appacr.databinding.ActivityInputAkrilikBinding
import com.ummhay.appacr.databinding.ActivityInputPesananBinding

class InputPesanan : AppCompatActivity() {

    private lateinit var find : ActivityInputPesananBinding
    private val db by lazy { DATABASE.getInstance(this) }
    private lateinit var selectedItemAcr : String
    private var opsiKendaraan : String = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        find= ActivityInputPesananBinding.inflate(layoutInflater)
        setContentView(find.root)

        find.backPsn.setOnClickListener{}

        var id = intent.getStringExtra("idPsn")

        if (id == null){
            modeTambah()

        }else {
            modeEditP(id.toString().toInt())
        }

        val dataNamaAcr = db.dao().getNmaAcr()
        val newData = arrayOf("Pilih Jenis") + dataNamaAcr
        val spnNmaAcr = find.inpJnsAcr
        val spnNmaAdptr = ArrayAdapter(this, R.layout.simple_spinner_item, newData)
        spnNmaAdptr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnNmaAcr.adapter = spnNmaAdptr
        spnNmaAcr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItemAcr = parent?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) { }
        }
        val indexSpnKendaraan = if (opsiKendaraan == "null") 0 else newData.indexOf(opsiKendaraan)
        spnNmaAcr.setSelection(indexSpnKendaraan)

        find.btnTmbhPsn.setOnClickListener {

            if (find.inpNmaPmsn.text.isNotEmpty() &&
                find.inpAlmtPmsn.text.isNotEmpty() &&
                find.inpJmlhPsn.text.isNotEmpty() &&
                selectedItemAcr != "Pilih Jenis"
            ){
                db.dao().InsertPesanan(
                    Pesanan(0,
                        find.inpNmaPmsn.text.toString(),
                        selectedItemAcr,
                        find.inpJmlhPsn.text.toString().toInt(),
                        find.inpAlmtPmsn.text.toString()
                    )
                )
                    alert("Data berhasil ditambahkan")


            }else{
                alert("Isi data terlebih dahulu")
            }
        }

        find.btnUpdatePsn.setOnClickListener {
            if (find.inpNmaPmsn.text.isNotEmpty() &&
                find.inpAlmtPmsn.text.isNotEmpty() &&
                find.inpJmlhPsn.text.isNotEmpty() &&
                selectedItemAcr != "Pilih Jenis"
            ){
                db.dao().UpdatePesanan(
                    Pesanan(
                        id.toString().toInt(),
                        find.inpNmaPmsn.text.toString(),
                        find.inpAlmtPmsn.text.toString(),
                        find.inpJmlhPsn.text.toString().toInt(),
                        selectedItemAcr
                    )
                )
                alert("Data berhasil diubah")

            }else{
                alert("Ubah data terlebih dahulu")
            }

        }
    }

    private fun alert(pesan: String) {
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show()
    }

    private fun modeEditP(id:Int) {
        find.btnTmbhPsn.visibility = View.GONE
        find.hdngPsn.text="Edit Akrilik"

        val dataPsn = db.dao().getIDPesanan(id)[0]
        find.inpNmaPmsn.setText(dataPsn.nama_psn)
        find.inpAlmtPmsn.setText(dataPsn.almt_psn)
        find.inpJmlhPsn.setText(dataPsn.jmlh_psn.toString())
        selectedItemAcr
    }

    private fun modeTambah() {
        find.btnUpdatePsn.visibility = View.GONE
        find.hdngPsn.text="Tambah Pesanan"
    }
}