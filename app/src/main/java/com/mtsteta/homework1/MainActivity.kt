package com.mtsteta.homework1

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mtsteta.homework1.adapters.GenresAdapter
import com.mtsteta.homework1.adapters.MoviesAdapter
import com.mtsteta.homework1.dataSourceImpls.GenresDataSourceImpl
import com.mtsteta.homework1.dataSourceImpls.MoviesDataSourceImpl
import com.mtsteta.homework1.diffUtils.MovieDiffUtilCallback
import com.mtsteta.homework1.dto.GenreDto
import com.mtsteta.homework1.dto.MovieDto
import com.mtsteta.homework1.fragments.MovieListFragment
import com.mtsteta.homework1.fragments.UserInfoFragment
import com.mtsteta.homework1.listeners.GenreItemClickListener
import com.mtsteta.homework1.listeners.MovieItemClickListener
import com.mtsteta.homework1.models.GenresModel
import com.mtsteta.homework1.models.MoviesModel


class MainActivity : AppCompatActivity() {

    private var initialFragment: Fragment? = null
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            initialFragment = MovieListFragment.newInstance()
            initialFragment?.apply {
                supportFragmentManager.beginTransaction().add(R.id.fragment_container,
                    this, INITIAL_FRAGMENT_TAG).commit()
            }
        }
        else {
            initialFragment = supportFragmentManager.findFragmentByTag(INITIAL_FRAGMENT_TAG)
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_menu_home -> {
                    loadFragment(MovieListFragment.newInstance())
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_menu_user -> {
                    loadFragment(UserInfoFragment.newInstance())
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
            fragment).commit()
    }

    companion object {
        const val INITIAL_FRAGMENT_TAG = "InitialFragment"
    }
}