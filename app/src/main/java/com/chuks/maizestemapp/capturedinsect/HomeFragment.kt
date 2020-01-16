package com.chuks.maizestemapp.capturedinsect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.chuks.maizestemapp.R
import com.chuks.maizestemapp.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

     lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
//        val root = inflater.inflate(R.layout.fragment_home, container, false)
        menuItemClicked()
        return binding.root

    }

    fun menuItemClicked(){
        binding.capturedInsect.setOnClickListener { findNavController().navigate(R.id.capturedInsectFragment) }
        binding.categories.setOnClickListener { findNavController().navigate(R.id.speciesCategories) }
        binding.maizePlot.setOnClickListener { findNavController().navigate(R.id.maizeInsectPlot) }
        binding.controlMeasure.setOnClickListener { findNavController().navigate(R.id.controlMeasureFragment) }
    }
}