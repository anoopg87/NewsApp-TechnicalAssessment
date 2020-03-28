package com.anooplab.newsapp.factslist.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anooplab.newsapp.factslist.model.FactItemUiModel

class FactsListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var factsList: MutableList<FactItemUiModel> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FactsViewHolder(FactItemView(parent.context))
    }

    override fun getItemCount(): Int {
        return factsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FactsViewHolder).view.setFact(factsList[position])
    }

    data class FactsViewHolder(val view: FactItemView) : RecyclerView.ViewHolder(view)
}