package com.example.gojekpractice.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gojekpractice.R
import com.example.gojekpractice.base.CharacterComparator
import com.example.gojekpractice.model.StarWarsPeopleData

class StarWarPeopleAdapter(val context: Context) :
    PagingDataAdapter<StarWarsPeopleData, StarWarPeopleAdapter.MyViewHolder>(CharacterComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the item layout and create a new ViewHolder
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_start_war, parent, false)
        return MyViewHolder(itemView, context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class MyViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView
        private val userImage: ImageView

        init {
            name = itemView.findViewById(R.id.txt_character_name)
            userImage = itemView.findViewById(R.id.iv_user)
        }

        fun bind(item: StarWarsPeopleData?) {
            // Bind the item to the view
            name.text = item?.name
            var url: Int =
                (item?.url?.removePrefix("https://swapi.dev/api/people/")?.removeSuffix("/")
                    ?: "1").toInt()
            Glide.with(context).load("https://i.pravatar.cc/150?img=${url}").circleCrop()
                .placeholder(R.drawable.baseline_supervised_user_circle_24).into(userImage)

        }
    }
}
