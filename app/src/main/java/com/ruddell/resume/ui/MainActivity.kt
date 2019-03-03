package com.ruddell.resume.ui

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.ruddell.resume.R
import com.ruddell.resume.extensions.circleHide
import com.ruddell.resume.extensions.circleReveal
import com.ruddell.resume.extensions.toColor
import com.ruddell.resume.models.Position
import com.ruddell.resume.ui.details.DetailsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var currentDetailShown:Details? = null
    var currentDetailPosition:Position = Position(0,0)
    var detailsFragment:DetailsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        detailsBackground?.setOnClickListener { hideDetails() }
    }

    fun showDetails(details: Details, centerPosition: Position) {
        currentDetailPosition = centerPosition
        currentDetailShown = details
        detailsBackground?.setBackgroundColor(details.backgroundColor)
        detailsBackground?.circleReveal(centerPosition)

        Handler().postDelayed({
            supportFragmentManager?.apply {
                detailsFragment = findFragmentByTag(DetailsFragment.TAG) as? DetailsFragment ?: DetailsFragment()
                beginTransaction()
                    .replace(R.id.detailsFragmentContainer,detailsFragment, DetailsFragment.TAG)
                    .commit()
            }
        }, 300)
    }

    fun hideDetails() {
        supportFragmentManager?.apply {
            beginTransaction()
                .remove(detailsFragment)
                .commit()
        }
        detailsFragment = null
        detailsBackground?.circleHide(currentDetailPosition)
    }
}



enum class Details(val backgroundColor:Int) {
    WORK_EXPERIENCE("#BBDEFB".toColor()),
    EDUCATION("#EF5350".toColor()),
    SKILLS("#C8E6C9".toColor()),
    ABOUT("#FFD54F".toColor())
}
