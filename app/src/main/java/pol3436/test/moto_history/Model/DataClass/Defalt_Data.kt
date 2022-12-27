package pol3436.test.moto_history.Model.DataClass

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*


@Entity(tableName = "Defalt_Data")//,primaryKeys  = ["rowid"])//,indices = [Index(value = ["Car_Name","Car_Maker"])])
data class Defalt_Data(
    @PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo @NonNull
    val rowid: Long,
    val Car_Name: String,
    val Car_Maker: String,
    val Car_Type: String,
    val Car_TankLiter: Int,// =0,
    val Car_TankEmerLiter: Int,// =0,
    val Car_TankMaxKan: Int,// =8,
    val Car_TankReChargeKan: Int,// =2,
    val Car_AvgType: String,// = ,
    val Gps_GetCheck: Boolean,
    val Opnet_GetCheck: Boolean,
    var image: Bitmap? = null,
   // @Ignore var Image : Bitmap? = null
)


/*   constructor() : this(
    rowid=0,
    Car_Name = null.toString(),
    Car_Maker = null.toString(),
    Car_Type = "메뉴얼",
    Car_TankLiter = 0,
    Car_TankEmerLiter = 0,
    Car_TankMaxKan = 8,
    Car_TankReChargeKan = 2,
    Car_AvgType ="Km/L",
    Gps_GetCheck = false,
    Opnet_GetCheck = false//,Image = null
)*/