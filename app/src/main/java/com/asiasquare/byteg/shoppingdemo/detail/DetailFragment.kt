package com.asiasquare.byteg.shoppingdemo.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import coil.load
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import com.asiasquare.byteg.shoppingdemo.databinding.FragmentDetailBinding
import com.asiasquare.byteg.shoppingdemo.itemlist.ItemListFragmentViewModel


class DetailFragment : Fragment(){

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailFragmentViewModel

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var item: NetworkItem

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentDetailBinding.inflate(inflater,container,false)

        val application = requireNotNull(activity).application
        item = args.selectedItem
        val viewModelFactory = DetailFragmentViewModel.Factory(item,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailFragmentViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            ivCatalogGrid.load(item.itemImageSource)
            tenSanPham.text = item.itemName
            giaSanPham.text = item.itemPrice.toString()
            moTaSanPham.text = item.itemDescription
            khoiLuongSanPham.text = item.itemWeight
            sanPhamThuongHieu.text =item.itemBrand
            sanPhamXuatXu.text= item.itemOrigin

        }
        binding.ivFavorite.setOnClickListener {
            Toast.makeText(context, "tim clicked", Toast.LENGTH_SHORT).show()
            viewModel.onAddFavoriteClicking()
        }
    }
}
