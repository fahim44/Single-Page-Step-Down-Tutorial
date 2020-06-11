package com.lamonjush.singlepagestepdowntutorial.model

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable

class Tut(
    val number: Int,
    val title: String,
    val description: String,
    val buttonText: String,
    val ancientColor: Int,
    val foregroundBlockColor: Int,
    val textColor: Int,
    val buttonTextColor : Int
) {

    var showing = false

    fun getNumberDrawable(): Drawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(ancientColor)
        gradientDrawable.shape = GradientDrawable.OVAL
        return gradientDrawable
    }

    fun getNumberOffStateForegroundDrawable() : Drawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(foregroundBlockColor)
        gradientDrawable.shape = GradientDrawable.OVAL
        return gradientDrawable
    }
}