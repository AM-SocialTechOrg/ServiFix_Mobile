package com.example.servifix_mobile

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import com.example.servifix_mobile.viewmodel.LoginViewModel
import java.util.Calendar
import java.util.Locale

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

        //Spinners
        val user_type_options = listOf("Cliente", "Técnico")
        val user_type_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, user_type_options)
        user_type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = user_type_adapter

        val user_gender_options = listOf("Masculino", "Femenino", "Otro", "Prefiero no decirlo")
        val user_gender_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, user_gender_options)
        user_gender_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = user_gender_adapter

        val defaultPosition = user_type_options.indexOf("Cliente")
        spinner1.setSelection(defaultPosition)

        //Botón continuar
        btnContinue.setOnClickListener {
            val selectedPosition = spinner1.selectedItemPosition
            val selectedOption = spinner1.adapter.getItem(selectedPosition) as String

            if (selectedOption == "Técnico") {
                val intent = Intent(this, WorkerValidationActivity::class.java)
                startActivity(intent)
            } else {
                val firstName = findViewById<EditText>(R.id.txtName).text.toString()
                val lastName = findViewById<EditText>(R.id.txtLastname).text.toString()
                val birthday = findViewById<TextView>(R.id.txtBirthdate).text.toString()
                val gender = spinner2.selectedItem.toString()
                val email = findViewById<EditText>(R.id.txtEmail).text.toString()
                val password = findViewById<EditText>(R.id.txtPassword).text.toString()
                val role = 1
                Log.e("RegisterActivity", "firstName: $firstName, lastName: $lastName, birthday: $birthday, gender: $gender, email: $email, password: $password, role: $role")
                LoginViewModel().register(firstName, lastName, gender, birthday, email, password, role)
            }
        }

        logInTextView.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        val textWatchers = listOf(
            findViewById<EditText>(R.id.txtName),
            findViewById<EditText>(R.id.txtLastname),
            findViewById<TextView>(R.id.txtBirthdate),
            findViewById<EditText>(R.id.txtId),
            findViewById<EditText>(R.id.txtEmail),
            findViewById<EditText>(R.id.txtPassword)
        ).map { editText ->
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    updateButtonState()
                }
            }.also { editText.addTextChangedListener(it) }
        }

        findViewById<Switch>(R.id.switch_selector).setOnCheckedChangeListener { _, _ ->
            updateButtonState()
        }

        findViewById<TextView>(R.id.txtBirthdate).setOnClickListener {
            val newFragment = DatePickerFragment()
            newFragment.show(supportFragmentManager, "datePicker")
        }

        updateButtonState()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun updateButtonState() {
        val areFieldsFilled = listOf(
            findViewById<EditText>(R.id.txtName).text.isNotEmpty(),
            findViewById<EditText>(R.id.txtLastname).text.isNotEmpty(),
            findViewById<TextView>(R.id.txtBirthdate).text.isNotEmpty(),
            findViewById<EditText>(R.id.txtId).text.isNotEmpty(),
            findViewById<EditText>(R.id.txtEmail).text.isNotEmpty(),
            findViewById<EditText>(R.id.txtPassword).text.isNotEmpty(),
            findViewById<Switch>(R.id.switch_selector).isChecked
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

    class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            return DatePickerDialog(requireContext(), this, year, month, day)
        }
        override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
            val formattedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, day)
            val birthdateTextView = requireActivity().findViewById<TextView>(R.id.txtBirthdate)
            birthdateTextView.text = formattedDate
        }
    }
}