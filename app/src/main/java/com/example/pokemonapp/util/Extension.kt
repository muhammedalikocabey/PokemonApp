package com.example.pokemonapp.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.displayImage(pictureUrl: String) {
    Glide.with(this).load(pictureUrl).into(this)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}