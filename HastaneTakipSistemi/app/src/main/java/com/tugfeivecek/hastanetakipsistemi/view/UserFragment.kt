package com.tugfeivecek.hastanetakipsistemi.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding

    private var durum: Boolean = false
    private var adsoyad: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sp = requireActivity().getSharedPreferences("GirisBilgi", Context.MODE_PRIVATE)
        durum = sp.getBoolean("girisDurum", false)
        adsoyad = sp.getString("adsoyad", "Ad Soyad Yok").toString()

        if (durum == true) {
            binding.tvHelloUser.text = "Merhaba \n $adsoyad"
        }

    }

}