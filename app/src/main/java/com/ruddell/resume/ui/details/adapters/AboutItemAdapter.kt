package com.ruddell.resume.ui.details.adapters

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ruddell.resume.R
import com.ruddell.resume.ui.about.AppDevelopmentFragment
import com.ruddell.resume.ui.about.ContactFragment
import com.ruddell.resume.ui.about.EmptyFragment
import com.ruddell.resume.ui.about.LicenseFragment
import java.util.*


class AboutItemAdapter(
    val context: Context, val fragmentManager: FragmentManager?
) : RecyclerView.Adapter<AboutItemAdapter.ViewHolder>() {

    companion object {
        const val ITEM_TYPE_NORMAL = 0
        const val ITEM_TYPE_DETAILS = 1
    }

    var itemTypeShown:ItemType? = null

    var aboutItems:List<AboutContent.AboutItem>? = null
    val onClickListener = View.OnClickListener {
        val holder = it.tag as? ViewHolder
        //show details
        if (holder?.itemType==itemTypeShown) itemTypeShown = ItemType.UNSET
        else itemTypeShown = holder?.itemType
        notifyItemChanged(aboutItems?.size ?: 0)
    }

    init {
        aboutItems = AboutContent().populate(context)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getLayout(viewType), parent, false)
        return ViewHolder(view)
    }

    fun getLayout(viewType: Int) = when (viewType) {
        ITEM_TYPE_NORMAL -> R.layout.card_about_item
        else -> R.layout.about_screen_details
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position<aboutItems?.size?:0) bindNormalItem(holder, position)
        else bindDetails(holder)
    }

    fun bindNormalItem(holder: ViewHolder, position: Int) {
        aboutItems?.get(position)?.let {item ->
            holder.item = item
            holder.titleView?.text = item.title
            holder.itemType = item.type

            with(holder.mView) {
                tag = holder
                setOnClickListener(onClickListener)
            }
        }
    }
    fun bindDetails(holder: ViewHolder) {
        Log.d("AboutAdapter", "bindDetails")
        val aboutItem = itemTypeShown ?: return
        Log.d("AboutAdapter", "bindDetails:${aboutItem.name}")

        when (aboutItem) {
            ItemType.UNSET -> fragmentManager?.beginTransaction()?.replace(R.id.detailsContainer, EmptyFragment())?.commit()
            ItemType.LICENSES -> {
                fragmentManager?.beginTransaction()?.replace(R.id.detailsContainer, LicenseFragment())?.commit()
            }
            ItemType.APP_DEVELOPMENT -> {
                fragmentManager?.beginTransaction()?.replace(R.id.detailsContainer, AppDevelopmentFragment())?.commit()
            }
            ItemType.CONTACT_INFO ->{
                fragmentManager?.beginTransaction()?.replace(R.id.detailsContainer, ContactFragment())?.commit()
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        aboutItems?.size?:0 -> ITEM_TYPE_DETAILS
        else -> ITEM_TYPE_NORMAL
    }

    override fun getItemCount(): Int = 1 + (aboutItems?.size ?: 0)

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var item:AboutContent.AboutItem? = null
        var itemType:ItemType? = null
        val titleView: TextView? = mView.findViewById(R.id.titleView)

        override fun toString(): String {
            return super.toString() + " '" + titleView?.text + "'"
        }
    }


    class AboutContent {


        val items: MutableList<AboutItem> = ArrayList()

        fun populate(context: Context) : List<AboutItem> {
            //add items
            items.add(AboutItem(context.getString(R.string.third_party_licenses), ItemType.LICENSES))
            items.add(AboutItem(context.getString(R.string.app_development), ItemType.APP_DEVELOPMENT))
            items.add(AboutItem(context.getString(R.string.contact_info), ItemType.CONTACT_INFO))
            return items
        }

        data class AboutItem(val title: String, val type:ItemType) {
            override fun toString(): String = title
        }
    }

    enum class ItemType {
        UNSET,
        LICENSES,
        APP_DEVELOPMENT,
        CONTACT_INFO
    }
}

