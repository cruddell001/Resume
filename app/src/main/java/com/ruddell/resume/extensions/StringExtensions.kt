package com.ruddell.resume.extensions

import android.graphics.Color

fun String.toColor():Int = Color.parseColor(this)