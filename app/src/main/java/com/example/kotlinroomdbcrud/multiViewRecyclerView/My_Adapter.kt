package com.example.kotlinroomdbcrud.multiViewRecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinroomdbcrud.R

class My_Adapter(context: Context, var list: ArrayList<DataModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private val yourContext: Context = context

    private inner class GfgViewOne(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var gfgText: TextView = itemView.findViewById(R.id.gfgTextView1)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            gfgText.text = recyclerViewModel.theText
        }
    }

    private inner class View2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var gfgText: TextView = itemView.findViewById(R.id.gfgTextView2)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            gfgText.text = recyclerViewModel.theText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return GfgViewOne(
                LayoutInflater.from(yourContext).inflate(R.layout.item_view_1, parent, false)
            )
        }
        return View2ViewHolder(
            LayoutInflater.from(yourContext).inflate(R.layout.item_view_2, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].theView == VIEW_TYPE_ONE) {
            (holder as GfgViewOne).bind(position)
        } else {
            (holder as View2ViewHolder).bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].theView
    }
}
