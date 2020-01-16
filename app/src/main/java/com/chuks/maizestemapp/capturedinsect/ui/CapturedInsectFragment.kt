package com.chuks.maizestemapp.capturedinsect.ui


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chuks.maizestemapp.DetailedFragment
import com.chuks.maizestemapp.R
import com.chuks.maizestemapp.capturedinsect.adapter.BaseRecyclerAdapter
import com.chuks.maizestemapp.capturedinsect.data.Insect
import com.chuks.maizestemapp.databinding.FragmentCapturedInsectBinding

/**
 * A simple [Fragment] subclass.
 */
class CapturedInsectFragment : Fragment() {

    private lateinit var capturedInsectRecyclerAdapter: BaseRecyclerAdapter
    private lateinit var binding: FragmentCapturedInsectBinding
    private lateinit var capturedInsectList: List<Insect>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_captured_insect, container, false )

        initializeRecyclerView()
        setData()
        capturedInsectRecyclerAdapter.layoutId = R.layout.insect_list_item
        capturedInsectRecyclerAdapter.items = capturedInsectList
        capturedInsectRecyclerAdapter.onCustomClickItemListner ={view, position ->
            Toast.makeText(context, "You clicked $position", Toast.LENGTH_LONG).show()
        }
        return binding.root
    }

    fun initializeRecyclerView(){
        binding.captureRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            capturedInsectRecyclerAdapter = BaseRecyclerAdapter()
            setHasFixedSize(true)
            adapter = capturedInsectRecyclerAdapter
        }
    }

    fun setData(){
        val list = dataSet()
        capturedInsectList = list
    }


//Dummy data to be replaced with real data from the api

    fun dataSet() : List<Insect>{
        val list = ArrayList<Insect>()
        list.add(Insect(
            "https://cdn.pixabay.com/photo/2019/05/03/03/09/vietnam-4174969_960_720.jpg",
            "Fall Armworm", "Portharcourt",
            "12 01 2020", "12:00PM",1))

        list.add(Insect(
            "https://cdn.pixabay.com/photo/2019/05/03/03/09/vietnam-4174969_960_720.jpg",
            "Fall Armworm", "Portharcourt",
            "12 01 2020", "12:00PM",1))

        list.add(Insect(
            "https://cdn.pixabay.com/photo/2019/05/03/03/09/vietnam-4174969_960_720.jpg",
            "Fall Armworm", "Portharcourt",
            "12 01 2020", "12:00PM",1))
        return list
    }

}
