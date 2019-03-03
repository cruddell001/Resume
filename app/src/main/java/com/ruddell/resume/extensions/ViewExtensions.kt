package com.ruddell.resume.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils
import com.ruddell.resume.models.Position

fun View.getCenter() : Position {
    val topLeft = intArrayOf(0, 0)
    getLocationOnScreen(topLeft)

    return Position((topLeft[0] + measuredWidth/2.0).toInt(), (topLeft[1] + measuredHeight/2.0).toInt())
}

fun View.circleReveal(position: Position) {

    //get longest distances to edge
    val longestX = Math.max(position.xPos, Math.abs(this.getWidth() - position.xPos))
    val longestY = Math.max(position.yPos, Math.abs(this.getHeight() - position.yPos))

    // get the final radius for the clipping circle
    val finalRadius = Math.hypot(longestX.toDouble(), longestY.toDouble()).toFloat()

    // make the view visible and start the animation
    this.setVisibility(View.VISIBLE)

    if (this.isAttachedToWindow()) {
        // create the animator for this view (the start radius is zero)
        val anim = ViewAnimationUtils.createCircularReveal(this, position.xPos, position.yPos, 0f, finalRadius)
        anim.start()
    }
}

fun View.circleHide(position: Position) {

    if (!isAttachedToWindow()) {
        setVisibility(View.INVISIBLE)
    } else {
        // get the initial radius for the clipping circle
        val initialRadius = Math.hypot(position.xPos.toDouble(), position.yPos.toDouble()).toFloat()

        // create the animation (the final radius is zero)
        val anim = ViewAnimationUtils.createCircularReveal(this, position.xPos, position.yPos, initialRadius, 0f)

        // make the view invisible when the animation is done
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                setVisibility(View.INVISIBLE)
            }
        })

        // start the animation
        anim.start()
    }
}