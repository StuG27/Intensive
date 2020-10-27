package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern:String="HH:mm:ss dd:MM:yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.humanizeDiff(date:Date = Date()) : String {
    var difference = (date.time-this.time)/1000
    if (difference>0) {
        return when {
            difference <=1 -> "только что"
            difference <=45 -> "несколько секунд назад"
            difference <=75 -> "минуту назад"
            difference <=2700 -> "${TimeUnits.MINUTE.plural(difference.toInt() / 60)} назад"
            difference <=4500 -> "час назад"
            difference <=79200 -> "${TimeUnits.HOUR.plural(difference.toInt() / 60 / 60)} назад"
            difference <=93600 -> "день назад"
            difference <=31104000 -> "${TimeUnits.DAY.plural(difference.toInt() / 60 / 60 / 24)} назад"
            else -> "более года назад"
        }
    }
    else {
        difference = (this.time-date.time)/1000
        return when {
            difference <= 1 -> "только что"
            difference <= 45 -> "через несколько секунд"
            difference <= 75 -> "через минуту"
            difference <= 2700 -> "через ${TimeUnits.MINUTE.plural(difference.toInt() / 60)}"
            difference <= 4500 -> "через час"
            difference <= 79200 -> "через ${TimeUnits.HOUR.plural(difference.toInt() / 60 / 60)}"
            difference <= 93600 -> "через день"
            difference <= 31104000 -> "через ${TimeUnits.DAY.plural(difference.toInt() / 60 / 60 / 24)}"
            else -> "более чем через год"
        }
    }
}



fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time +=when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;
    fun plural(value: Int): String {
        var form1 = ""
        var form2 = ""
        var form3 = ""
        when (this) {
            SECOND -> {
                form1 = "секунду"
                form2 = "секунды"
                form3 = "секунд"
            }
            MINUTE -> {
                form1 = "минуту"
                form2 = "минуты"
                form3 = "минут"
            }
            HOUR -> {
                form1 = "час"
                form2 = "часа"
                form3 = "часов"
            }
            DAY -> {
                form1 = "день"
                form2 = "дня"
                form3 = "дней"
            }
        }
        var valueS = value.toString()
        val valueC1 = valueS.last()
        valueS = valueS.dropLast(1)
        var valueC2 = '0'
        if (valueS != "") {
            valueC2 = valueS.last()
        }
        var form = if ((valueC1 == '1') && (valueC2 == '1') || (valueC1 == '2') && (valueC2 == '1') ||
            (valueC1 == '3') && (valueC2 == '1') || (valueC1 == '4') && (valueC2 == '1')) {
            form3
        } else if (valueC1 == '1') {
            form1
        } else if ((valueC1 == '2') || (valueC1 == '3') || (valueC1 == '4')) {
            form2
        } else {
            form3
        }
        return "$value $form"
    }
}