package com.ajaring_kotlin.util

import android.app.Dialog;
import android.content.Context
import android.view.Window
import com.ajaring_kotlin.R

/**
 * Created by Ashish on 31/5/19.
 */

class ProgressLoader(context: Context) {

    lateinit var dialog: Dialog
    var mContext: Context

    init {
        this.mContext = context
    }

    fun showProgress() {
        // TODO Auto-generated method stub
        dialog = Dialog(mContext, android.R.style.Theme_Translucent)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //here we set layout of progress dialog
        dialog.setContentView(R.layout.dialog_common_progress)
//        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

    }

    fun dismissProgress() {
        if (dialog != null) {
            dialog.dismiss()
        }
    }
}