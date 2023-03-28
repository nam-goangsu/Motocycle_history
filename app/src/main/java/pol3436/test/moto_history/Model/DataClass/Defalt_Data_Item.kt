package pol3436.test.moto_history.Model.DataClass

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import java.util.*
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


 
/*@Fts4*/
@Entity(tableName = "Defalt_Data_Item")//,primaryKeys  = ["rowid"])//,indices = [Index(varue = ["Car_Name","Item_Name"])])
data class Defalt_Data_Item(
    @PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo @NonNull
    val rowid: Long,
    var Car_Name: String = "",
    var Item_Name: String = "",
    var Item_CheckKm : Long =0,
    var Item_CheckDate: Date = Date(),var image: Bitmap? = null,
    /*,
 @Ignore var Image : Bitmap? = null*/
)
