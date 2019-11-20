package com.mctech.feature.arq.components

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("android:text")
fun formatDate(view: TextView, date: Date?) {
    if(date == null) return

    view.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
}
