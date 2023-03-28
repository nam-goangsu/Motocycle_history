package pol3436.test.moto_history.Model.DataClass

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


//@Fts4
 
@Entity(tableName = "Sigun_List")//, primaryKeys = ["rowid","SigunCode","SigunName"])//,indices = [Index(value = ["SidoCode","SidoName"])]) //시군 정보
data class Sigun_code(
    @PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo @NonNull
    val rowid: Int,
    val SidoCode: String,
    val SidoName: String,
    val SigunCode: String,
    val SigunName: String
)

