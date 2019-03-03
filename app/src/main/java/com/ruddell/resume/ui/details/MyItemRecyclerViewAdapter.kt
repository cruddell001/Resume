package com.ruddell.resume.ui.details

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ruddell.resume.R



import com.ruddell.resume.ui.details.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.card_work_item.view.*


class MyItemRecyclerViewAdapter(
    private val mValues: List<DummyItem>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_work_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id
        holder.mContentView.text = item.content
        Log.d("RecyclerViewAdapter", "onBind(${item.content})")

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.findViewById(R.id.title)
        val mContentView: TextView = mView.findViewById(R.id.subTitle)
        val rightLabel : TextView = mView.findViewById(R.id.rightLabel)

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
