package com.asiasquare.byteg.shoppingdemo.itemlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asiasquare.byteg.shoppingdemo.R
import com.asiasquare.byteg.shoppingdemo.databinding.FragmentItemListBinding


class ItemListFragment : Fragment() {

    private var _binding : FragmentItemListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }


}