package com.zaus_app.moviefrumy_new.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zaus_app.moviefrumy_new.R
import com.zaus_app.moviefrumy_new.databinding.ActivityMainBinding
import com.zaus_app.moviefrumy_new.view.fragments.BallFragment
import com.zaus_app.moviefrumy_new.view.fragments.FavoritesFragment
import com.zaus_app.moviefrumy_new.view.fragments.HomeFragment
import com.zaus_app.moviefrumy_new.view.fragments.NotificationsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(HomeFragment(),"home")
        initNavigation()
    }

    private fun checkFragmentExistence(tag: String): Fragment? = supportFragmentManager.findFragmentByTag(tag)

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .commit()
    }

    private fun initNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val tag = "home"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: HomeFragment(), tag)
                    true
                }
                R.id.favorites -> {
                    val tag = "favorites"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: FavoritesFragment(), tag)
                    true
                }
                R.id.notifications -> {
                    val tag = "watch_later"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: NotificationsFragment(), tag)
                    true
                }
                R.id.ball -> {
                    val tag = "selections"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: BallFragment(), tag)
                    true
                }
                else -> false
            }
        }
    }
}