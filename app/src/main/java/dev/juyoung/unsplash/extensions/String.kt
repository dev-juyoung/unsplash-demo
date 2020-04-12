package dev.juyoung.unsplash.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

val String.toDate: Date?
    @SuppressLint("SimpleDateFormat")
    get() = SimpleDateFormat("yyyy-MM-dd").parse(this)
