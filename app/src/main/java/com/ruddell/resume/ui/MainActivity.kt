package com.ruddell.resume.ui

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ruddell.resume.R
import com.ruddell.resume.datasource.ApiImporter
import com.ruddell.resume.datasource.LocalImporter
import com.ruddell.resume.extensions.circleHide
import com.ruddell.resume.extensions.circleReveal
import com.ruddell.resume.extensions.toColor
import com.ruddell.resume.models.Position
import com.ruddell.resume.ui.details.DetailsFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main
    var currentDetailShown:Details? = null
    var currentDetailPosition:Position = Position(0,0)
    var detailsFragment:DetailsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.detailsBackground)?.setOnClickListener { hideDetails() }

        launch {
            withContext(Dispatchers.IO) { LocalImporter.import(this@MainActivity, R.raw.initialcontent) }
            ApiImporter.import(this@MainActivity)
        }
    }

    override fun onBackPressed() {
        if (detailsFragment!=null) hideDetails()
        else super.onBackPressed()
    }

    fun showDetails(details: Details, centerPosition: Position) {
        currentDetailPosition = centerPosition
        currentDetailShown = details
        findViewById<View>(R.id.detailsBackground)?.setBackgroundColor(details.backgroundColor)
        findViewById<View>(R.id.detailsBackground)?.circleReveal(centerPosition)

        Handler().postDelayed({
            supportFragmentManager?.apply {
                detailsFragment = findFragmentByTag(DetailsFragment.TAG) as? DetailsFragment ?: DetailsFragment()
                detailsFragment?.details = details
                detailsFragment?.let {detailsFragment ->
                    beginTransaction()
                        .replace(R.id.detailsFragmentContainer,detailsFragment, DetailsFragment.TAG)
                        .commit()
                }

            }
        }, 300)
    }

    fun hideDetails() {
        detailsFragment?.let {detailsFragment ->
            supportFragmentManager?.apply {
                beginTransaction()
                    .remove(detailsFragment)
                    .commit()
            }
        }

        detailsFragment = null
        findViewById<View>(R.id.detailsBackground)?.circleHide(currentDetailPosition)
    }
}



enum class Details(val backgroundColor:Int) {
    WORK_EXPERIENCE("#BBDEFB".toColor()),
    EDUCATION("#EF5350".toColor()),
    SKILLS("#C8E6C9".toColor()),
    ABOUT("#FFD54F".toColor())
}
