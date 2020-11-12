package com.chuks.maizestemapp.categoriesofspecies.fallarmyworm.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chuks.maizestemapp.R
import com.chuks.maizestemapp.categoriesofspecies.fallarmyworm.viewmodel.FallArmyWormViewModel
import com.chuks.maizestemapp.common.adapter.BaseRecyclerAdapter
import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.common.util.showToast
import com.chuks.maizestemapp.databinding.FragmentFallArmywormBinding
import kotlinx.android.synthetic.main.fragment_egyptian_worm.emptyState
import kotlinx.android.synthetic.main.fragment_egyptian_worm.progressBar
import kotlinx.android.synthetic.main.fragment_fall_armyworm.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * This is FallArmywormFragment. This handles all UI related items
 * and communicates with the FallArmyWormViewModel
 */
class FallArmywormFragment : Fragment() {

    private lateinit var fallArmyRecyclerAdapter: BaseRecyclerAdapter
    private lateinit var binding: FragmentFallArmywormBinding
    private  var insectList: List<Insect> = ArrayList()
    private val TAG : String = "FallArmywormFragment"
    private val fallArmyViewModel by viewModel<FallArmyWormViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_fall_armyworm, container,
            false )

        initializeRecyclerView()
        setData()
        showProgress()
        showMessage()
        fallArmyRecyclerAdapter.layoutId = R.layout.insect_list_item
        fallArmyRecyclerAdapter.items = insectList

        fallArmyRecyclerAdapter.onCustomClickItemListner ={view, position ->

            val bundle = Bundle()
            bundle.putInt("position", position )

            NavHostFragment.findNavController(this)
                .navigate(R.id.detailedFragment, bundle)

            Toast.makeText(context, "You clicked $position", Toast.LENGTH_LONG).show()

        }

        binding.toolbar.setNavigationOnClickListener{
            findNavController().popBackStack()
        }

        return binding.root
    }
    /**
     * The [initializeRecyclerView] shows a list of all fall armyworm
     */
    private fun initializeRecyclerView(){
        binding.fallRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            fallArmyRecyclerAdapter = BaseRecyclerAdapter()
            setHasFixedSize(true)
            adapter = fallArmyRecyclerAdapter
        }
    }

    /**
     * The [setData] sets the data on the recyclerview
     */
    private fun setData(){
        fallArmyViewModel.fallArmyList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG , "captured $it")
            if(it.isNotEmpty()){
                fallRecyclerView.visibility = View.VISIBLE
                fallArmyRecyclerAdapter.setData(it)
                emptyState.visibility = View.GONE

            }else{
                fallRecyclerView.visibility = View.GONE
                emptyState.visibility = View.VISIBLE
            }
        })
    }

    /**
     * This shows the progressbar when loading the fall armyworm list data
     */
    private fun showProgress(){
        fallArmyViewModel.showProgress.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

    /**
     * This shows a toast message when an error occurs
     */
    private fun showMessage(){
        fallArmyViewModel.showMessage.observe(viewLifecycleOwner, Observer {
            val message = it
            context?.showToast(message)
        })
    }
}
