package com.ucb.finals.trackwise

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.ucb.finals.trackwise.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var isPasswordVisible: Boolean = false
    private var isConfirmPasswordVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginRedirect.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signupButton.setOnClickListener {
            val idNumber = binding.signupIDNumber.text.toString()
            val fullname = binding.signupFullName.text.toString()
            val email = binding.signupEmail.text.toString()
            val pass = binding.signupPassword.text.toString()
            val confirmPass = binding.signupConfirmPassword.text.toString()
            val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId
            val selectedRadioButton = binding.radioGroup.findViewById<RadioButton>(selectedRadioButtonId)
            val role = selectedRadioButton?.text.toString()

            if (idNumber.isNotEmpty() && fullname.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty() && role.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Store user information in SharedPreferences
                            val sharedPreferences = getSharedPreferences("User Prefs", MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("idNumber", idNumber)
                            editor.putString("fullName", fullname)
                            editor.putString("email", email)
                            editor.apply()

                            // Start MainActivity and pass user information
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("idNumber", idNumber)
                            intent.putExtra("fullName", fullname)
                            intent.putExtra("email", email)
                            startActivity(intent)
                            Toast.makeText(this, "Signed Up Successfully", Toast.LENGTH_SHORT).show()
                            finish() // Optional: Call finish() to remove the SignupActivity from the back stack
                        } else {
                            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not Matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.togglePasswordVisibility.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                binding.signupPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.togglePasswordVisibility.setImageResource(R.drawable.ic_visibility)
            } else {
                binding.signupPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.togglePasswordVisibility.setImageResource(R.drawable.ic_visibility_off)
            }
            binding.signupPassword.setSelection(binding.signupPassword.text.length)
        }

        binding.toggleConfirmPasswordVisibility.setOnClickListener {
            isConfirmPasswordVisible = !isConfirmPasswordVisible
            if (isConfirmPasswordVisible) {
                binding.signupConfirmPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.toggleConfirmPasswordVisibility.setImageResource(R.drawable.ic_visibility)
            } else {
                binding.signupConfirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.toggleConfirmPasswordVisibility.setImageResource(R.drawable.ic_visibility_off)
            }
            binding.signupConfirmPassword.setSelection(binding.signupConfirmPassword.text.length)
        }
    }
}