package com.tugfeivecek.hastanetakipsistemi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.model.Notice

class NoticeAdapter (val noticeList: ArrayList<Notice>) :
    RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {


    inner class NoticeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvNoticeTitle: TextView
        var tvNoticeContent: TextView
        var tvNoticeDate: TextView


        init {
            tvNoticeTitle = view.findViewById(R.id.tv_notice_title)
            tvNoticeContent = view.findViewById(R.id.tv_notice_content)
            tvNoticeDate = view.findViewById(R.id.tv_notice_date)
        }
    }

    //adapteri baglama
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rv_notice_card_design, parent, false)
        return NoticeViewHolder(view)
    }

    //card item
    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val notices = noticeList[position]

        holder.tvNoticeTitle.text = notices.baslik
        holder.tvNoticeContent.text = notices.content
        holder.tvNoticeDate.text = notices.date

    }

    override fun getItemCount(): Int {
        return noticeList.size
    }

    //yeni listeyi ekleme
    fun updateNotice(newNoticeList: List<Notice>) {
        noticeList.clear()
        noticeList.addAll(newNoticeList)
        //adapteru yenileme metodu
        notifyDataSetChanged()
    }
}