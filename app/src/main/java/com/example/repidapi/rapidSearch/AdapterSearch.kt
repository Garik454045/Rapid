package com.example.repidapi.rapidSearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.repidapi.R
import com.example.repidapi.resultat.Result

 class AdapterSearch : RecyclerView.Adapter<AdapterSearch.SearchViewHolder>() {

    private val resultItem: MutableList<Result> = mutableListOf()

    class SearchViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val title: TextView = item.findViewById(R.id.title)
        private val link: TextView = item.findViewById(R.id.link)
        private val description: TextView = item.findViewById(R.id.description)

        fun bind(itemList: Result) {
            title.text = itemList.title
            link.text = itemList.link
            description.text = itemList.description
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(resultItem[position])
    }

    override fun getItemCount(): Int {
        return resultItem.size
    }

    fun setAll(list: List<Result>) {
        resultItem.clear()
        resultItem.addAll(list)
        notifyDataSetChanged()
    }
}