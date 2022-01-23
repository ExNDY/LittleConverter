package app.wcrtv.littleconverter.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.text.ParsePosition
import java.util.*

object TextUtils {
    @Throws(NumberFormatException::class)
    fun parseStringToDouble(inputString: String): Double {
        var input = inputString.trim()
        input = input.trim { it <= ' ' }
        if (input == "") {
            throw NumberFormatException("Entered value is EMPTY")
        }

        val inputValue: Double = try {
            parseDecimal(input)
        } catch (ex: NumberFormatException) {
            try {
                input.replace(",".toRegex(), ".").toDouble()
            } catch (e: NumberFormatException) {
                throw NumberFormatException("Error:$e input value: $input")
            }
        } catch (ex: ParseException) {
            try {
                input.replace(",".toRegex(), ".").toDouble()
            } catch (e: NumberFormatException) {
                throw NumberFormatException("Error:$e input value: $input")
            }
        }
        return inputValue
    }

    @Throws(ParseException::class)
    private fun parseDecimal(input: String): Double {
        val numberFormat: NumberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
        val parsePosition = ParsePosition(0)
        val number: Number? = numberFormat.parse(input, parsePosition)

        if (parsePosition.index != input.length) {
            throw ParseException("Invalid input", parsePosition.index)
        }

        if (number != null) {
            return number.toDouble()
        }

        return 0.0
    }

    fun formattingDifferenceValue(value: Double):String{
        return DecimalFormat("#0.00").format(value)
    }
}