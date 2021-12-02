package com.idme.mypurchase.common

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*


class Utils {

    // Format ISO date to month name format
    fun dateStringToDate(dateString:String?):String{
        val ta = DateTimeFormatter.ISO_INSTANT.parse(dateString)
        val i = Instant.from(ta)
        val date = Date.from(i)
        return SimpleDateFormat("MMM dd,yyyy").format(date)
    }

    // Format phone number to format like +1(505)343-6456
    fun formatPhoneNumber(number: String): String {
        var number = number
        number = number.substring(0, number.length - 4) + "-" + number.substring(
            number.length - 4,
            number.length
        )
        number = number.substring(0, number.length - 8) + ")" + number.substring(
            number.length - 8,
            number.length
        )
        number = number.substring(0, number.length - 12) + "(" + number.substring(
            number.length - 12,
            number.length
        )
        return "+$number"
    }
}