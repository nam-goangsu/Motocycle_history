package pol3436.test.moto_history.Model.DataClass

import androidx.room.Entity
import androidx.room.*
import java.util.*
import android.os.Parcelable
import androidx.annotation.NonNull
import kotlinx.android.parcel.Parcelize

 
//@Fts4(contentEntity = AllGasStation::class)
@Entity(tableName = "AllGasStation" )//,indices = [Index(value = ["RODCD","PRODNM"])]))
data class AllGasStation( //프라머리 키는 id로
    @PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo @NonNull
    val rowid: Long,
    val Car_Name: String = "",
    val TRADE_DT: Long = 0,
    @ColumnInfo @NonNull
    val PRODCD: String, //제품구분코드
    val PRODNM: String="", //제품명
    val PRICE: Float = 0.0f, // 가격
    val DIFF: Float = 0.0f // 전일 대비 등락율
)