package com.example.gojekpractice.base

import androidx.recyclerview.widget.DiffUtil
import com.example.gojekpractice.model.StarWarsPeopleData

object CharacterComparator : DiffUtil.ItemCallback<StarWarsPeopleData>() {

        override fun areItemsTheSame(oldItem: StarWarsPeopleData, newItem: StarWarsPeopleData) =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: StarWarsPeopleData, newItem: StarWarsPeopleData) =
            oldItem == newItem
    }