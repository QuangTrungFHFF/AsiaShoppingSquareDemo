package com.asiasquare.byteg.shoppingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.asiasquare.byteg.shoppingdemo.databinding.ActivityMainBinding
import com.asiasquare.byteg.shoppingdemo.favorite.FavoriteFragmentViewModel

class MainActivity : AppCompatActivity() {

    //Using viewBinding for activity
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: FavoriteFragmentViewModel by lazy {
            val activity = requireNotNull(this)
            ViewModelProvider(this, FavoriteFragmentViewModel.Factory(activity.application))
                .get(FavoriteFragmentViewModel::class.java)
        }

        val items= viewModel.getCountFavorite()

        //Setup navigation controller
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        binding.bottomNav.getOrCreateBadge(R.id.favoriteFragment).apply {
            backgroundColor = resources.getColor(R.color.secondary_800)
            badgeTextColor = resources.getColor(R.color.white)
            maxCharacterCount = 3
            if (items!=null) {
                number = items // should be change
                isVisible = true
            } else {
                isVisible = false}
        }

    }
}