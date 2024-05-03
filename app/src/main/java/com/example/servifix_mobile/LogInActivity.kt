package com.example.servifix_mobile

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.servifix_mobile.viewmodel.LoginViewModel


class LogInActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var logInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.log_in)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        val registerTextView: TextView = findViewById(R.id.txtRegister)
        emailEditText = findViewById(R.id.txtEmail)
        passwordEditText = findViewById(R.id.txtPassword)
        logInButton = findViewById(R.id.btnLogIn)
        logInButton.isEnabled = false

        emailEditText.addTextChangedListener(textWatcher)
        passwordEditText.addTextChangedListener(textWatcher)

        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val btnLogIn: Button = findViewById(R.id.btnLogIn)
        btnLogIn.setOnClickListener {
            val email = findViewById<EditText>(R.id.txtEmail).text.toString()
            val password = findViewById<EditText>(R.id.txtPassword).text.toString()
            LoginViewModel().logIn(email, password)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textWatcher.afterTextChanged(null)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            val emailInput = emailEditText.text.toString().trim()
            val passwordInput = passwordEditText.text.toString().trim()

            logInButton.isEnabled = emailInput.isNotEmpty() && passwordInput.isNotEmpty()

            val backgroundColor = if (logInButton.isEnabled) {
                ContextCompat.getColor(this@LogInActivity, R.color.main_blue)
            } else {
                ContextCompat.getColor(this@LogInActivity, R.color.disabled_button)
            }

            logInButton.backgroundTintList = ColorStateList.valueOf(backgroundColor)

        }
    }

}