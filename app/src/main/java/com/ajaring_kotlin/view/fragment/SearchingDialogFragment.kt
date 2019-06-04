package com.ajaring_kotlin.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.ajaring_kotlin.R
import com.ajaring_kotlin.presenter.SearchingCarListener
import com.ajaring_kotlin.presenter.SearchingCarPresenter
import com.ajaring_kotlin.util.Utils
import com.google.gson.JsonElement
import org.jetbrains.annotations.Nullable

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchingDialogFragment : BaseFragment(), SearchingCarListener, SearchPlacesFragment.NotifyPlaces {

    val TAG: String by lazy { "SearchingDialogFragment" }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_searching_dialog
    }

    @Nullable
    @OnClick(R.id.action_close)
    internal fun actionClose() {
        backPress(R.id.container_landing)
    }

    @Nullable
    @OnClick(R.id.action_search)
    internal fun action_search() {
        if (locationText.text.toString() != resources.getString(R.string.enter_location)) {
            if (calenderText.text.toString() != resources.getString(R.string.enter_date))
                searchCar(
                    locationText.text.toString(), pickLat, pickLng, "", "", "",
                    pickDateStr, pickTimeStr, dropDateStr, dropTimeStr
                )
            else
                showToast(resources.getString(R.string.please_enter_date_time))
        } else {
            showToast(resources.getString(R.string.please_enter_location))
        }
    }

    @Nullable
    @BindView(R.id.action_calender)
    lateinit var calenderText: TextView
    @Nullable
    @BindView(R.id.action_book_now)
    lateinit var buttonBookNow: Button
    @Nullable
    @BindView(R.id.action_search)
    lateinit var buttonSearch: Button
    @Nullable
    @BindView(R.id.action_location)
    lateinit var locationText: TextView

    var flag: Int = 0
    var fragmentFlag: Int = 0
    var days: Int = 0

    lateinit var address: String;
    lateinit var pickLat: String;
    lateinit var pickLng: String;
    lateinit var pickDateStr: String;
    lateinit var dropDateStr: String
    lateinit var pickTimeStr: String;
    lateinit var dropTimeStr: String
//    private val searchedCar: SearchedCar()


    @OnClick(R.id.action_location)
    internal fun actionLocation() {
        addFragment(R.id.container_landing, SearchPlacesFragment().newInstance(this, 1))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (fragmentFlag == 1) {
            buttonBookNow.setVisibility(View.VISIBLE)
            buttonSearch.setVisibility(View.GONE)
        } else {
            buttonBookNow.setVisibility(View.GONE)
            buttonSearch.setVisibility(View.VISIBLE)
        }
    }

    private fun searchCar(
        pickAdd: String, pickLat: String, pickLng: String,
        dropAdd: String, dropLat: String, dropLng: String,
        pickDateStr1: String, pickTime: String, dropDateStr1: String, dropTime: String
    ) {
        val hashMap: HashMap<String, String> = hashMapOf()
        hashMap.put("pick_location", pickAdd)
        hashMap.put("pick_lat", pickLat)
        hashMap.put("pick_long", pickLng)
        hashMap.put("drop_location", dropAdd)
        hashMap.put("drop_lat", dropLat)
        hashMap.put("drop_long", dropLng)
        hashMap.put("pick_date", pickDateStr1)
        hashMap.put("pick_time", pickTime)
        hashMap.put("drop_date", dropDateStr1)
        hashMap.put("drop_time", dropTime)
        hashMap.put("language", "english")
        if (Utils.isNetworkAvailable(mContext))
            SearchingCarPresenter(mContext, this).searchingCar(hashMap)
        else
            showToast("No Internet Connection!")
    }


    override fun onSuccess(status: Boolean?, message: String?, data: JsonElement?) {
        progressBar.showProgress()
    }

    override fun onFailure(errorMsg: String) {
        progressBar.dismissProgress()
        Log.e(TAG, errorMsg)
    }

    override fun onSentRequest() {
        progressBar.dismissProgress()
    }

    override fun notifyPlaces(lat: String, lng: String, address: String, flag: Int) {

    }


    override fun onDetach() {
        super.onDetach()
        progressBar.dismissProgress()
    }

}
