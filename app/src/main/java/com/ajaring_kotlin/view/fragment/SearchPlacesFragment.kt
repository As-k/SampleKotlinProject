package com.ajaring_kotlin.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import com.ajaring_kotlin.R
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import org.jetbrains.annotations.Nullable

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [SearchPlacesFragment.OnListFragmentInteractionListener] interface.
 */
class SearchPlacesFragment : BaseFragment(), MySearchPlacesRecyclerViewAdapter.PlaceAutoCompleteInterface {
    override fun getLayoutResource(): Int {
        return R.layout.fragment_searchplaces_list
    }

    // TODO: Customize parameters
    private var columnCount = 1
    private var flag: Int = 0

    lateinit var notifyPlaces: NotifyPlaces
    lateinit var mGoogleApiClient: GoogleApiClient
    private val BOUNDS_INDIA = LatLngBounds(
        LatLng(-0.0, 0.0), LatLng(0.0, 0.0)
    )

    fun newInstance(notifyPlaces: NotifyPlaces, flag: Int): SearchPlacesFragment {
        this.notifyPlaces = notifyPlaces
        this.flag = flag
        return SearchPlacesFragment()
    }

    @Nullable
    @BindView(R.id.list)
    lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        with(view) {
//            layoutManager = when {
//                columnCount <= 1 -> LinearLayoutManager(context)
//                else -> GridLayoutManager(context, columnCount)
//            }
//            adapter =
//                activity?.let { MySearchPlacesRecyclerViewAdapter(it, mGoogleApiClient, BOUNDS_INDIA, null, this) }
//        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NotifyPlaces) {
            notifyPlaces = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }


    override fun onPlaceClick(mResultList: MySearchPlacesRecyclerViewAdapter.PlaceAutocomplete) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface NotifyPlaces {
        fun notifyPlaces(lat: String, lng: String, address: String, flag: Int)
    }

}
