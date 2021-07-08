package com.chuks.maizestemapp.categoriesofspecies.africanarmyworm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chuks.maizestemapp.R
import com.chuks.maizestemapp.categoriesofspecies.africanarmyworm.viewmodel.AfricanArmyWormViewModel
import com.chuks.maizestemapp.common.adapter.BaseRecyclerAdapter
import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.common.util.showToast
import com.chuks.maizestemapp.databinding.FragmentAfricanWormBinding
import kotlinx.android.synthetic.main.fragment_african_worm.*
import kotlinx.android.synthetic.main.fragment_captured_insect.emptyState
import kotlinx.android.synthetic.main.fragment_captured_insect.progressBar
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * This is AfricanWormFragment. This handles all UI related items
 * and communicates with the AfricanArmyWormViewModel
 */
class AfricanWormFragment : Fragment() {
    private lateinit var africanRecyclerAdapter: BaseRecyclerAdapter
    private lateinit var binding: FragmentAfricanWormBinding
    private  var insectList: List<Insect> = ArrayList()
    private val TAG : String = "AfricanWormFragment"
    private val africanArmyWormViewModel by viewModel<AfricanArmyWormViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_african_worm,
            container, false)

        initializeRecyclerView()
        setData()
        showProgress()
        showMessage()
        africanRecyclerAdapter.layoutId = R.layout.insect_list_item
        africanRecyclerAdapter.items = insectList

        africanRecyclerAdapter.onCustomClickItemListner ={view, position ->

            val bundle = Bundle()
            bundle.putInt("position", position )

            NavHostFragment.findNavController(this)
                .navigate(R.id.detailedFragment, bundle)

            Toast.makeText(context, "You clicked $position", Toast.LENGTH_LONG).show()

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
     * The [initializeRecyclerView] shows a list of all african worm
     */
    private fun initializeRecyclerView(){
        binding.africanRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            africanRecyclerAdapter = BaseRecyclerAdapter()
            setHasFixedSize(true)
            adapter = africanRecyclerAdapter
        }
    }

    /**
     * The [setData] sets the data on the recyclerview
     */
    private fun setData(){
        africanArmyWormViewModel.africanArmyList("AAW").observe(viewLifecycleOwner, Observer {
            Log.d(TAG , "captured $it")
            if(it.isNotEmpty()){
                africanRecyclerView.visibility = View.VISIBLE
                africanRecyclerAdapter.setData(it)
                emptyState.visibility = View.GONE

            }else{
                africanRecyclerView.visibility = View.GONE
                emptyState.visibility = View.VISIBLE
            }
        })
    }

    /**
     * This shows the progressbar when loading the african worm list data
     */
    private fun showProgress(){
        africanArmyWormViewModel.showProgress.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

    /**
     * This shows a toast message when an error occurs
     */
    private fun showMessage(){
        africanArmyWormViewModel.showMessage.observe(viewLifecycleOwner, Observer {
            val message = it
            context?.showToast(message)
        })
    }

}
