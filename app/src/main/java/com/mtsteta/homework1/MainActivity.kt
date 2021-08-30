package com.mtsteta.homework1

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.fragment_container)

        val bdUpdateWorker: PeriodicWorkRequest = PeriodicWorkRequestBuilder<BdUpdateWorker>(
            24, TimeUnit.HOURS).build()
        WorkManager.getInstance(this).enqueue(bdUpdateWorker)

        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_menu_home -> {
                    navController.navigate(R.id.movieListFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_menu_user -> {
                    navController.navigate(R.id.userInfoFragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.logInFragment) {
                bottomNavigationView.visibility = View.INVISIBLE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}