package pol3436.test.moto_history.utill

import java.text.SimpleDateFormat
import java.util.*

class Utills {
    companion object {
        val fullFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MM", Locale.getDefault())
        val dayFormat = SimpleDateFormat("dd", Locale.getDefault())

    }
    fun getCalender(){
        val calendar = Calendar.getInstance()
    /*    val fullFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MM", Locale.getDefault())
        val dayFormat = SimpleDateFormat("dd", Locale.getDefault())*/

        val defalt_day = fullFormat.format(calendar.time)
        val year = yearFormat.format(calendar.time)
        val month = monthFormat.format(calendar.time)
        val day = dayFormat.format(calendar.time)
        val timestamp = calendar.timeInMillis
    }
    class retrun_time(){


        fun allday(millis:Long):Any{
            val defalt_day = fullFormat.format(millis)
            return defalt_day
        }
        fun year(millis:Long):Any{
            val year = yearFormat.format(millis)
            return year
        }
        fun month(millis:Long):Any{
            val month = monthFormat.format(millis)
            return month
        }
        fun day(millis:Long):Any{
            val day = dayFormat.format(millis)
            return day
        }



    }
}