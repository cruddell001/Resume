package com.ruddell.resume.ui.details

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruddell.resume.R
import com.ruddell.resume.ui.Details
import com.ruddell.resume.ui.details.adapters.WorkItemAdapter

import com.ruddell.resume.ui.details.dummy.DummyContent
import com.ruddell.resume.ui.details.dummy.DummyContent.DummyItem

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [DetailsFragment.OnListFragmentInteractionListener] interface.
 */
class DetailsFragment : Fragment() {

    lateinit var details:Details

    companion object {
        const val TAG = "DetailsFragment"

        fun newInstance(details: Details) : DetailsFragment{
            val fragment = DetailsFragment()
            fragment.details = details
            return fragment
        }
    }

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        Log.d(TAG, "onCreateView()")

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                Log.d(TAG, "setting adapter...")
                layoutManager = LinearLayoutManager(context)
                adapter = when (details) {
                    Details.WORK_EXPERIENCE -> WorkItemAdapter(view.context, this@DetailsFragment)
                    else -> MyItemRecyclerViewAdapter(DummyContent.ITEMS, listener)
                }
            }
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }

}
