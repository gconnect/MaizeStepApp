package com.chuks.maizestemapp.categoriesofspecies


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chuks.maizestemapp.common.ui.HomeActivity
import com.chuks.maizestemapp.databinding.FragmentSpeciesCategoriesBinding


/**
 * The speciesCategories Fragment extends [Fragment] and it handles onclick
 * to the different categories of the species
 */
class SpeciesCategories : Fragment() {
    lateinit var binding : FragmentSpeciesCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            com.chuks.maizestemapp.R.layout.fragment_species_categories, container, false)
        categoryItemClicked()

        binding.toolbar.setNavigationOnClickListener{
            findNavController().popBackStack()
        }

        return binding.root
    }

    /**
     * This method is called in the onCreate to perform click on category button
     * clicked and takes the user to the page
     */
    private fun categoryItemClicked(){
        binding.fallArmyworm.setOnClickListener { findNavController().navigate(com.chuks.maizestemapp.R.id.fallArmywormFragment) }
        binding.egyptianLeafworm.setOnClickListener { findNavController().navigate(com.chuks.maizestemapp.R.id.egyptianWormFragment) }
        binding.africanArmyworm.setOnClickListener { findNavController().navigate(com.chuks.maizestemapp.R.id.africanWormFragment) }
    }


}
