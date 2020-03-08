package com.chuks.maizestemapp.common.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.chuks.maizestemapp.capturedinsect.viewmodel.CapturedInsectViewModel
import com.chuks.maizestemapp.databinding.FragmentDetailedBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.content.Intent

/**
* This Detailed Fragment displays the details of each list items
* */
class DetailedFragment : Fragment() {

    private lateinit var binding: FragmentDetailedBinding
    private val capturedInsectViewModel by viewModel<CapturedInsectViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            com.chuks.maizestemapp.R.layout.fragment_detailed, container, false
        )

        binding.back.setOnClickListener { findNavController().popBackStack() }
        binding.shareFab.setOnClickListener { shareInsect() }
        // detailView implementation
        arguments?.let { bundle ->
            bundle.getInt("position")
                ?.let { position ->
                    capturedInsectViewModel.capturedInsect.observe(viewLifecycleOwner, Observer {
                        val data = it[position]

                        Glide.with(this).asBitmap().override(1080, 600)
                            .load(data.insect_image).into(binding.insectImage)
                        binding.title.text = data.name
                        binding.locationLat.text = data.latitude
                        binding.locationLong.text = data.longitude
                        binding.timeTv.text = data.time
                        binding.calendar.text = data.date
                        binding.count.text = data.count.toString()

                    })

                }
        }

        return binding.root
    }

    /**
    * The [shareInsect] shares the selected insect
    * */
    private fun shareInsect() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Hello dear, Check out Maize Insect App "
        )
        sendIntent.type = "text/plain"
        this.startActivity(sendIntent)
    }

}
