package com.tugfeivecek.hastanetakipsistemi.utils

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.downloadFromUrl(url: String, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions().placeholder(progressDrawable)


    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeHolderProgressBar(context: Context): CircularProgressDrawable {

    return CircularProgressDrawable(context).apply {
        setColorSchemeColors(Color.DKGRAY)
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}