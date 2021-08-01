package com.tugfeivecek.hastanetakipsistemi.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.model.Device
import com.tugfeivecek.hastanetakipsistemi.model.Hospital
import com.tugfeivecek.hastanetakipsistemi.utils.downloadFromUrl
import com.tugfeivecek.hastanetakipsistemi.utils.placeHolderProgressBar
import com.tugfeivecek.hastanetakipsistemi.view.personel.MedikalDeviceDetail
import com.tugfeivecek.hastanetakipsistemi.view.personel.PersonelMedikalDeviceListActivity

class OtherDeviceAdapter(val otherList: ArrayList<Hospital>) :
    RecyclerView.Adapter<OtherDeviceAdapter.OtherDeviceViewHolder>() {

    inner class OtherDeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_other_hospital_km: TextView
        var tv_other_hospital_name: TextView

        init {
            tv_other_hospital_name = view.findViewById(R.id.tv_other_hospital_name)
            tv_other_hospital_km = view.findViewById(R.id.tv_other_hospital_km)

        }
    }

    //adapteri baglama
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherDeviceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.other_devices_card, parent, false)
        return OtherDeviceViewHolder(view)
    }

    //card item
    override fun onBindViewHolder(holder: OtherDeviceViewHolder, position: Int) {
        val device = otherList[position]

        holder.tv_other_hospital_name.text = device.hospitalName
        holder.tv_other_hospital_km.text = device.capacity.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PersonelMedikalDeviceListActivity::class.java)
            intent.putExtra("hospitalNameData",device.hospitalName)
            holder.itemView.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return otherList.size
    }

    //yeni listeyi ekleme
    fun updateOtherList(newsOtherList: List<Hospital>) {
        otherList.clear()
        otherList.addAll(newsOtherList)
        //adapteru yenileme metodu
        notifyDataSetChanged()
    }
}