package com.ajaring_kotlin.view.activity

import android.os.Bundle
import com.ajaring_kotlin.R
import com.ajaring_kotlin.view.fragment.LoginFragment
import kotlinx.android.synthetic.main.*

class MainActivity : BaseActivity() {

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(R.id.main_contain, LoginFragment())
    }
}
