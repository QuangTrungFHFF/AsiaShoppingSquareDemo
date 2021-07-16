package com.asiasquare.byteg.shoppingdemo

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.databinding.ActivityMainBinding
import shark.LibraryLeakReferenceMatcher

class MainActivity : AppCompatActivity() {

    //Using viewBinding for activity
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this)
        ViewModelProvider(this, MainViewModel.Factory(activity.application))
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Setup navigation controller
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)



        viewModel.itemCount.observe(this, Observer  {
            //override fun onChanged (){
                binding.bottomNav.getOrCreateBadge(R.id.favoriteFragment).apply {
                    backgroundColor = resources.getColor(R.color.secondary_800)
                    badgeTextColor = resources.getColor(R.color.white)
                    maxCharacterCount = 3
                    if (viewModel.itemCount.value != null ) {
                        number = viewModel.itemCount.value!! // should be change
                        isVisible = true
                    } else {
                        isVisible = false
                    }
                }
            //}

        })


    }





}





