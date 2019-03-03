package com.ruddell.resume.ui.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruddell.resume.R
import com.ruddell.resume.ui.Details
import com.ruddell.resume.ui.MainActivity
import com.ruddell.resume.ui.details.adapters.EducationItemAdapter
import com.ruddell.resume.ui.details.adapters.SkillsItemAdapter
import com.ruddell.resume.ui.details.adapters.WorkItemAdapter
import com.ruddell.resume.ui.details.dummy.DummyContent
import com.ruddell.resume.ui.details.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

    lateinit var details:Details

    val icon:Drawable?
        get() {
        val context = context ?: return null
        return when (details) {
            Details.WORK_EXPERIENCE -> context.resources.getDrawable(R.drawable.work_icon, null)
            Details.EDUCATION -> context.resources.getDrawable(R.drawable.education_icon, null)
            Details.SKILLS -> context.resources.getDrawable(R.drawable.skills_icon, null)
            Details.ABOUT -> context.resources.getDrawable(R.drawable.info_icon, null)
        }
    }

    val title:String?
    get() {
        val context = context ?: return null
        return when (details) {
            Details.WORK_EXPERIENCE -> context.resources.getString(R.string.work_experience)
            Details.EDUCATION -> context.resources.getString(R.string.education)
            Details.SKILLS -> context.resources.getString(R.string.skills)
            Details.ABOUT -> context.resources.getString(R.string.about)
        }
    }

    companion object {
        const val TAG = "DetailsFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iconView?.setImageDrawable(icon)
        titleView?.text = title

        iconView?.setOnClickListener { (activity as? MainActivity)?.onBackPressed() }

        recyclerView?.let {
            Log.d(TAG, "setting adapter...")
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = when (details) {
                Details.WORK_EXPERIENCE -> WorkItemAdapter(view.context, this@DetailsFragment)
                Details.EDUCATION -> EducationItemAdapter(view.context, this@DetailsFragment)
                Details.SKILLS -> SkillsItemAdapter(view.context, this@DetailsFragment)
                else -> MyItemRecyclerViewAdapter(DummyContent.ITEMS)
            }
        }

    }


}
