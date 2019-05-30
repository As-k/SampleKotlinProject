package com.ajaring_kotlin.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import butterknife.BindView
import com.ajaring_kotlin.util.ApplicationPreferences
import com.ajaring_kotlin.R
import com.ajaring_kotlin.util.AppContents
import com.ajaring_kotlin.view.fragment.LoginFragment
import org.jetbrains.annotations.Nullable
import pl.droidsonroids.gif.GifImageView

class MainActivity : BaseActivity() {

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    @Nullable
    @BindView(R.id.splashImage)
    lateinit var splashImage: GifImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferenceStorage = ApplicationPreferences(applicationContext)
        preferenceStorage.setStringData(AppContents().LANGUAGE, AppContents().ENGLISH)
        Handler().postDelayed({
            splashImage.setVisibility(View.GONE)
            if(preferenceStorage.getStringData(AppContents().AUTH_KEY).equals(""))
                replaceFragment(R.id.main_contain, LoginFragment())
            else {
                startActivity(Intent(this@MainActivity, LandingActivity::class.java))
                finish()
            }
        }, 3000)

    }
}
