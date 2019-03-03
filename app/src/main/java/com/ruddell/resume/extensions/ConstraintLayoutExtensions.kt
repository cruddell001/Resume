package com.ruddell.resume.extensions

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet

fun ConstraintLayout.constraintSet() : ConstraintSet {
    val constraintSet = ConstraintSet()
    constraintSet.clone(this)
    return constraintSet
}

fun Int.constraintSet(context: Context) : ConstraintSet {
    val constraintSet = ConstraintSet()
    constraintSet.clone(context, this)
    return constraintSet
}