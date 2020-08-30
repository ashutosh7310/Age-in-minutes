package com.example.ageinminutescalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener{view ->
            clickDatePicker(view)  //view work as it recieves a request to do process after btn click
             }
        DateReset.setOnClickListener{view -> dateReset(view)}
    }

    //TO DISPLAY  CLALANDER

    fun clickDatePicker(view: View){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,                    //DatePickerDialog would popup a calendar
            DatePickerDialog.OnDateSetListener{

                view,selectedYear,selectedMonth,selectedDay ->

                val selectDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
                SelectedDate.text = selectDate

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val theDate = sdf.parse(selectDate)

                val sdm = theDate!!.time /60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val cdm = currentDate!!.time/60000

                val aim = cdm - sdm
                SelectedDateInMinutes.setText(aim.toString())
            }
            ,year,month,day)
            dpd.datePicker.setMaxDate(Date().time- 86400000)
        dpd.show()
    }

    fun dateReset(view: View)
    {
        SelectedDate.setText("")
        SelectedDateInMinutes.setText("")

    }
}