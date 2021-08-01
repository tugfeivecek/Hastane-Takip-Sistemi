package com.tugfeivecek.hastanetakipsistemi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.tugfeivecek.hastanetakipsistemi.R
import java.util.*

class SliderAdapter : RecyclerView.Adapter<SliderAdapter.SliderAdapterViewHolder>() {

    var imageArray = arrayOf(
        R.drawable.presentationmap,
        R.drawable.presentationemergency,
        R.drawable.presentationanalystic,
        R.drawable.presentationmedicaldevice
    )

    var textArray =
        arrayOf("Konumunuzu Açınız", "Acil Servis Takibi", "Veri Analizi", "Tıbbi Cihaz Takibi")

    inner class SliderAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewSlider: TextView
        var imageViewSlider: ImageView

        init {
            imageViewSlider = view.findViewById(R.id.imageViewSlider)
            textViewSlider = view.findViewById(R.id.textViewSlider)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.slider_layout, parent, false)
        return SliderAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderAdapterViewHolder, position: Int) {

        holder.textViewSlider.text = textArray[position]
        holder.imageViewSlider.setImageResource(imageArray[position])
    }

    override fun getItemCount(): Int {
        return imageArray.size
    }


}