package com.mindteck.ui.screen.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.compose.ui.text.toLowerCase
import androidx.recyclerview.widget.RecyclerView
import com.mindteck.R

class LatestModelSeriesAdapter(val mContext :Context,val list:ArrayList<String>) :
    RecyclerView.Adapter<LatestModelSeriesAdapter.LatestModelSeriesViewHolder>(),Filterable {
    private var filteredContactList: ArrayList<String>? = null
    init {
        filteredContactList = list as ArrayList<String>
    }


class LatestModelSeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txt_lvel = itemView.findViewById<TextView>(R.id.txt_lvel)

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestModelSeriesViewHolder {
        var root = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false)
        return  LatestModelSeriesViewHolder(root)
    }

    override fun getItemCount(): Int {
     return filteredContactList!!.size
    }

    override fun onBindViewHolder(holder: LatestModelSeriesViewHolder, position: Int) {
        holder.txt_lvel.setText(filteredContactList!!.get(position))
    }

    override fun getFilter(): Filter {
        return object :Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint == null || constraint!!.toString().length == 0) {
                    filteredContactList = list as ArrayList<String>
                } else {
                    val resultList = ArrayList<String>()
                    for (row in list) {
                        if (row.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    filteredContactList = resultList
                    Log.d("getFilter==>0",filteredContactList.toString())

                }
                filterResults.values = filteredContactList
                Log.d("getFilter==>1",filterResults.count.toString())

                return filterResults
            }


            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredContactList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }
}