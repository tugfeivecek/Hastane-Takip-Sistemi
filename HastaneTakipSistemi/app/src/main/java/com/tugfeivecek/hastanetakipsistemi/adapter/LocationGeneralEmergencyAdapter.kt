package com.tugfeivecek.hastanetakipsistemi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.model.Hospital

class LocationGeneralEmergencyAdapter(val generalEmergencyList: ArrayList<Hospital>) :
    RecyclerView.Adapter<LocationGeneralEmergencyAdapter.LocationGeneralEmergencyViewHolder>() {

    inner class LocationGeneralEmergencyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvEmergencyArea: TextView
        var tvBekleyen: TextView
        var pbGeneralFullResult: TextView

        init {
            tvEmergencyArea = view.findViewById(R.id.tvEmergencyArea)
            tvBekleyen = view.findViewById(R.id.tvBekleyen)
            pbGeneralFullResult = view.findViewById(R.id.pbGeneralFullResult)
        }
    }

    //adapteri baglama
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationGeneralEmergencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.location_emergency_card_design, parent, false)
        return LocationGeneralEmergencyViewHolder(view)
    }

    //card item
    override fun onBindViewHolder(holder: LocationGeneralEmergencyViewHolder, position: Int) {
        holder.tvEmergencyArea.text = generalEmergencyList[position].capacity.toString()
        holder.pbGeneralFullResult.text = generalEmergencyList[position].capacity.toString()
        holder.tvBekleyen.text = "Bekleyen: " + generalEmergencyList[position].capacity

    }

    override fun getItemCount(): Int {
        return generalEmergencyList.size
    }

    //yeni listeyi ekleme
    fun updateGeneralEmergencyList(newsGeneralEmergencyList: List<Hospital>) {
        generalEmergencyList.clear()
        generalEmergencyList.addAll(newsGeneralEmergencyList)
        //adapteru yenileme metodu
        notifyDataSetChanged()
    }
}