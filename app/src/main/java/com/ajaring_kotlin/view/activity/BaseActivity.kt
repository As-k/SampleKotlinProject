package com.ajaring_kotlin.view.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import butterknife.ButterKnife


/**
 * Created by Ashish on 1/4/19.
 */

abstract class BaseActivity : AppCompatActivity() {

    private val TAG: String = "BaseActivity"
    protected abstract fun getLayoutResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: $localClassName")
        try {
            setContentView(getLayoutResource())
            ButterKnife.bind(this)
        } catch (e : Exception){
            e.printStackTrace();
        }
    }

    fun replaceFragment(id: Int, fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager;
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment);
        //   fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    fun addFragment(id: Int, fragment: Fragment) {
//        val fragmentManager = supportFragmentManager
        //  fragmentManager.popBackStack();  //it will clear all fragment from stack
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(id, fragment)
        fragmentTransaction.addToBackStack("")
        fragmentTransaction.commit()
    }

    fun addFragment1(id: Int, fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        //  fragmentManager.popBackStack();  //it will clear all fragment from stack
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(id, fragment)
        //        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit()
    }

}