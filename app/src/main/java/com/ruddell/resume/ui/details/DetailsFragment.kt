package com.ruddell.resume.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.ruddell.resume.ui.Details


class DetailsFragment : Fragment() {

    val details = mutableStateOf(Details.WORK_EXPERIENCE)

    companion object {
        const val TAG = "DetailsFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(inflater.context).apply {
            setContent {
                val detailView = remember { details }
                DetailsView(detailView.value)
            }
        }
    }


}
