package com.tugfeivecek.hastanetakipsistemi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tugfeivecek.hastanetakipsistemi.R
import com.tugfeivecek.hastanetakipsistemi.adapter.SliderAdapter
import com.tugfeivecek.hastanetakipsistemi.databinding.ActivityOnBoardingScreenBinding
import me.relex.circleindicator.CircleIndicator3

class OnBoardingScreen : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingScreenBinding;
    private lateinit var sliderAdapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val indicator=findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(binding.sliderViewPager)

        sliderAdapter = SliderAdapter()
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, ChoiceActivity::class.java)
            startActivity(intent)
        }

        binding.sliderViewPager.adapter = sliderAdapter
    }

}