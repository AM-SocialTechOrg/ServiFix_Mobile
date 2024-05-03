package com.example.servifix_mobile

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WorkerValidationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.worker_validation)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //Obtener datos de register view
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val birthday = intent.getStringExtra("birthday")
        val gender = intent.getStringExtra("gender")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        val txtUsername: TextView = findViewById(R.id.txtUsername)
        val txtUsermail: TextView = findViewById(R.id.txtUsermail)

        txtUsername.text = "$firstName $lastName"
        txtUsermail.text = email

        //Botón continuar
        val btnContinue: Button = findViewById(R.id.btnContinue)
        btnContinue.setOnClickListener {
            val intent = Intent(this, WorkerExperienceActivity::class.java).apply {
                putExtra("firstName", firstName)
                putExtra("lastName", lastName)
                putExtra("birthday", birthday)
                putExtra("gender", gender)
                putExtra("email", email)
                putExtra("password", password)
            }
            startActivity(intent)
        }

        //Redirección al login
        val logInTextView: TextView = findViewById(R.id.txtLogIn)
        logInTextView.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
    }
}