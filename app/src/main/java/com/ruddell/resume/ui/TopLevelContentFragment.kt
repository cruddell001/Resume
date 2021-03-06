package com.ruddell.resume.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ruddell.resume.R
import com.ruddell.resume.extensions.getCenter
import kotlinx.android.synthetic.main.fragment_top_level_content.*

class TopLevelContentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_top_level_content, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workButton?.setOnClickListener { showDetails(Details.WORK_EXPERIENCE, workButton) }
        educationButton?.setOnClickListener { showDetails(Details.EDUCATION, educationButton) }
        skillsButton?.setOnClickListener { showDetails(Details.SKILLS, skillsButton) }
        aboutButton?.setOnClickListener { showDetails(Details.ABOUT, aboutButton) }
    }

    fun showDetails(details: Details, sender:View) {
        (activity as? MainActivity)?.showDetails(details, sender.getCenter())
    }
}
