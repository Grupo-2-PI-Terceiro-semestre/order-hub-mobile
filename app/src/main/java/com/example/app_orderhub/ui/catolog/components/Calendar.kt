package com.example.app_orderhub.ui.catolog.components

import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Calendar() {
    CalendarPreview()
}

@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf("") }

        AndroidView(
            factory = { ctx ->
                CalendarView(ctx).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    setOnDateChangeListener { _, year, month, dayOfMonth ->
                        selectedDate = "$dayOfMonth/${month + 1}/$year"
                        Toast.makeText(ctx, "Data selecionada: $selectedDate", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
}