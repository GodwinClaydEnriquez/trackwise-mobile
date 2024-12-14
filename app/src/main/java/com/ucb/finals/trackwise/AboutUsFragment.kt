package com.ucb.finals.trackwise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class AboutUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_us, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textAboutUs = view.findViewById<TextView>(R.id.text_about_us)
        val textAboutDescription = view.findViewById<TextView>(R.id.text_about_description)

        textAboutUs.text = getString(R.string.about_us)
        textAboutDescription.text = getString(R.string.about_us_description)
    }
}
