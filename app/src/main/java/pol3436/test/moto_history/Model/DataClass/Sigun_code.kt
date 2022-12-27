package pol3436.test.moto_history.Model.DataClass
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
 

//@Fts4

@Entity(tableName = "Sigun_List", primaryKeys = ["rowid","SigunCode","SigunName"])//,indices = [Index(value = ["SidoCode","SidoName"])]) //시군 정보
data class Sigun_code(
   //@PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo @NonNull
    val rowid: Long,
    @ColumnInfo @NonNull
    val SigunCode: Int,
    @ColumnInfo @NonNull
    val SigunName: String
)
