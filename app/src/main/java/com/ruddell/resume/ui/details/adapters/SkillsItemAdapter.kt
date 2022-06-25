package com.ruddell.resume.ui.details.adapters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ruddell.resume.R
import com.ruddell.resume.database.repositories.ContentRepository
import com.ruddell.resume.extensions.constraintSet
import com.ruddell.resume.models.Skills

class SkillsItemAdapter(
    val context: Context, lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<SkillsItemAdapter.ViewHolder>() {


    var expandedItem : Int = -1

    var skillItems:List<Skills>? = null
    val onClickListener = View.OnClickListener {
        val holder = it.tag as? ViewHolder
        holder?.let {
            if (it.isExpanded) collapseItem(it)
            else expandItem(it)
        }
    }

    fun expandItem(holder:ViewHolder) {
        val root = holder.constraintLayout
        val constraintSet = R.layout.card_skills_item_expanded.constraintSet(context)
        TransitionManager.beginDelayedTransition(root)
        constraintSet.applyTo(root)
        holder.isExpanded = true
    }

    fun collapseItem(holder:ViewHolder) {
        val root = holder.constraintLayout
        val constraintSet = R.layout.card_skills_item_collapsed.constraintSet(context)
        TransitionManager.beginDelayedTransition(root)
        constraintSet.applyTo(root)
        holder.isExpanded = false
    }

    init {
        ContentRepository.getSkillItems(context)?.observe(lifecycleOwner, Observer {
            skillItems = it
            notifyDataSetChanged()
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_skills_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        skillItems?.get(position)?.let {item ->
            holder.item = item
            holder.titleView.text = item.categoryName
            holder.detailsView.text = getDescription(item)
            if (holder.isExpanded) collapseItem(holder)
            with(holder.mView) {
                tag = holder
                setOnClickListener(onClickListener)
            }
        }
    }

    fun getDescription(skill:Skills) : String {
        var description = ""
        skill.itemNames.forEachIndexed {index,item ->
            if (index>0) description += "\n"
            description += "• $item"
        }
        return description
    }

    override fun getItemCount(): Int = skillItems?.size ?: 0

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var item:Skills? = null
        val constraintLayout: ConstraintLayout = mView.findViewById(R.id.constraintLayout)
        val titleView: TextView = mView.findViewById(R.id.title)
        val detailsView : TextView = mView.findViewById(R.id.detailsView)
        var isExpanded = false

        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }

}