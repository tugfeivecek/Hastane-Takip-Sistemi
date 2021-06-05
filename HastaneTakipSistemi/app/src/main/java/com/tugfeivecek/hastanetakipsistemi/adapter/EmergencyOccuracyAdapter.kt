package com.tugfeivecek.hastanetakipsistemi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.model.EmergencyOccuracy

class EmergencyOccuracyAdapter(val occuracyList: ArrayList<EmergencyOccuracy>) :
    RecyclerView.Adapter<EmergencyOccuracyAdapter.EmergencyOccuracyViewHolder>() {


    inner class EmergencyOccuracyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvHospitalName: TextView
        var tvCapacity: TextView
        var tvWait: TextView


        init {
            tvHospitalName = view.findViewById(R.id.tv_occuracy_name)
            tvCapacity = view.findViewById(R.id.tv_occuracy_capacity)
            tvWait = view.findViewById(R.id.tv_occuracy_wait)
        }
    }

    //adapteri baglama
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmergencyOccuracyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.emergency_occupancy_card_design, parent, false)
        return EmergencyOccuracyViewHolder(view)
    }

    //card item
    override fun onBindViewHolder(holder: EmergencyOccuracyViewHolder, position: Int) {
        val occuracy = occuracyList[position]

        holder.tvHospitalName.text = occuracy.hospitalName
        holder.tvCapacity.text = "Kapasite:" + occuracy.capacity
        holder.tvWait.text = "Bekleyen: " + occuracy.wait

    }

    override fun getItemCount(): Int {
        return occuracyList.size
    }

    //yeni listeyi ekleme
    fun updateNotice(newOccuracyList: List<EmergencyOccuracy>) {
        occuracyList.clear()
        occuracyList.addAll(newOccuracyList)
        //adapteru yenileme metodu
        notifyDataSetChanged()
    }
}