package pol3436.test.moto_history.Model.DataClass

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import java.util.*
import androidx.room.PrimaryKey
 

 
// 주유내역
//@Fts4
@Entity(tableName = "Gas_InputList")//,primaryKeys  = ["rowid"])//,indices = [Index(varue = ["Date","GasStation"])])
data class Gas_InputList(
    @PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo @NonNull
    val rowid: Long,
    var Car_Name: String = "",
    var Date : Date=Date(),
    var ODD : Int=0,
    var Liter : Long=0,
    var Won : Int=0,
    var Danga : Long=0,
    var PRODCD :String="", //제품구문
    var Aftertank : Long=0,
    var Beforetank : Long=0,
    var GasStation :  String="",
    var Gps_Lat: Float=0.0f,
    var Gps_Long: Float=0.0f,
    var GIS_X_COOR: Float=0.0f, // gis 좌표
    var GIS_Y_COOR: Float=0.0f,
    var Etc :String="",var image: Bitmap? = null,/*,
      @Ignore   var Image :Bitmap?=null*/
) 

/*


/// 전국 주유소 평균 가격
// 
@Entity(tableName = "AllGasStation",primaryKeys  = ["TRADE_DT","PRODCD"])//,"PRODCD"
data class AllGasStation( //프라머리 키는 id로
    var TRADE_DT : String, // 일자
    var PRODCD :String, //제품구분코드
    var PRODNM : String?, //제품명
    var PRICE : Float?, // 가격
    var DIFF : Float? // 전일 대비 등락율
)

 */