package com.example.app_orderhub.ui.catolog.components

import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.LinearLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

@Composable
fun Calendar(
    initialDate: String? = null,
    onDateSelected: (String) -> Unit) {
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { ctx ->
            CalendarView(ctx).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                initialDate?.let { dateStr ->
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val localDate = LocalDate.parse(dateStr, formatter)
                    val calendar = Calendar.getInstance().apply {
                        set(localDate.year, localDate.monthValue - 1, localDate.dayOfMonth)
                    }
                    date = calendar.timeInMillis
                }

                setOnDateChangeListener { _, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    onDateSelected(selectedDate)
                }
            }
        }
    )
}
