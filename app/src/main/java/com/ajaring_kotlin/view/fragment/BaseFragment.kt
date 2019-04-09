package com.ajaring_kotlin.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife
import com.ajaring_kotlin.ApplicationPreferences
import com.ajaring_kotlin.MainApplication


/**
 * Created by Ashish on 1/4/19.
 */

abstract class BaseFragment: Fragment() {

    internal lateinit var view: View
    protected abstract fun getLayoutResource(): Int

    lateinit var application: MainApplication
    lateinit var applicationPreferences: ApplicationPreferences
    lateinit var mContext:Context

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext= context!!

        application = activity!!.application as MainApplication
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        view = inflater.inflate(getLayoutResource(), container, false)
        ButterKnife.bind(this, view)
        applicationPreferences = ApplicationPreferences(mContext)
        return view
    }

    fun replaceFragment(id: Int, fragment: Fragment) {
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(id, fragment)
        fragmentTransaction.addToBackStack("")
        fragmentTransaction.commit()
    }

    fun replaceFragment1(id: Int, fragment: Fragment) {
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(id, fragment)
        //        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit()
    }

    fun addFragment(id: Int, fragment: Fragment) {
        val fragmentManager = activity!!.supportFragmentManager
        //        fragmentManager.popBackStack();  //it will clear all fragment from stack
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(id, fragment)
        fragmentTransaction.addToBackStack("")
        fragmentTransaction.commit()
    }

    fun backPress(layout: Int) {
        val fm = activity!!.supportFragmentManager
        fm.findFragmentById(layout)
        fm.popBackStack()
    }

    fun showToast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }

}