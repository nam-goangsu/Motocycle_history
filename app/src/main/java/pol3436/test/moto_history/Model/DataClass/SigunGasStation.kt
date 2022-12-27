package pol3436.test.moto_history.Model.DataClass

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
 
import java.util.*


@Entity(tableName = "SigunGasStation")//, primaryKeys = ["rowid"])
data class SigunGasStation(
    @PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo @NonNull
    val rowid: Long,
    val Day : Date = Date(),
    val SIGUNCD :Int =0,
    val SIGUNNM:String ="",
    val PRODCD :String ="",
    val PRICE :Float =0.0f,
    val DIFF : Float =0.0f
)
