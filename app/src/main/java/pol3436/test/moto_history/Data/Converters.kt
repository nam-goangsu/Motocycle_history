package pol3436.test.moto_history.Data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.google.gson.Gson
import pol3436.test.moto_history.Model.DataClass.Sido_code
import java.io.ByteArrayOutputStream
import java.util.*

class Converters {
    // Bitmap -> ByteArray 변환
    @TypeConverter
    fun toByteArray(bitmap : Bitmap) : ByteArray{
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    // ByteArray -> Bitmap 변환
    @TypeConverter
    fun toBitmap(bytes : ByteArray) : Bitmap{
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

// 날짜 정보를 data 포멧으로 변경
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it!!) }
    }


    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
    @TypeConverter
    fun listToJson(value: List<Sido_code>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Sido_code>::class.java).toList()
    //currentTimeMillis
/*    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it!!)
        }
    }*/
}