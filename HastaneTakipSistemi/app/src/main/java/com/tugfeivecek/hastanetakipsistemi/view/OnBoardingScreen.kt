package com.tugfeivecek.hastanetakipsistemi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.tugfeivecek.hastanetakipsistemi.adapter.SliderAdapter
import com.tugfeivecek.hastanetakipsistemi.databinding.ActivityOnBoardingScreenBinding

class OnBoardingScreen : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingScreenBinding;
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var mDots: Array<TextView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sliderAdapter = SliderAdapter()
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, ChoiceActivity::class.java)
            startActivity(intent)
        }

        binding.sliderViewPager.adapter = sliderAdapter
    }
}