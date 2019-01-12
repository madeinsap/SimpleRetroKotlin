package com.project.solomode.testapp.retrokotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.my_item.view.*

class MyAdapter(val context: Context, val instansiList: ArrayList<Instansi>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return instansiList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textInstansi.text = instansiList[position].instansi.toString()
        holder.textAlamat.text = instansiList[position].alamat.toString()
        holder.btnSubmit.setOnClickListener {
            Toast.makeText(context, "${instansiList[position].instansi}", Toast.LENGTH_SHORT).show()
        }
    }

    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val textInstansi = itemView.txt_instansi
        val textAlamat = itemView.txt_alamat
        val btnSubmit = itemView.btn_submit
    }
}