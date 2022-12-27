package pol3436.test.moto_history.Model.DataClass

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
 


 
/*@Fts4*/
@Entity(tableName = "RadiusGasStation")//,primaryKeys  = ["rowid"])
    //,indices = [Index(value = ["UNI_ID","POOL_DIV_CD","OS_NM"])]) // 현 위치 중심 반경 내 주유소
data class RadiusGasStation(
    @PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo @NonNull
    val rowid: Long,
    val UNI_ID: String="",  // 주유소코드
    val POOL_DIV_CD: String="", // 상표
    val OS_NM: String="", // 상호
    val PRODCD :String="", //제품구문
    val PRICE: Float=0.0f, // 가격
    val DISTANCE: Float=0.0f, // 위치로 부터 거리
    val GIS_X_COOR: Float=0.0f, // gis 좌표
    val GIS_Y_COOR: Float=0.0f
) 
