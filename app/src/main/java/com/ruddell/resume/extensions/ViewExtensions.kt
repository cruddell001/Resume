package com.ruddell.resume.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils
import com.ruddell.resume.models.Position
import kotlin.math.abs
import kotlin.math.hypot

fun View.circleReveal(position: Position) {

    //get longest distances to edge
    val longestX = position.xPos.coerceAtLeast(abs(this.width - position.xPos))
    val longestY = position.yPos.coerceAtLeast(abs(this.height - position.yPos))

    // get the final radius for the clipping circle
    val finalRadius = hypot(longestX.toDouble(), longestY.toDouble()).toFloat()

    // make the view visible and start the animation
    this.visibility = View.VISIBLE

    if (this.isAttachedToWindow) {
        // create the animator for this view (the start radius is zero)
        val anim = ViewAnimationUtils.createCircularReveal(this, position.xPos, position.yPos, 0f, finalRadius)
        anim.start()
    }
}

fun View.circleHide(position: Position) {

    if (!isAttachedToWindow) {
        visibility = View.INVISIBLE
    } else {
        // get the initial radius for the clipping circle
        val initialRadius = hypot(position.xPos.toDouble(), position.yPos.toDouble()).toFloat()

        // create the animation (the final radius is zero)
        val anim = ViewAnimationUtils.createCircularReveal(this, position.xPos, position.yPos, initialRadius, 0f)

        // make the view invisible when the animation is done
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                visibility = View.INVISIBLE
            }
        })

        // start the animation
        anim.start()
    }
}