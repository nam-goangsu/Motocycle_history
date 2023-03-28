package pol3436.test.moto_history.Model.DataClass

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import pol3436.test.moto_history.CustomDialog


 
@Entity(tableName = "Defalt_Data")//,primaryKeys  = ["rowid"])//,indices = [Index(value = ["Car_Name","Car_Maker"])])
data class Defalt_Data(
    @PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo
    //@NonNull
    val rowid: Int,
    val Car_Name: String,
    val Car_Maker: String,
    val Car_CC: Int,
    val Car_Type: String,
    val Start_Odd:Long,
    val Car_TankLiter: Int,// =0,
    val Car_TankMaxKan: Int =0,// =8,
    val Car_TankReChargeKan: Int=0,// =2,
    val Car_TankEmerLiter: Int,// =0,
    val Car_AvgType: String,// = ,
    val Gps_GetCheck: Boolean = false,
    val Opnet_GetCheck: Boolean= false,
    /*var Image: Bitmap? = null*/
    val Image : Bitmap? = null,
    val create_Time: Long = 0L
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