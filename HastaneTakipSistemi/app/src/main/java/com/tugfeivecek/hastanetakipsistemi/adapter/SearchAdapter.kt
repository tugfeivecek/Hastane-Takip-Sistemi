package com.tugfeivecek.hastanetakipsistemi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.model.Search

class SearchAdapter(val searchList: ArrayList<Search>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvSearchName: TextView
        var tvHospitalAddress: TextView
        var tvHospitalCapacity: TextView
        var tvPatientCount: TextView

        init {
            tvSearchName = view.findViewById(R.id.tv_search_name)
            tvHospitalAddress = view.findViewById(R.id.tv_hospital_address)
            tvHospitalCapacity = view.findViewById(R.id.tv_hospital_capacity)
            tvPatientCount = view.findViewById(R.id.tv_patient_count)
        }
    }

    //adapteri baglama
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.search_hospital_list, parent, false)
        return SearchViewHolder(view)
    }

    //card item
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        val searches = searchList[position]

        holder.tvSearchName.text = searches.name
        holder.tvHospitalAddress.text = searches.address
        holder.tvHospitalCapacity.text = "Yatak Kapasitesi:" + searches.capacity.toString()
        holder.tvPatientCount.text = "Hasta Sayısı: " + searches.patientCount.toString()

    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    //yeni listeyi ekleme
    fun updateSearchList(newSearchList: List<Search>) {
        searchList.clear()
        searchList.addAll(newSearchList)
        //adapteru yenileme metodu
        notifyDataSetChanged()
    }
}