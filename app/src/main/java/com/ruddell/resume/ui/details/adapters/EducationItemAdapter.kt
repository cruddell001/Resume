package com.ruddell.resume.ui.details.adapters

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ruddell.resume.R
import com.ruddell.resume.database.repositories.ContentRepository
import com.ruddell.resume.models.Education
import com.ruddell.resume.ui.MainActivity


class EducationItemAdapter(
    val context: Context, lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<EducationItemAdapter.ViewHolder>() {

    companion object {
        const val TAG = "WorkItemAdapter"
    }

    var educationItems:List<Education>? = null
    val onClickListener = View.OnClickListener {
        val holder = it.tag as? ViewHolder
        //launch website
        launchWebsite(holder?.item?.website)
    }

    fun launchWebsite(url:String?) {
        url ?: return
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        (context as? MainActivity)?.startActivity(browserIntent)
    }

    init {
        ContentRepository.getEducationItems(context)?.observe(lifecycleOwner, Observer {
            educationItems = it
            notifyDataSetChanged()
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_education_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        educationItems?.get(position)?.let {item ->
            holder.item = item
            holder.titleView.text = item.degreeName
            holder.subtitleView.text = item.schoolName
            holder.dateView.text = item.graduationDate
            holder.website = item.website
            if (item.gpa!=null) holder.gpaView.text = "${context.resources.getString(R.string.GPA)} ${item.gpa}"
            holder.gpaView.visibility = if (item.gpa==null) View.GONE else View.VISIBLE

            with(holder.mView) {
                tag = holder
                setOnClickListener(onClickListener)
            }
        }
    }

    override fun getItemCount(): Int = educationItems?.size ?: 0

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var item:Education? = null
        val titleView: TextView = mView.findViewById(R.id.titleView)
        val subtitleView: TextView = mView.findViewById(R.id.subTitle)
        val dateView : TextView = mView.findViewById(R.id.dateView)
        val gpaView : TextView = mView.findViewById(R.id.gpaView)
        var website:String? = null

        override fun toString(): String {
            return super.toString() + " '" + subtitleView.text + "'"
        }
    }

}