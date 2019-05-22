package com.devediapp.componeteskotlin.view

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.devediapp.componeteskotlin.R
import kotlinx.android.synthetic.main.activity_date.*
import java.text.SimpleDateFormat
import java.util.*

class DateActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePicker.OnTimeChangedListener {

    private val mSimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)

        setListners()
    }

    private fun setListners() {
        buttonDatePicker.setOnClickListener(this)
        buttonDateVoltar.setOnClickListener(this)
        buttonGetTime.setOnClickListener(this)
        buttonSetTime.setOnClickListener(this)

        timePicker.setOnTimeChangedListener(this)

    }

    override fun onClick(view: View) {
        val id = view.id

        if (id == R.id.buttonDatePicker) {
            openDatePickerDialog()
        } else if (id == R.id.buttonDateVoltar) {
            finish()
        } else if (id == R.id.buttonGetTime) {

            // Verificando versão do android para saber qual comando usar
            if (Build.VERSION.SDK_INT >= 23) {
                val value = "Get->${timePicker.hour}:${timePicker.minute}"
                Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
            } else {
                val value = "Get->${timePicker.currentHour}:${timePicker.currentMinute}"

                Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
            }

        } else if (id == R.id.buttonSetTime) {
            // Verificando versão do android para saber qual comando usar
            if (Build.VERSION.SDK_INT >= 23) {
                timePicker.hour = 20
                timePicker.minute = 11
            } else {
                timePicker.currentHour = 20
                timePicker.currentMinute = 11
            }
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val id = view.id
        /*if(id == R.id.buttonDatePicker){*/
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val value = mSimpleDateFormat.format(calendar.time)
        buttonDatePicker.text = value
    }

    override fun onTimeChanged(view: TimePicker, hourOfDay: Int, minute: Int) {
        Toast.makeText(this, "Hora->$hourOfDay:$minute", Toast.LENGTH_SHORT).show()
    }

    private fun openDatePickerDialog() {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, this, year, month, day).show()
    }
}
