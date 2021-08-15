package com.example.pokemonapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.displayImage(pictureUrl: String) {
    Glide.with(this).load(pictureUrl).into(this)
}