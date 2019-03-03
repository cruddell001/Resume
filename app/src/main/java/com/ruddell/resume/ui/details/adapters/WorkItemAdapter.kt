package com.ruddell.resume.ui.details.adapters

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ruddell.resume.R
import com.ruddell.resume.database.repositories.ContentRepository
import com.ruddell.resume.models.WorkExperience
import kotlinx.android.synthetic.main.card_work_item.view.*

class WorkItemAdapter(
    context: Context, lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<WorkItemAdapter.ViewHolder>() {

    var workItems:List<WorkExperience>? = null
    val onClickListener = View.OnClickListener {
        val item = it.tag as? WorkExperience

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
            holder.titleView.text = item.position
            holder.subtitleView.text = item.companyName
            holder.rightLabel.text = item.dates
            holder.detailsView.text = item.description
            with(holder.mView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }
    }

    override fun getItemCount(): Int = workItems?.size ?: 0

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val titleView: TextView = mView.title
        val subtitleView: TextView = mView.subTitle
        val rightLabel : TextView = mView.rightLabel
        val detailsView : TextView = mView.detailsView

        override fun toString(): String {
            return super.toString() + " '" + subtitleView.text + "'"
        }
    }

}