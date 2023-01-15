package com.example.taskapp

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskapp.databinding.ActivityMainBinding
import com.example.taskapp.ui.dashboard.DashboardFragment
import com.example.taskapp.ui.home.HomeFragment
import com.example.taskapp.ui.notifications.NotificationsFragment
import com.example.taskapp.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var bottomNavigationView: BottomNavigationView
//    private lateinit var menu: MenuItem
//    private lateinit var home: HomeFragment
//    private lateinit var n: NotificationsFragment
//    private lateinit var d: DashboardFragment
//    private lateinit var profile: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.taskFragment,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            navView.isVisible = destination.id != R.id.taskFragment
        }
//        BottomNavigationView.OnNavigationItemReselectedListener {
//            when (menu.itemId) {
//                R.id.navigation_home -> home
//                R.id.navigation_dashboard -> d
//                R.id.navigation_notifications -> n
//                R.id.navigation_profile -> profile
//
//            }
//        }
    }

}