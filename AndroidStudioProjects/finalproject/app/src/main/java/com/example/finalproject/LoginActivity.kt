package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnFacebook = findViewById<ImageButton>(R.id.btnFacebookLogin)
        val btnGmail = findViewById<ImageButton>(R.id.btnGmailLogin)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)

        // Nút Login nếu có (bạn tự thêm vào XML nếu cần)
        // val btnLogin = findViewById<Button>(R.id.btnLogin)

        tvSignUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnFacebook.setOnClickListener {
            Toast.makeText(this, "Login with Facebook clicked", Toast.LENGTH_SHORT).show()
        }

        btnGmail.setOnClickListener {
            Toast.makeText(this, "Login with Gmail clicked", Toast.LENGTH_SHORT).show()
        }

        tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
