package com.ajaring_kotlin.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ajaring_kotlin.R

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchingDialogFragment : BaseFragment() {
    override fun getLayoutResource(): Int {
        /*TODO("not implemented") //To change body of created functions use File | Settings | File Templates.*/
        return R.layout.fragment_searching_dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}
