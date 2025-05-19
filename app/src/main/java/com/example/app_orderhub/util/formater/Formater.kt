package com.example.app_orderhub.util.formater

fun formatPhoneNumber(input: String): String {
    val digits = input.filter { it.isDigit() }

    return if (digits.length == 11) {
        "(${digits.substring(0, 2)}) ${digits.substring(2, 7)}-${digits.substring(7)}"
    } else {
        input
    }
}

