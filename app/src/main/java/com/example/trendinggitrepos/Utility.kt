package com.example.trendinggitrepos

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class Utility {

    companion object {
        fun showToast(activityContext: Context, msg: String) {
            Toast.makeText(activityContext, msg, Toast.LENGTH_SHORT).show()
        }

        fun setImageFromUrl(context: Context, url: String, imgView: ImageView) {
            Glide
                .with(context)
                .load(url)
                .centerCrop()
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.oval_shape)
                .into(imgView);
        }
    }
}