package com.ummhay.appacr.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ummhay.appacr.DetailAkrilik
import com.ummhay.appacr.DetailPesanan
import com.ummhay.appacr.InputPesanan
import com.ummhay.appacr.R
import com.ummhay.appacr.database.Akrilik
import com.ummhay.appacr.database.Pesanan

class AdptrPsn (val list: ArrayList<Pesanan>, val listener: onPsn)
    : RecyclerView.Adapter<AdptrPsn.ViewHolder>() {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {

        val namapesan = itemView.findViewById<TextView>(R.id.txtNmaPmsn)
        val almtpesan = itemView.findViewById<TextView>(R.id.txtAlmt)
        val jnspesan = itemView.findViewById<TextView>(R.id.txtJns)
        val detailPsn = itemView.findViewById<CardView>(R.id.dtailPsanan)
        val hpspesan= itemView.findViewById<ImageView>(R.id.deletePsnan)
        val edtpesan= itemView.findViewById<ImageView>(R.id.updatePsnan)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).
            inflate(R.layout.adptr_pesanan,parent,false)
        )
    }

    override fun onBindViewHolder(holder: AdptrPsn.ViewHolder, position: Int) {
        holder.namapesan.text = list[position].nama_psn
        holder.almtpesan.text = list[position].almt_psn
        holder.jnspesan.text = list[position].jns_psn
        holder.hpspesan.setOnClickListener{
            listener.hapusPsn(list[position])
        }
        holder.edtpesan.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, InputPesanan::class.java).putExtra("idPsn", list[position].id_psn.toString())
            context.startActivity(intent)
        }
        holder.detailPsn.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailPesanan::class.java).putExtra("idPsn", list[position].id_psn.toString())
            context.startActivity(intent)
        }
    }

    interface onPsn{
        fun hapusPsn(pesanan: Pesanan)
    }

    fun setDataPsn(newList: List<Pesanan>){
        list.clear()
        list.addAll(newList)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
