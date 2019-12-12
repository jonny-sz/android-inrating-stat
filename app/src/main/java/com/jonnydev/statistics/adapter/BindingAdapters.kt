package com.jonnydev.statistics.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jonnydev.statistics.R
import com.jonnydev.statistics.model.UserImg
import com.jonnydev.statistics.picasso.RoundedTransformation
import com.squareup.picasso.Picasso

@BindingAdapter("android:text")
fun convertLongToString(view: TextView, value: Long) {
    view.text = value.toString()
}

@BindingAdapter("url")
fun loadImage(view: ImageView, url: UserImg) {
    Picasso.get()
        .load(url.toString())
        .transform(RoundedTransformation(20, 0))
        .error(R.drawable.error_user_icon)
        .into(view)
}
