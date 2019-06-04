package com.ajaring_kotlin.view.fragment

import android.support.constraint.ConstraintLayout
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ajaring_kotlin.R


import com.ajaring_kotlin.view.fragment.dummy.DummyContent.DummyItem
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.places.AutocompleteFilter
import com.google.android.gms.maps.model.LatLngBounds

import kotlinx.android.synthetic.main.fragment_searchplaces.view.*
import java.util.*
import kotlin.collections.ArrayList
import com.ajaring_kotlin.view.fragment.MySearchPlacesRecyclerViewAdapter.PlaceAutoCompleteInterface as PlaceAutoCompleteInterface1

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MySearchPlacesRecyclerViewAdapter(
    var context: FragmentActivity, var googleApiClient: GoogleApiClient,
    var mBounds: LatLngBounds, var filter: AutocompleteFilter?, var placeAutoCompleteInterface: PlaceAutoCompleteInterface
) : RecyclerView.Adapter<MySearchPlacesRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    interface PlaceAutoCompleteInterface {
        fun onPlaceClick(mResultList: PlaceAutocomplete)
    }

    lateinit var mResultList: ArrayList<PlaceAutocomplete>

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
//            placeAutoCompleteInterface.onPlaceClick()
        }

    }


    /**
     * Sets the bounds for all subsequent queries.
     */
    fun setBounds(bounds: LatLngBounds) {
        mBounds = bounds
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_searchplaces, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = mValues[position]
//        holder.mAddressView.text = item.content
//
//        with(holder.mView) {
//            tag = item
//            setOnClickListener(mOnClickListener)
//        }
    }
    /*
    Clear List items
     */
    fun clearList() {
        if (mResultList.size > 0) {
            mResultList.clear()
        }
    }
    override fun getItemCount(): Int = mResultList.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mAddressView: TextView = mView.address
        val mConstraintLayoutView: ConstraintLayout = mView.predictedRow

        override fun toString(): String {
            return super.toString() + " '" + mAddressView.text + "'"
        }
    }

    /**
     * Holder for Places Geo Data Autocomplete API results.
     */
    inner class PlaceAutocomplete internal constructor(var placeId: CharSequence, var description: CharSequence) {

        override fun toString(): String {
            return description.toString()
        }
    }
}
