package com.ucb.finals.trackwise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment

class RateAppFragment : Fragment() {

    private lateinit var ratingBar: RatingBar
    private lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rate_app, container, false)

        ratingBar = view.findViewById(R.id.ratingBar)
        submitButton = view.findViewById(R.id.button_submit_rating)

        submitButton.setOnClickListener {
            val rating = ratingBar.rating
            Toast.makeText(requireContext(), "You rated the app: $rating", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}


