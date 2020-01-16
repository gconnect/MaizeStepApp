package com.chuks.maizestemapp.util

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chuks.maizestemapp.R
import de.hdodenhof.circleimageview.CircleImageView

 object LoadImage {
    @BindingAdapter("app:insect_Image")
    @JvmStatic
    fun loadImage(view: CircleImageView, insectImage: String) {
        if (insectImage.isNullOrEmpty()) {
            view.setImageResource(R.drawable.image)
        } else {
            Glide.with(view.context)
                .load(insectImage).apply(RequestOptions().circleCrop())
                .into(view)
        }
    }

}