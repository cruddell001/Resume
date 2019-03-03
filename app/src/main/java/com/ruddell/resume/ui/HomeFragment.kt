package com.ruddell.resume.ui


import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ruddell.resume.R
import com.ruddell.resume.extensions.constraintSet
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_top_level_content.*
import java.util.*
import kotlin.concurrent.schedule


class HomeFragment : Fragment() {
    var collapsed = false
    var constraintOriginal:ConstraintSet? = null
    var constraintNew:ConstraintSet? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_home, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //animate after delay
        Handler().postDelayed({
            animateView()
        }, 3000)

        resumeLabel?.setOnClickListener { animateView() }
        backgroundImage?.setOnClickListener { animateView() }

    }

    private fun animateView() {
        val root = view as? ConstraintLayout ?: return
        val context =  activity ?: return
        activity?.runOnUiThread {
            constraintOriginal = constraintOriginal ?: root.constraintSet()
            constraintNew = constraintNew ?: R.layout.fragment_home_collapsed.constraintSet(context)
            val constraintSet = if (collapsed) constraintOriginal else constraintNew
            collapsed = !collapsed

            when (collapsed) {
                true -> {
                    Handler().postDelayed({
                        childFragmentManager.findFragmentById(R.id.topLevelFragment)?.view?.visibility = View.VISIBLE
                    }, 300)
                }
                false -> childFragmentManager.findFragmentById(R.id.topLevelFragment)?.view?.visibility = View.GONE
            }

            TransitionManager.beginDelayedTransition(root)
            constraintSet?.applyTo(root)

        }
    }
}
