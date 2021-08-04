package com.asiasquare.byteg.shoppingdemo.detail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import coil.load
import com.asiasquare.byteg.shoppingdemo.R
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import com.asiasquare.byteg.shoppingdemo.database.items.ShoppingBasketItem
import com.asiasquare.byteg.shoppingdemo.databinding.FragmentDetailBinding


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
        val viewModelFactory = DetailFragmentViewModel.Factory(item, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailFragmentViewModel::class.java)


        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            ivCatalogGrid.load(item.itemImageSource)
            tenSanPham.text = item.itemName
            giaSanPham.text = "€"+item.itemPrice.toString()
            moTaSanPham.text = item.itemDescription
            khoiLuongSanPham.text = item.itemWeight
            sanPhamThuongHieu.text =item.itemBrand
            sanPhamXuatXu.text= item.itemOrigin
        }

        /** Create spinner button **/
        val amount = arrayListOf<Int>()
        for (i in 1..10) {amount.add(i)}

        val arrayAdapter =
            context?.let { ArrayAdapter(it, R.layout.spinner_item_custom,amount) }
        arrayAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter= arrayAdapter


        /** Change heart color: red if it's a favorite, black if it is not **/
        viewModel.isFavorite.observe(viewLifecycleOwner, {
            val checkFavorite = when(it){
                true -> R.drawable.timdo24
                else -> R.drawable.timden24
            }
            binding.ivFavorite.setImageResource(checkFavorite)
        })


        binding.ivFavorite.setOnClickListener {
            viewModel.onFavoriteClicking()
        }

        binding.buttonMua.setOnClickListener {
            viewModel.onCartClicking()
            Toast.makeText(context, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show()
        }


    }
}
