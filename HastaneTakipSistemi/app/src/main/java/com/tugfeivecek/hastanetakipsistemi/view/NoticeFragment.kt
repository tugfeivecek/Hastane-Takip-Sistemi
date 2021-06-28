package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.adapter.NoticeAdapter
import com.tugfeivecek.hastanetakipsistemi.adapter.RecordsAdapter
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentNoticeBinding
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentRecordsBinding
import com.tugfeivecek.hastanetakipsistemi.model.Notice
import com.tugfeivecek.hastanetakipsistemi.model.Records


class NoticeFragment : Fragment() {
    private lateinit var binding: FragmentNoticeBinding
    private lateinit var adapter: NoticeAdapter
    private lateinit var records: ArrayList<Notice>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoticeBinding.inflate(layoutInflater)
        binding.toolbarHome.setNavigationIcon(R.drawable.back)
        binding.toolbarHome.setNavigationOnClickListener {

            activity?.onBackPressed()
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNotice.setHasFixedSize(true)
        binding.rvNotice.layoutManager= LinearLayoutManager(context)

        val u1= Notice("e-Rapor/Doğum Raporu Bileşeni Nüfus Tescil İşlemi 3. Grup Uzaktan Eğitim Toplantısı","Hacettepe Üniversitesi Hastanesi tarafından “ Hekimler için Hemodiyaliz Diyalizi Sertifikalı Eğitim Programı ” 19 Şubat 2018 tarihinde başlayacak olup katılmak isteyen bütün katılımcıların 15 Ocak – 15 Şubat 2018 tarihleri arasında evrakları ile doğrudan eğitim merkezine başvuru yapmaları gerekmektedir.","29/12/2017")
        val u2= Notice("Başkent Üniversitesi Nefroloji Bilim Dalı Pratisyen Doktor Diyaliz Sertifikasyon Programı ve Eğitim Tarihleri","Eğitime başvurmak isteyenlerin aşağıdaki belgeler ile birlikte hastanemiz Nefroloji Polikliniğine başvurmaları gerekmektedir.","23/09/2020")
        val u3= Notice("Evde Sağlık Hizmetleri Temel Eğitimleri Başlıyor"," Eğitimler; Kamu Hastaneleri Genel Müdürlüğü Sağlık Hizmetleri Dairesi Başkanlığı tarafından hazırlanmıştır. Sağlık Hizmetleri Genel Müdürlüğü Eğitim ve Sertifikasyon Dairesi Başkanlığı ile ortak çalışmalar yürütülmüş olup, eğitimlere Uzaktan Sağlık Eğitim Sistemi (USES) üzerinden erişim sağlanabilir.","12/04/2021")


        //araylist olusturma
        records = ArrayList<Notice>()
        records.add(u1)
        records.add(u2)
        records.add(u3)
        adapter= NoticeAdapter(records)
        binding.rvNotice.adapter = adapter

    }
}