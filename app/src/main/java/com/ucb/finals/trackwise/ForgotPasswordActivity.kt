package com.ucb.finals.trackwise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        firebaseAuth = FirebaseAuth.getInstance()

        val forgotPassButton = findViewById<Button>(R.id.forgotPassButton)
        val forgotPassCancel = findViewById<Button>(R.id.forgotPassCancel)

        forgotPassButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.forgotPassUsername).text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "Please input email to reset password", Toast.LENGTH_SHORT).show()
            } else {
                sendPasswordResetEmail(email)
            }
        }

        forgotPassCancel.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun sendPasswordResetEmail(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Password reset email sent", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Error sending reset email: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}