package com.chuks.maizestemapp.capturedinsect.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chuks.maizestemapp.common.adapter.BaseRecyclerAdapter
import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.databinding.FragmentCapturedInsectBinding
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.chuks.maizestemapp.R
import com.chuks.maizestemapp.capturedinsect.viewmodel.CapturedInsectViewModel
import com.chuks.maizestemapp.common.util.showToast
import kotlinx.android.synthetic.main.fragment_captured_insect.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


/**
 * This is CapturedInsectFragment. This handles all UI related items
 * and communicates with the CapturedInsectViewModel
 */
class CapturedInsectFragment : Fragment() {

    private lateinit var capturedInsectRecyclerAdapter: BaseRecyclerAdapter
    private lateinit var binding: FragmentCapturedInsectBinding
    private  var capturedInsectList: List<Insect> = ArrayList()
    private val TAG : String = "CapturedInsectFragment"
    private val capturedInsectViewModel by viewModel<CapturedInsectViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
           R.layout.fragment_captured_insect, container, false )

        initializeRecyclerView()
        setData()
        showProgress()
        showMessage()
        capturedInsectRecyclerAdapter.layoutId = R.layout.insect_list_item
        capturedInsectRecyclerAdapter.items = capturedInsectList

        capturedInsectRecyclerAdapter.onCustomClickItemListner ={view, position ->

            val bundle = Bundle()
            bundle.putInt("position", position )
            NavHostFragment.findNavController(this)
                .navigate(R.id.detailedFragment, bundle)
            context?.showToast("You clicked $position")
        }

        binding.toolbar.setNavigationOnClickListener{
            findNavController().popBackStack()
        }
        binding.swipe.setOnRefreshListener {
            setData()
            showMessage()
            showProgress()
            binding.swipe.isRefreshing = false
        }
        return binding.root
    }

    /**
    * The [initializeRecyclerView] shows a list of all captured insects
    */
    fun initializeRecyclerView(){
        binding.captureRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            capturedInsectRecyclerAdapter = BaseRecyclerAdapter()
            setHasFixedSize(true)
            adapter = capturedInsectRecyclerAdapter
        }
    }

    /**
     * The [setData] sets the data on the recyclerview
     */
    private fun setData(){
        capturedInsectViewModel.capturedInsect.observe(viewLifecycleOwner, Observer {
            Log.d(TAG , "captured $it.size")
            if(it.isNotEmpty()){
                captureRecyclerView.visibility = View.VISIBLE
                capturedInsectRecyclerAdapter.setData(it)
                emptyState.visibility = View.GONE

            }else{
                binding.captureRecyclerView.visibility = View.GONE
                emptyState.visibility = View.VISIBLE
            }
        })
    }

    /**
     * This shows the progressbar when loading the captured list data
     */
    private fun showProgress(){
        capturedInsectViewModel.showProgress.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

    /**
     * This shows a toast message when an error occurs
     */
    private fun showMessage(){
        capturedInsectViewModel.showMessage.observe(viewLifecycleOwner, Observer {
            val message = it
            context?.showToast(message)
        })
    }
}

