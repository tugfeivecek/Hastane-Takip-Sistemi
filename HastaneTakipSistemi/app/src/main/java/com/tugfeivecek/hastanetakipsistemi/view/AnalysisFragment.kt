package com.tugfeivecek.hastanetakipsistemi.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentAnalysisBinding


class AnalysisFragment : Fragment() {
    private lateinit var binding: FragmentAnalysisBinding
    lateinit var barlist: ArrayList<BarEntry>
    lateinit var bardataset: ArrayList<String>
    lateinit var lineDataSet: BarDataSet
    lateinit var barData: BarData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAnalysisBinding.inflate(layoutInflater)
        barlist = ArrayList()
        barlist.add(BarEntry(1f, 900f))
        barlist.add(BarEntry(2f, 800f))
        barlist.add(BarEntry(3f, 600f))
        barlist.add(BarEntry(4f, 700f))
        barlist.add(BarEntry(5f, 800f))

        lineDataSet = BarDataSet(barlist, "En çok gidilen hastaneler")
        barData = BarData(lineDataSet)
        binding.barChart.data = barData

        binding.barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS, 250)
        lineDataSet.valueTextColor = Color.BLACK
        lineDataSet.valueTextSize = 12f

        val xLabels: ArrayList<String> = ArrayList()
        xLabels.add("Çapa")
        xLabels.add("Bezmialem")
        xLabels.add("Cerrahpaşa")
        xLabels.add("Haseki")
        xLabels.add("Samatya")

        val xAxis: XAxis = binding.barChart.getXAxis()
        xAxis.setValueFormatter(IndexAxisValueFormatter(xLabels))
        binding.barChart.setDrawGridBackground(false)
        binding.barChart.axisRight.isEnabled = false
        binding.barChart.description.isEnabled=false
        binding.barChart.invalidate()

        return binding.root
    }
}