package com.asiasquare.byteg.shoppingdemo.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.asiasquare.byteg.shoppingdemo.R
import com.asiasquare.byteg.shoppingdemo.databinding.FragmentCatalogBinding
/**
    First fragment of the app.
    Display a list of catalog for user to choose.
 **/
class CatalogFragment : Fragment() {

    /** binding will only exist between onAttach and on Detach **/
    private var _binding : FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    /**
     * Create viewModel, provide application to Factory to create an AndroidViewModel class
     */
    private val viewModel: CatalogFragmentViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this,CatalogFragmentViewModel.Factory(activity.application))
            .get(CatalogFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCatalogBinding.inflate(inflater,container,false)

        return binding.root
    }


    /**Remove _binding when fragment is destroy**/
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}