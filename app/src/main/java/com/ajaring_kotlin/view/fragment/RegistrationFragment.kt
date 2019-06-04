package com.ajaring_kotlin.view.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.OnClick
import com.ajaring_kotlin.R
import com.ajaring_kotlin.presenter.LoginListener
import com.ajaring_kotlin.presenter.LoginPresenter
import com.ajaring_kotlin.util.Utils
import com.ajaring_kotlin.view.activity.LandingActivity
import com.google.gson.JsonElement
import org.jetbrains.annotations.Nullable


class RegistrationFragment : BaseFragment(), LoginListener {

    val TAG: String by lazy { "RegistrationFragment" }
    @Nullable
    @BindView(R.id.full_name)
    lateinit var fullName: EditText

    @Nullable
    @BindView(R.id.mobile_no)
    lateinit var mobileNumber: EditText

    @Nullable
    @BindView(R.id.email_id)
    lateinit var emailId: EditText

    @Nullable
    @BindView(R.id.password)
    lateinit var password: EditText

    @Nullable
    @BindView(R.id.conf_password)
    internal lateinit var confirmPassword: EditText

    @Nullable
    @BindView(R.id.already_login_action)
    internal lateinit var already_login_action: LinearLayout

    @OnClick(R.id.already_login_action)
    internal fun newRegistrationAction() {
        backPress(R.id.main_contain)
    }

    fun newInstace():RegistrationFragment{

        return RegistrationFragment()
    }

    @OnClick(R.id.action_signIn)
    internal fun registerUser() {
        if (fullName.text.length == 0) {
            showToast(resources.getString(R.string.please_enter_full_name))
        } else if (fullName.text.length < 3) {
            showToast(
                resources.getString(R.string.full_name_should_be_greater_than_3_characters)
            )
        } else if (emailId.getText().length == 0) {
            showToast(resources.getString(R.string.please_enter_email_id))
        } else if (emailId.getText().length != 0 && !application.isValidEmail(emailId.getText().toString())) {
            showToast(resources.getString(R.string.please_enter_a_valid_email_id))
        } else if (mobileNumber.text.length == 0) {
            showToast(resources.getString(R.string.please_enter_mobile_number))
        } else if (mobileNumber.text.length < 10) {
            showToast(resources.getString(R.string.please_enter_valid_mobile_number))
        } else if (password.getText().length == 0) {
            showToast(resources.getString(R.string.please_enter_password))
        } else if (password.getText().length < 6) {
            showToast(
                resources.getString(R.string.password_cannot_be_less_than_6_characters)
            )
        } else if (confirmPassword.getText().toString() != password.getText().toString()) {
            showToast(
                resources.getString(R.string.password_and_confirm_password_should_be_same)
            )
        } else {
//            showToast("registration success")
            registration(
                fullName.text.toString(),
                emailId.text.toString(),
                mobileNumber.text.toString(),
                password.text.toString(),
                "1", application.androidId(mContext)
            )
        }
    }

    private fun registration(name: String, email: String, mobileNumber: String,
                             password: String, deviceType: String, androidId: String) {
        val hashMap: HashMap<String, String> = hashMapOf()
        hashMap.put("name", name)
        hashMap.put("email", email)
        hashMap.put("mobile", mobileNumber)
        hashMap.put("password", password)
        hashMap.put("device_type", deviceType)
        hashMap.put("device_id", androidId)
        hashMap.put("language", "english")
        if (Utils.isNetworkAvailable(mContext))
            LoginPresenter(mContext, this).registractionRequest(hashMap)
        else
            showToast("No Internet Connection!")

    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_registration
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onSentRequest() {
        progressBar.showProgress()
    }

    override fun onSuccess(status: Boolean?, message: String?, data: JsonElement?) {
        progressBar.dismissProgress()
        startActivity(Intent(activity, LandingActivity::class.java))
        activity!!.finish()
    }

    override fun onFailure(errorMsg: String) {
        Log.d(TAG, "onFailure: " + errorMsg)
    }

}
