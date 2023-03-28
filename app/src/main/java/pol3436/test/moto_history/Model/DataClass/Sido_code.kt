package pol3436.test.moto_history.Model.DataClass

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose


@Entity(tableName = "Sido_List")
data class Sido_code(
    @PrimaryKey(autoGenerate = true) //primary key는 자동으로 만들게 합니다.
    @ColumnInfo @NonNull
    @Expose
    val rowid: Int,
    val SidoCode: String,
    val SidoName: String
)