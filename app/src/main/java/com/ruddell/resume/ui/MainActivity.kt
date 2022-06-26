package com.ruddell.resume.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ruddell.resume.R
import com.ruddell.resume.datasource.ApiImporter
import com.ruddell.resume.datasource.LocalImporter
import com.ruddell.resume.extensions.circleHide
import com.ruddell.resume.extensions.circleReveal
import com.ruddell.resume.extensions.toColor
import com.ruddell.resume.models.Position
import com.ruddell.resume.ui.navigation.AppNavHost
import com.ruddell.resume.ui.navigation.Destination
import com.ruddell.resume.ui.navigation.navigate
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main
    private var currentDetailShown:Details? = null
    private var currentDetailPosition:Position = Position(0,0)
    private var controller: NavHostController? = null
    private var detailsBackground: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch {
            withContext(Dispatchers.IO) { LocalImporter.import(this@MainActivity, R.raw.initialcontent) }
            ApiImporter.import(this@MainActivity)
        }

        setContent {
            Box {

                controller = rememberNavController().also {
                    AppNavHost(controller = it)
                }
                AndroidView(factory = {
                    View(it).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    }.also { view ->
                        detailsBackground = view
                    }
                })
            }

        }
    }

    fun showDetails(details: Details, centerPosition: Position) {
        currentDetailPosition = centerPosition
        currentDetailShown = details
        detailsBackground?.setBackgroundColor(details.backgroundColor)
        detailsBackground?.circleReveal(centerPosition)

        launch {
            delay(300L)
            controller?.navigate(Destination.Detail, details.ordinal.toString())
            delay(200L)
            detailsBackground?.visibility = View.INVISIBLE
        }
    }

    fun hideDetails() {
        launch {
            detailsBackground?.visibility = View.VISIBLE
            controller?.popBackStack()
            detailsBackground?.circleHide(currentDetailPosition)
        }
    }
}



enum class Details(val backgroundColor:Int) {
    WORK_EXPERIENCE("#BBDEFB".toColor()),
    EDUCATION("#EF5350".toColor()),
    SKILLS("#C8E6C9".toColor()),
    ABOUT("#FFD54F".toColor())
}
