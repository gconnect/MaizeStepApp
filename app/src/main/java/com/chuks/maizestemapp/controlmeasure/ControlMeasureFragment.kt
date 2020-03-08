package com.chuks.maizestemapp.controlmeasure


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.chuks.maizestemapp.databinding.FragmentControlMeasureBinding
import android.R.color
import android.text.style.BulletSpan
import android.R
import android.annotation.SuppressLint
import android.os.Build
import android.text.*
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController


/**
 * Control Measure Fragment will be used to display the control measures text descriptions
 */
class ControlMeasureFragment : Fragment() {

    private lateinit var binding : FragmentControlMeasureBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, com.chuks.maizestemapp.R.layout.fragment_control_measure,
            container, false)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

//        val string = SpannableStringBuilder("Text with\nBullet point")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            string.setSpan(BulletSpan(40, resources.getColor(com.chuks.maizestemapp.R.color.colorAccent), 20),
//                10, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//        binding.controlMeasure.text =string

        return binding.root
    }


}

