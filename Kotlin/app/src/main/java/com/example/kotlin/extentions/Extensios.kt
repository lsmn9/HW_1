package com.example.kotlin.extentions

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.kotlin.R
import com.example.kotlin.data.model.Note.Color
import java.text.SimpleDateFormat
import java.util.*

const val DATE_TIME_FORMAT = "dd.MMM.yy HH:mm"

inline fun Context.dip(value: Int) = resources.displayMetrics.density * value

inline fun View.dip(value: Int) = context.dip(value)

fun Date.format(): String =
        SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
                .format(this)

fun Color.getColorInt(context: Context): Int =
        ContextCompat.getColor(context, getColorRes())

fun Color.getColorRes(): Int = when (this) {
    Color.WHITE -> R.color.color_white
    Color.VIOLET -> R.color.color_violet
    Color.YELLOW -> R.color.color_yello
    Color.RED -> R.color.color_red
    Color.PINK -> R.color.color_pink
    Color.GREEN -> R.color.color_green
    Color.BLUE -> R.color.color_blue
}