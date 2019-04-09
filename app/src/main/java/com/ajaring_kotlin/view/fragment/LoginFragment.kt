package com.ajaring_kotlin.view.fragment


import android.os.Bundle
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.OnClick
import com.ajaring_kotlin.ApiClient
import com.ajaring_kotlin.ApiInterface
import com.ajaring_kotlin.ApplicationPreferences

import com.ajaring_kotlin.R
import com.ajaring_kotlin.model.Login
import com.ajaring_kotlin.model.StandardResult
import com.ajaring_kotlin.presenter.LoginListener
import com.ajaring_kotlin.presenter.LoginPresenter
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.jetbrains.annotations.Nullable
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : BaseFragment(), LoginListener {

    val TAG: String = "LoginFragment"


    @Nullable
    @BindView(R.id.email_id)
    lateinit var emailIdText: EditText

    @Nullable
    @BindView(R.id.password)
    lateinit var passwordText: EditText

    @OnClick(R.id.new_registration_action)
    internal fun newRegistrationAction() {
        val fragment = RegistrationFragment()
        replaceFragment(R.id.main_contain, fragment)
    }


    @OnClick(R.id.action_login)
    internal fun loginAction() {
        val emailId = emailIdText.getText().toString()
        if (emailId.length == 0) {
            showToast(resources.getString(R.string.please_enter_email_id))
        } else if (emailId.length != 0 && !application.isValidEmail(emailId)) {
            showToast(resources.getString(R.string.please_enter_a_valid_email_id))
        } else if (passwordText.getText().toString().length == 0) {
            showToast(resources.getString(R.string.please_enter_password))
        } else if (passwordText.getText().toString().length < 5) {
            showToast(resources.getString(R.string.password_cannot_be_less_than_6_characters))
        } else {
            login(
                emailIdText.text.toString(),
                passwordText.text.toString(), "1",
                application.androidId(mContext)
            )
        }
    }

    private fun login(email: String, password: String, device_type: String, device_id: String) {
        val hashMap: HashMap<String, String> = HashMap<String, String>()
        hashMap.put("email", email)
        hashMap.put("password", password)
        hashMap.put("device_type", device_type)
        hashMap.put("device_id", device_id)
        hashMap.put("language", "english")
        val apiInterface = ApiClient(mContext).getServiceApi()
        val standardResult: Call<StandardResult> = apiInterface.callLoginApi(hashMap)
        standardResult.enqueue(object : Callback<StandardResult> {
            override fun onFailure(call: Call<StandardResult>, t: Throwable) {
                showToast("onFailure")
            }

            override fun onResponse(call: Call<StandardResult>, response: Response<StandardResult>) {
                if (response.isSuccessful) {
                    val jsonElement = response.body()!!.data
                    if (jsonElement is JsonObject) {
                        val userData: Login = Gson().fromJson<Any>(jsonElement, Login::class.java) as Login
                        val preferenceStorage = ApplicationPreferences(mContext)
                        preferenceStorage.setStringData("name", userData.name!!)
                    }
                    showToast("Success")
                }
            }

        })
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_login
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        loginPresenter!!.invoke(mContext)
    }

    override fun onSentRequest() {
        showToast("onSentRequest")
    }

    override fun onSuccess(status: Boolean?, message: String?, data: JsonElement?) {
        showToast("Success")
    }

    override fun onPendingVerified(status: Boolean, message: String, mobile: String, isVerified: Boolean) {
    }

    override fun onFailure() {
        showToast("onFailure")
    }


}
