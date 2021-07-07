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

//class DetailFragment : Fragment() {
//    private var _binding : FragmentDetailBinding? = null
//    private val binding get() = _binding!!
//

//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        _binding = FragmentDetailBinding.inflate(inflater,container,false)
//
//        return binding.root
//        }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.buttonMua.setOnClickListener {
//            //
//        }
//
//    }
//    /**Remove _binding when fragment is destroy**/
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}

//private val viewModel: DetailFragmentViewModel by lazy {
//    val activity = requireNotNull(this.activity)
//    ViewModelProvider(this, DetailFragmentViewModel.Factory(activity.application))
//        .get(DetailFragmentViewModel::class.java)
//}
class DetailFragment : Fragment(){

private var _binding : FragmentDetailBinding? = null
private val binding get() = _binding!!

    private lateinit var viewModel: DetailFragmentViewModel

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var item: NetworkItem

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        _binding = FragmentDetailBinding.inflate(inflater,container,false)

        val dataSource = AsiaDatabase.getInstance(application).favoriteItemDao
        val itemProperty = DetailFragmentArgs.fromBundle(requireArguments()).selectedItem
        val viewModelFactory = DetailFragmentViewModel.Factory(dataSource,itemProperty, application)
        ViewModelProvider(
            this, viewModelFactory).get(DetailFragmentViewModel::class.java)

        item =args.selectedItem

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
        binding.timChiTietActivity.setOnClickListener {
            Toast.makeText(context, "tim clicked", Toast.LENGTH_SHORT).show()
            viewModel.onLikeImageClicking()
        }
    }
}
