package pol3436.test.moto_history.Model.DataClass

import android.graphics.Bitmap
import androidx.annotation.NonNull
import androidx.room.*
import java.util.*
import android.os.Parcelable




@Entity(
    tableName = "Change_Item"
)
data class Change_Item(
    @PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo @NonNull
    val rowid: Long,
    var Car_Name:String = "",
    var PartName: String = "",
    var Change_check: Boolean = false,
    var Start_Date: Date = Date(),
    var Change_Date: Date = Date(),
    var Start_ODD: Int=0,
    var Change_ODD: Int=0,
    var Change_Limit: Float=0.0f,
    var Ea: Int=0,
    var Won: Int=0,
    var Danga: Int=0,
    var Etc: String="",
    var image: Bitmap? = null
    //,    @Ignore var Image :Bitmap? = null
)
   
/**
 * ,indices = [Index(value = ["SidoCode","SidoName"])] //Fts4시 문자 검색시 특정 열 색인 생성
 *
 *
 * 부품 교체 관리 및 확인 유지 보수 정보 작성 관리
 *
 */