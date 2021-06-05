package com.tugfeivecek.hastanetakipsistemi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.model.Semptom
import com.tugfeivecek.hastanetakipsistemi.utils.downloadFromUrl
import com.tugfeivecek.hastanetakipsistemi.utils.placeHolderProgressBar
import com.tugfeivecek.hastanetakipsistemi.view.SemptomDetailFragmentDirections

class SemptomAdapter(val semptomList: ArrayList<Semptom>) :
    RecyclerView.Adapter<SemptomAdapter.SemptomViewHolder>() {

    inner class SemptomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageSemptom: ImageView
        var tvSemptom: TextView

        init {
            imageSemptom = view.findViewById(R.id.image_semptom)
            tvSemptom = view.findViewById(R.id.tv_semptom)
        }
    }

    //adapteri baglama
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemptomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.semptom_card_design, parent, false)
        return SemptomViewHolder(view)
    }

    //card item
    override fun onBindViewHolder(holder: SemptomViewHolder, position: Int) {

        holder.tvSemptom.text = semptomList[position].name
        holder.imageSemptom.downloadFromUrl(
            "http://kutuphanemnerede.com/hastane/images/" +
                    semptomList[position].imageUrl.toString(),
            placeHolderProgressBar(holder.itemView.context)
        )

        holder.itemView.setOnClickListener {
            val action =
                SemptomDetailFragmentDirections.actionSemptomDetailFragmentToSemptomListFragment(
                    semptomList[position].type_id!!.toString()
                )
            Toast.makeText(
                holder.itemView.context,
                "Data : ${semptomList[position].type_id!!.toString()}",
                Toast.LENGTH_SHORT
            ).show()
            Navigation.findNavController(it).navigate(action)


        }
    }

    override fun getItemCount(): Int {
        return semptomList.size
    }

    //yeni listeyi ekleme
    fun updateSemptomList(newSemptomList: List<Semptom>) {
        semptomList.clear()
        semptomList.addAll(newSemptomList)
        //adapteru yenileme metodu
        notifyDataSetChanged()
    }
}