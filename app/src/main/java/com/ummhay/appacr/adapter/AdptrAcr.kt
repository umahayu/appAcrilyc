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
import com.ummhay.appacr.InputAkrilik
import com.ummhay.appacr.R
import com.ummhay.appacr.database.Akrilik

class AdptrAcr (val list: ArrayList<Akrilik>, val listener: onAcr)
    : RecyclerView.Adapter<AdptrAcr.ViewHolder>() {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {

        val namaAkrilik = itemView.findViewById<TextView>(R.id.txtNmaAcr)
        val hargaAkrilik = itemView.findViewById<TextView>(R.id.txtHrgAcr)
//      val gmbrAkrilik = itemView.findViewById<TextView>(R.id.imgAcr)
        val detailAcr = itemView.findViewById<CardView>(R.id.dtailAcr)
        val hpsacr= itemView.findViewById<ImageView>(R.id.deleteAcr)
        val edtacr= itemView.findViewById<ImageView>(R.id.updateAcr)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).
            inflate(R.layout.adptr_akrilik,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namaAkrilik.text = list[position].nama_acr
        holder.hargaAkrilik.text = list[position].harga_acr.toString()
        holder.hpsacr.setOnClickListener{
            listener.hapusacr(list[position])
        }
        holder.edtacr.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, InputAkrilik::class.java).putExtra("idAcr", list[position].id_acrcr.toString())
            context.startActivity(intent)
        }
        holder.detailAcr.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailAkrilik::class.java).putExtra("idAcr", list[position].id_acrcr.toString())
            context.startActivity(intent)
        }

    }
    interface onAcr{
        fun hapusacr(akrilik: Akrilik)
    }

    fun setDataAcr(newList: List<Akrilik>){
        list.clear()
        list.addAll(newList)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}