package com.tugfeivecek.hastanetakipsistemi.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.model.Device
import com.tugfeivecek.hastanetakipsistemi.model.SemptomDetail
import com.tugfeivecek.hastanetakipsistemi.utils.downloadFromUrl
import com.tugfeivecek.hastanetakipsistemi.utils.placeHolderProgressBar
import com.tugfeivecek.hastanetakipsistemi.view.personel.MedikalDeviceDetail
import com.tugfeivecek.hastanetakipsistemi.view.personel.PersonelDeviceSelectionActivity

class MedicalDeviceAdapter(val medikalList: ArrayList<Device>) :
    RecyclerView.Adapter<MedicalDeviceAdapter.MedikalDeviceViewHolder>() {

    inner class MedikalDeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_medikal_device: TextView
        var tv_active_result: TextView
        var tv_store_result: TextView
        var image_device: ImageView

        init {
            tv_medikal_device = view.findViewById(R.id.tv_medikal_device)
            tv_active_result = view.findViewById(R.id.tv_active_result)
            tv_store_result = view.findViewById(R.id.tv_store_result)
            image_device = view.findViewById(R.id.image_device)
        }
    }

    //adapteri baglama
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedikalDeviceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.personal_device_card, parent, false)
        return MedikalDeviceViewHolder(view)
    }

    //card item
    override fun onBindViewHolder(holder: MedikalDeviceViewHolder, position: Int) {
        val device = medikalList[position]

        holder.tv_medikal_device.text = device.name
        holder.tv_active_result.text = device.activeCount
        holder.tv_store_result.text = device.stokCount
        holder.image_device.downloadFromUrl(
            "http://kutuphanemnerede.com/" +
                    device.imageUrl,
            placeHolderProgressBar(holder.itemView.context)
        )
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MedikalDeviceDetail::class.java)
            intent.putExtra("deviceId", device.deviceId)
            holder.itemView.context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return medikalList.size
    }

    //yeni listeyi ekleme
    fun updateMedikalList(newsMedikalList: List<Device>) {
        medikalList.clear()
        medikalList.addAll(newsMedikalList)
        //adapteru yenileme metodu
        notifyDataSetChanged()
    }
}