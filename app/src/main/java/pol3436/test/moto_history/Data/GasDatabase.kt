package pol3436.test.moto_history.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pol3436.test.moto_history.Model.DataClass.*


/*
[]
    */
@Database(entities =[Defalt_Data::class,AllGasStation::class,Change_Item::class,Defalt_Data_Item::class,
    Gas_InputList::class,RadiusGasStation::class,SevenDayPrice::class,
    Sido_code::class, Sigun_code::class, SigunGasStation::class,SigunLowPrice::class],version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GasDatabase : RoomDatabase() {

    abstract fun gasDao(): GasDao

    companion object{
        @Volatile //다른 thread에서 접근 가능하게 만드는 것입니다.
        private var INSTANCE: GasDatabase? = null

        fun getDatabase(context: Context):GasDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){ //synchronized는 새로운 데이터베이스를 instance시킵니다.
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GasDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}