package com.chuks.maizestemapp.categoriesofspecies.egyptianarmyworm.ui


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
import com.chuks.maizestemapp.categoriesofspecies.egyptianarmyworm.viewmodel.EgyptianWormViewModel
import com.chuks.maizestemapp.common.adapter.BaseRecyclerAdapter
import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.common.util.showToast
import com.chuks.maizestemapp.databinding.FragmentAfricanWormBinding
import com.chuks.maizestemapp.databinding.FragmentEgyptianWormBinding
import kotlinx.android.synthetic.main.fragment_african_worm.*
import kotlinx.android.synthetic.main.fragment_african_worm.emptyState
import kotlinx.android.synthetic.main.fragment_african_worm.progressBar
import kotlinx.android.synthetic.main.fragment_captured_insect.*
import kotlinx.android.synthetic.main.fragment_egyptian_worm.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * This is EgyptianWormFragment. This handles all UI related items
 * and communicates with the EgyptianWormViewModel
 */
class EgyptianWormFragment : Fragment() {

    private lateinit var egyptianRecyclerAdapter: BaseRecyclerAdapter
    private lateinit var binding: FragmentEgyptianWormBinding
    private  var insectList: List<Insect> = ArrayList()
    private val TAG : String = "EgyptianWormFragment"
    private val egyptianViewModel by viewModel<EgyptianWormViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_egyptian_worm, container, false )

        initializeRecyclerView()
        setData()
        showProgress()
        showMessage()
        egyptianRecyclerAdapter.layoutId = R.layout.insect_list_item
        egyptianRecyclerAdapter.items = insectList

        egyptianRecyclerAdapter.onCustomClickItemListner ={view, position ->

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
     * The [initializeRecyclerView] shows a list of all egyptian worm
     */
    private fun initializeRecyclerView(){
        binding.egyptianRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            egyptianRecyclerAdapter = BaseRecyclerAdapter()
            setHasFixedSize(true)
            adapter = egyptianRecyclerAdapter
        }
    }

    /**
     * The [setData] sets the data on the recyclerview
     */
    private fun setData(){
        egyptianViewModel.egyptianList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG , "captured $it")
            if(it.isNotEmpty()){
                egyptianRecyclerView.visibility = View.VISIBLE
                egyptianRecyclerAdapter.setData(it)
                emptyState.visibility = View.GONE

            }else{
                egyptianRecyclerView.visibility = View.GONE
                emptyState.visibility = View.VISIBLE
            }
        })
    }

    /**
     * This shows the progressbar when loading the egyptian worm list data
     */
    private fun showProgress(){
        egyptianViewModel.showProgress.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

    /**
     * This shows a toast message when an error occurs
     */
    private fun showMessage(){
        egyptianViewModel.showMessage.observe(viewLifecycleOwner, Observer {
            val message = it
            context?.showToast(message)
        })
    }

}
