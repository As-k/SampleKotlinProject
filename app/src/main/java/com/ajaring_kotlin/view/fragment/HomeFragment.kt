package com.ajaring_kotlin.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.ajaring_kotlin.R
import org.jetbrains.annotations.Nullable


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseFragment() {
    val TAG = HomeFragment::class.java.name

    override fun getLayoutResource(): Int {
        return R.layout.fragment_home
    }
    @Nullable
    @BindView(R.id.text_toolbar)
    lateinit var toolbar: TextView

    @Nullable
    @OnClick(R.id.action_loc)
    internal fun action_loc() {
        val fragment = SearchingDialogFragment()
        addFragment(R.id.container_landing, fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setText(resources.getString(R.string.title_home))
    }


}
