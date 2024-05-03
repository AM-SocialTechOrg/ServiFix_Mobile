package com.example.servifix_mobile

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.register)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        val spinner1: Spinner = findViewById(R.id.user_type_spinner)
        val spinner2: Spinner = findViewById(R.id.user_gender_spinner)
        val btnContinue: Button = findViewById(R.id.btnContinue)
        val logInTextView: TextView = findViewById(R.id.txtLogIn)

        val user_type_options = listOf("Cliente", "Técnico")
        val user_type_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, user_type_options)
        user_type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = user_type_adapter

        val user_gender_options = listOf("Masculino", "Femenino", "Otro", "Prefiero no decirlo")
        val user_gender_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, user_gender_options)
        user_gender_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = user_gender_adapter

        // Establecer "Cliente" como opción por defecto
        val defaultPosition = user_type_options.indexOf("Cliente")
        spinner1.setSelection(defaultPosition)

        btnContinue.setOnClickListener {
            val selectedPosition = spinner1.selectedItemPosition
            val selectedOption = spinner1.adapter.getItem(selectedPosition) as String

            if (selectedOption == "Técnico") {
                val intent = Intent(this, WorkerValidationActivity::class.java)
                startActivity(intent)
            }
        }

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