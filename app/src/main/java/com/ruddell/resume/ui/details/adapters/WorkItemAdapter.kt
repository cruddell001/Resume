package com.ruddell.resume.ui.details.adapters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ruddell.resume.R
import com.ruddell.resume.database.repositories.ContentRepository
import com.ruddell.resume.extensions.constraintSet
import com.ruddell.resume.models.WorkExperience
import kotlinx.android.synthetic.main.card_work_item.view.*

class WorkItemAdapter(
    val context: Context, lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<WorkItemAdapter.ViewHolder>() {

    companion object {
        const val TAG = "WorkItemAdapter"
    }

    var expandedItem : Int = -1

    var workItems:List<WorkExperience>? = null
    val onClickListener = View.OnClickListener {
        val holder = it.tag as? ViewHolder
        holder?.let {
            if (it.isExpanded) collapseItem(it)
            else expandItem(it)
        }
    }

    fun expandItem(holder:ViewHolder) {
        val root = holder.constraintLayout
        val constraintSet = R.layout.card_work_item_expanded.constraintSet(context)
        TransitionManager.beginDelayedTransition(root)
        constraintSet.applyTo(root)
        holder.isExpanded = true
    }

    fun collapseItem(holder:ViewHolder) {
        val root = holder.constraintLayout
        val constraintSet = R.layout.card_work_item_collapsed.constraintSet(context)
        TransitionManager.beginDelayedTransition(root)
        constraintSet.applyTo(root)
        holder.isExpanded = false
    }

    init {
        ContentRepository.getWorkItems(context)?.observe(lifecycleOwner, Observer {
            workItems = it
            notifyDataSetChanged()
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_work_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        workItems?.get(position)?.let {item ->
            holder.item = item
            holder.titleView.text = item.position
            holder.subtitleView.text = item.companyName
            holder.rightLabel.text = item.dates
            holder.detailsView.text = item.description
            if (holder.isExpanded) collapseItem(holder)
            with(holder.mView) {
                tag = holder
                setOnClickListener(onClickListener)
            }
        }
    }

    override fun getItemCount(): Int = workItems?.size ?: 0

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var item:WorkExperience? = null
        val constraintLayout: ConstraintLayout = mView.findViewById(R.id.constraintLayout)
        val titleView: TextView = mView.findViewById(R.id.title)
        val subtitleView: TextView = mView.findViewById(R.id.subTitle)
        val rightLabel : TextView = mView.findViewById(R.id.rightLabel)
        val detailsView : TextView = mView.findViewById(R.id.detailsView)
        var isExpanded = false

        override fun toString(): String {
            return super.toString() + " '" + subtitleView.text + "'"
        }
    }

}