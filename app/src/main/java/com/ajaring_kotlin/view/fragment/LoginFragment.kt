package com.ajaring_kotlin.view.fragment


import android.content.Intent
import android.util.Log
import android.widget.EditText
import butterknife.BindView
import butterknife.OnClick
import com.ajaring_kotlin.R
import com.ajaring_kotlin.presenter.LoginListener
import com.ajaring_kotlin.presenter.LoginPresenter
import com.ajaring_kotlin.view.activity.LandingActivity
import com.ajaring_kotlin.view.activity.MainActivity
import com.google.gson.JsonElement
import org.jetbrains.annotations.Nullable
import java.lang.Error


class LoginFragment : BaseFragment(), LoginListener {

    val TAG: String by lazy { "LoginFragment" }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_login
    }

    var loginPresenter = context?.let { LoginPresenter(it, this) }

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
        val hashMap: HashMap<String, String> = hashMapOf()
        hashMap.put("email", email)
        hashMap.put("password", password)
        hashMap.put("device_type", device_type)
        hashMap.put("device_id", device_id)
        hashMap.put("language", "english")
        LoginPresenter(mContext, this).loginRequest(hashMap)
        /*val apiInterface = ApiClient(mContext).getServiceApi()
        val standardResult: Call<StandardResult> = apiInterface.callLoginApi(hashMap)
        standardResult.enqueue(object : Callback<StandardResult> {
            override fun onFailure(call: Call<StandardResult>, t: Throwable) {
                Log.e(TAG, "onFailure" + t.toString())
                showToast("onFailure" + t.toString())
            }

            override fun onResponse(call: Call<StandardResult>, response: Response<StandardResult>) {
                if (response.isSuccessful) {
                    val jsonElement = response.body()?.data
                    if (jsonElement is JsonObject) {
                        val userData: Login = Gson().fromJson<Any>(jsonElement, Login::class.java) as Login
                        val preferenceStorage = ApplicationPreferences(mContext)
                        Log.e(TAG, userData.toString())
                        showToast("Success")
                        userData.name?.let { preferenceStorage.setStringData(AppContents().NAME, userData.name) }
                        userData.authKey?.let { preferenceStorage.setStringData(AppContents().AUTH_KEY, userData.authKey) }
                    }
                }
            }

        })*/
    }

    override fun onSentRequest() {
        showToast("onSentRequest")
        Log.d(TAG, "onSentRequest")
    }

    override fun onSuccess(status: Boolean?, message: String?, data: JsonElement?) {
        startActivity(Intent(activity, LandingActivity::class.java))
        activity!!.finish()
    }

    override fun onFailure(errorMsg: String) {
        showToast("onFailure")
        Log.d(TAG, "onFailure: "+errorMsg)
    }


}
