package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etUsername = findViewById<EditText>(R.id.etRegUsername)
        val etPassword = findViewById<EditText>(R.id.etRegPassword)
        val etVerifyPassword = findViewById<EditText>(R.id.etVerifyPassword)
        val btnConfirm = findViewById<Button>(R.id.btnConfirmRegister)
        val btnFacebook = findViewById<ImageView>(R.id.btnFacebookRegister)
        val btnGmail = findViewById<ImageView>(R.id.btnGmailRegister)
        val tvLoginIn = findViewById<TextView>(R.id.tvLoginIn)

        btnConfirm.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString()
            val verifyPassword = etVerifyPassword.text.toString()

            if (username.isEmpty() || password.isEmpty() || verifyPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password != verifyPassword) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
            // TODO: Xử lý đăng ký thật nếu có
        }

        btnFacebook.setOnClickListener {
            Toast.makeText(this, "Register with Facebook clicked", Toast.LENGTH_SHORT).show()
        }

        btnGmail.setOnClickListener {
            Toast.makeText(this, "Register with Gmail clicked", Toast.LENGTH_SHORT).show()
        }

        tvLoginIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
