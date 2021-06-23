package com.asiasquare.byteg.shoppingdemo.itemlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import com.asiasquare.byteg.shoppingdemo.databinding.FragmentItemListBinding
import kotlin.properties.Delegates


class ItemListFragment : Fragment() {

    private val args: ItemListFragmentArgs by navArgs()
    private var itemList by Delegates.notNull<Int>()

    private var _binding : FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ItemListFragmentViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, ItemListFragmentViewModel.Factory(activity.application))
            .get(ItemListFragmentViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentItemListBinding.inflate(inflater,container,false)

        /** Create recyclerView adapter and define OnClickListener **/
        val adapter = ItemListFragmentAdapter(ItemListFragmentAdapter.OnClickListener{
            viewModel.onDetailClick(it)
        })

        binding.recyclerViewCatalog.adapter = adapter

        /** Update data to adapter **/
        viewModel.text.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
//                when (args.catalogId){
//                    0 -> adapter.submitList(it).getData()
//                    1 ->
//                    2 ->
//                    3 ->
//                    4 ->
//                    5 ->
//                    else ->
//                }
            }
        })

        itemList= args.catalogId
        Toast.makeText(context, "Catalog ID: ${args.catalogId}", Toast.LENGTH_LONG).show()


        /** Navigate to detail by Id **/
        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    ItemListFragmentDirections.actionItemListFragmentToDetailFragment(it)
                )
                viewModel.onNavigationComplete()
            }
        })
        return binding.root
    }

    /**Remove _binding when fragment is destroy**/
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}