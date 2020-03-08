package com.chuks.maizestemapp.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chuks.maizestemapp.R
import com.chuks.maizestemapp.common.data.InsectResponse


/**
 * This About Fragment displays the about page/layout
 * */
class AboutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_about, container, false)

        return root
    }
}