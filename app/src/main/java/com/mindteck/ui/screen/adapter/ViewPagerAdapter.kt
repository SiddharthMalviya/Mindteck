package com.mindteck.ui.screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.mindteck.R
import java.util.*


class ViewPagerAdapter(val mContext:Context,val list:List<Int>) : PagerAdapter() {
    var mLayoutInflater: LayoutInflater? = null


    init {
        mLayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    }
    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = mLayoutInflater!!.inflate(R.layout.pager_item, container, false)
//
        // referencing the image view from the item.xml file
        val imageView: ImageView = itemView.findViewById<View>(R.id.imageViewMain) as ImageView

        // setting the image in the imageView

        imageView.setImageResource(list.get(position))

        // Adding the View
        Objects.requireNonNull(container)?.addView(itemView)
        return itemView
    }

//    override fun instantiateItem(container: ViewGroup?, position: Int): Any? {
//        // inflating the item.xml
//        val itemView: View = mLayoutInflater!!.inflate(R.layout.pager_item, container, false)
//
//        // referencing the image view from the item.xml file
//        val imageView: ImageView = itemView.findViewById<View>(R.id.imageViewMain) as ImageView
//
//        // setting the image in the imageView
//
//        imageView.setImageResource(list.get(position))
//
//        // Adding the View
//        Objects.requireNonNull(container)?.addView(itemView)
//        return itemView
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any?) {
//        container.removeView(`object` as LinearLayout?)
//    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout?)
    }


}