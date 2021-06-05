package com.tugfeivecek.hastanetakipsistemi.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.model.Records

class RecordsAdapter(val recordList: ArrayList<Records>) :
    RecyclerView.Adapter<RecordsAdapter.EmergecnyFullViewHolder>() {


    inner class EmergecnyFullViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvHospitalName: TextView
        var tvRecordDate: TextView
        var tvRecordHour: TextView
        var rcRecordAlan: TextView

        init {
            tvHospitalName = view.findViewById(R.id.tv_hospital_name)
            tvRecordDate = view.findViewById(R.id.tv_record_date)
            tvRecordHour = view.findViewById(R.id.tv_record_hour)
            rcRecordAlan = view.findViewById(R.id.tv_record_alan)

        }
    }

    //adapteri baglama
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmergecnyFullViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.records_card_design, parent, false)
        return EmergecnyFullViewHolder(view)
    }

    //card item
    override fun onBindViewHolder(holder: EmergecnyFullViewHolder, position: Int) {
        val generalFull = recordList[position]

        holder.tvHospitalName.text = generalFull.hospitalName
        holder.tvRecordDate.text = generalFull.date
        holder.tvRecordHour.text = generalFull.hour
        holder.rcRecordAlan.text = generalFull.alan

        if (generalFull.alan == "Kırmızı Alan") {
            holder.rcRecordAlan.setTextColor(Color.RED)
            Log.e("General Full Alan: ", "Kırmızı Alandayım")
        } else if (generalFull.alan == "Yeşil Alan") {
            holder.rcRecordAlan.setTextColor(Color.GREEN)
            Log.e("General Full Alan: ", "Yeşil Alandayım")
        } else if (generalFull.alan == "Sarı Alan") {
            holder.rcRecordAlan.setTextColor(Color.YELLOW)
            Log.e("General Full Alan: ", "Sarı Alandayım")
        }

    }

    override fun getItemCount(): Int {
        return recordList.size
    }

    //yeni listeyi ekleme
    fun updateRecord(newRecordList: List<Records>) {
        recordList.clear()
        recordList.addAll(newRecordList)
        //adapteru yenileme metodu
        notifyDataSetChanged()
    }
}