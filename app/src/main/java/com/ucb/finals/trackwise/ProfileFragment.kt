package com.ucb.finals.trackwise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private lateinit var idNumberTextView: TextView
    private lateinit var fullNameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var editFullNameEditText: EditText
    private lateinit var editEmailEditText: EditText
    private lateinit var editButton: Button
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize views
        idNumberTextView = view.findViewById(R.id .idNumberTextView)
        fullNameTextView = view.findViewById(R.id.fullNameTextView)
        emailTextView = view.findViewById(R.id.emailTextView)
        editFullNameEditText = view.findViewById(R.id.editFullNameEditText)
        editEmailEditText = view.findViewById(R.id.editEmailEditText)
        editButton = view.findViewById(R.id.editButton)
        saveButton = view.findViewById(R.id.saveButton)

        // Retrieve data from arguments
        val idNumber = arguments?.getString("idNumber")
        val fullName = arguments?.getString("fullName")
        val email = arguments?.getString("email")

        // Set the retrieved data to the TextViews
        idNumberTextView.text = "ID Number: $idNumber"
        fullNameTextView.text = "Full Name: $fullName"
        emailTextView.text = "Email: $email"

        // Set up the Edit Profile button click listener
        editButton.setOnClickListener {
            // Show EditText fields and hide TextViews
            editFullNameEditText.setText(fullName)
            editEmailEditText.setText(email)
            fullNameTextView.visibility = View.GONE
            emailTextView.visibility = View.GONE
            editFullNameEditText.visibility = View.VISIBLE
            editEmailEditText.visibility = View.VISIBLE
            saveButton.visibility = View.VISIBLE
        }

        // Set up the Save Changes button click listener
        saveButton.setOnClickListener {
            // Get updated values from EditText fields
            val updatedFullName = editFullNameEditText.text.toString()
            val updatedEmail = editEmailEditText.text.toString()

            // Update the TextViews with new data
            fullNameTextView.text = "Full Name: $updatedFullName"
            emailTextView.text = "Email: $updatedEmail"

            // Hide EditText fields and show TextViews again
            fullNameTextView.visibility = View.VISIBLE
            emailTextView.visibility = View.VISIBLE
            editFullNameEditText.visibility = View.GONE
            editEmailEditText.visibility = View.GONE
            saveButton.visibility = View.GONE
        }

        return view
    }
}