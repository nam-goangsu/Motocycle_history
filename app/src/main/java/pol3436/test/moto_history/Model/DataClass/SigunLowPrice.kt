package pol3436.test.moto_history.Model.DataClass

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


 
@Entity(tableName = "SigunLowPrice")//, primaryKeys = ["rowid"]) // 시군구 최저가
data class SigunLowPrice(
    @PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo @NonNull
    val rowid: Long,
    @ColumnInfo @NonNull
    val UNI_ID : String,
    val PRODCD :String="", //제품구문
    val PRICE :Float =0.0f,
    @ColumnInfo @NonNull
    val POOL_DIV_CD : String,
    val OS_NM:String ="",
    val VAN_ADR:String="",
    val NEW_ADR:String="",
    val GIS_X_COOR:Float=0.0f,
    val GIS_Y_COOR: Float=0.0f
)
