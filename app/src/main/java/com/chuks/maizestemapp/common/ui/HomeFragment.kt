package com.chuks.maizestemapp.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chuks.maizestemapp.databinding.FragmentHomeBinding
import com.chuks.maizestemapp.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * The HomeFragment is the destination fragment that displays the four buttons for captured insect,
 * categories of species, maizeInsectPlot and control measure
 * */
class HomeFragment : Fragment() {

     lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)

        menuItemClicked()
        return binding.root

    }

    /**
     * The [menuItemClicked] performs the click on each buttons and will be called in the oncreate
     * */
    fun menuItemClicked(){
        binding.capturedInsect.setOnClickListener { findNavController().navigate(R.id.capturedInsectFragment) }
        binding.categories.setOnClickListener { findNavController().navigate(R.id.speciesCategories) }
        binding.maizePlot.setOnClickListener { findNavController().navigate(R.id.maizeInsectPlot) }
        binding.controlMeasure.setOnClickListener { findNavController().navigate(R.id.controlMeasureFragment2) }
    }


}