package com.example.servifix_mobile

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isNotEmpty
import com.example.servifix_mobile.viewmodel.LoginViewModel

class WorkerExperienceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.worker_experience)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //Obtener datos de worker validation view
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val birthday = intent.getStringExtra("birthday")
        val gender = intent.getStringExtra("gender")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val role = 2

        val txtUsername: TextView = findViewById(R.id.txtUsername)
        val txtUsermail: TextView = findViewById(R.id.txtUsermail)

        txtUsername.text = "$firstName $lastName"
        txtUsermail.text = email

        //Spinners
        val spinner1: Spinner = findViewById(R.id.job_area_spinner)
        val spinner2: Spinner = findViewById(R.id.experience_period_spinner)

        val job_area_options = listOf("Pintor", "Gasfitero")
        val job_area_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, job_area_options)
        job_area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = job_area_adapter

        val experience_period_options = listOf("Años", "Meses")
        val experience_period_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, experience_period_options)
        experience_period_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = experience_period_adapter


        //Cambiar estado del botón
        val textWatchers = listOf(
            findViewById<EditText>(R.id.txtExperience),
            findViewById<EditText>(R.id.txtPeriod)


        ).map { editText ->
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    updateButtonState()
                }
            }.also { editText.addTextChangedListener(it) }
        }


        //Redirección al login
        val logInTextView: TextView = findViewById(R.id.txtLogIn)
        logInTextView.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        //Continuar al registro
        val btnContinue: Button = findViewById(R.id.btnContinue)
        btnContinue.setOnClickListener {
            Log.e("RegisterActivity", "firstName: $firstName, lastName: $lastName, birthday: $birthday, gender: $gender, email: $email, password: $password, role: $role")
            LoginViewModel().register(
                firstName ?: "",
                lastName ?: "",
                gender ?: "",
                birthday ?: "",
                email ?: "",
                password ?: "",
                role
            )
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateButtonState() {
        val areFieldsFilled = listOf(
            findViewById<EditText>(R.id.txtExperience).text.isNotEmpty(),
            findViewById<Spinner>(R.id.job_area_spinner).isNotEmpty(),
            findViewById<Spinner>(R.id.experience_period_spinner).isNotEmpty(),
            findViewById<EditText>(R.id.txtPeriod).text.isNotEmpty()
        ).all { it }

        val btnContinue = findViewById<Button>(R.id.btnContinue)
        btnContinue.isEnabled = areFieldsFilled

        val backgroundColor = if (areFieldsFilled) {
            ContextCompat.getColor(this, R.color.main_blue)
        } else {
            ContextCompat.getColor(this, R.color.disabled_button)
        }

        btnContinue.backgroundTintList = ColorStateList.valueOf(backgroundColor)
    }
}