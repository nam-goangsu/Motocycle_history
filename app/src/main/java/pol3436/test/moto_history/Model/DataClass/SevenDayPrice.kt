package pol3436.test.moto_history.Model.DataClass

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
 
import java.util.*

//@Fts4
@Entity(tableName = "SevenDayPrice")//,primaryKeys  = ["rowid"])
data class SevenDayPrice( // 최근 7일간 전국 일일편균가격
    @PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo @NonNull
    val rowid: Long,
    val Date : Date =Date(), //날짜
    val PRODCD :String="", //제품구문
    val PRICE:Float=0.0f // 가격
)
