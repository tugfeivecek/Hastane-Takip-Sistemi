package com.tugfeivecek.hastanetakipsistemi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.model.SemptomDetail

class SemptomDetailAdapter (val semptomDetailList: ArrayList<SemptomDetail>) :
    RecyclerView.Adapter<SemptomDetailAdapter.SemptomListViewHolder>() {

    inner class SemptomListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView
        var tvCapacity: TextView

        init {
            tvName = view.findViewById(R.id.tvName)
            tvCapacity = view.findViewById(R.id.tvCapacity)
        }
    }

    //adapteri baglama
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemptomListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.semptom_list_card, parent, false)
        return SemptomListViewHolder(view)
    }

    //card item
    override fun onBindViewHolder(holder: SemptomListViewHolder, position: Int) {
        val semptomList = semptomDetailList[position]

        holder.tvName.text = semptomList.name
        holder.tvCapacity.text = "Yatak Kapasitesi: " + semptomList.capacity

    }

    override fun getItemCount(): Int {
        return semptomDetailList.size
    }

    //yeni listeyi ekleme
    fun updateSemptomDetailList(newsSemptomDetailList: List<SemptomDetail>) {
        semptomDetailList.clear()
        semptomDetailList.addAll(newsSemptomDetailList)
        //adapteru yenileme metodu
        notifyDataSetChanged()
    }
}