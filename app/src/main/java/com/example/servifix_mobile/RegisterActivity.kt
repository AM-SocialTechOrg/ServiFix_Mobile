package com.example.servifix_mobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
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

        val spinner: Spinner = findViewById(R.id.user_type_spinner)
        val btnContinue: Button = findViewById(R.id.btnContinue)

        val options = listOf("Cliente", "Técnico")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Establecer "Cliente" como opción por defecto
        val defaultPosition = options.indexOf("Cliente")
        spinner.setSelection(defaultPosition)

        btnContinue.setOnClickListener {
            val selectedPosition = spinner.selectedItemPosition
            val selectedOption = spinner.adapter.getItem(selectedPosition) as String

            if (selectedOption == "Técnico") {
                val intent = Intent(this, WorkerValidationActivity::class.java)
                startActivity(intent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}