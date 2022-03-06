package com.example.modul6networking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modul6networking.MainActivity
import com.example.modul6networking.R
import com.example.modul6networking.model.Poster
import com.example.modul6networking.model.PosterResp

class MainAdapter(val activity: MainActivity, val items:ArrayList<Poster>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is MainViewHolder) {
            holder.apply {
                title.text = item.title.toUpperCase()
                body.text = item.body.toUpperCase()
                llClick.setOnLongClickListener {
                    activity.dialogPoster(item)
                    false
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.tv_title)
        val body = view.findViewById<TextView>(R.id.tv_body)
        val llClick = view.findViewById<LinearLayout>(R.id.ll_poster)
    }


}