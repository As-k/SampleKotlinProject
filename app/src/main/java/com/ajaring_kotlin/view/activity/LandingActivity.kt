package com.ajaring_kotlin.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.view.View
import butterknife.BindView
import com.ajaring_kotlin.R
import com.ajaring_kotlin.view.fragment.HomeFragment

class LandingActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_landing
    }

    @BindView(R.id.navigation)
    lateinit var navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragment = HomeFragment()
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .add(R.id.container_landing, fragment)
            .commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = { item : MenuItem ->
        when (item.getItemId()) {
            R.id.navigation_home -> {
                val fragment = supportFragmentManager
                    .findFragmentById(R.id.container_landing)
                if (fragment !is HomeFragment) {
                    val selectedFragment = HomeFragment()
                    replaceFragment(R.id.container_landing, selectedFragment)
                }
            }
            R.id.navigation_booking -> {

                val fragment1 = supportFragmentManager
                    .findFragmentById(R.id.container_landing)
//                if (fragment1 !is MyBookingFragment) {
//                    val selectedFragment2 = MyBookingFragment()
//                    replaceFragment(R.id.container_landing, selectedFragment2)
//                }
            }
            R.id.navigation_favorite -> {

                val fragment2 = supportFragmentManager
                    .findFragmentById(R.id.container_landing)
//                if (fragment2 !is MyFavoriteFragment) {
//                    val selectedFragment3 = MyFavoriteFragment()
//                    replaceFragment(R.id.container_landing, selectedFragment3)
//                }
            }
            R.id.navigation_more -> {

                val fragment3 = supportFragmentManager
                    .findFragmentById(R.id.container_landing)
//                if (fragment3 !is MoreFragment) {
//                    val selectedFragment4 = MoreFragment()
//                    addFragment(R.id.container_landing, selectedFragment4)
//                }
            }
        }

        true
    }
}
