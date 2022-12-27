package pol3436.test.moto_history.Model.DataClass


import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
 


@Entity(tableName = "Sido_List")//, primaryKeys = ["rowid"])//,indices = [Index(value = ["SidoCode","SidoName"])])
data class Sido(
    @PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo @NonNull
    val rowid: Long,
    val SidoCode : Int=0,
    val SidoName : String =""
)