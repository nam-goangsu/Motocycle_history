package pol3436.test.moto_history.Data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow
import pol3436.test.moto_history.Model.DataClass.*


@Dao
interface GasDao {

    /*  //삽입 시작
      @Insert(onConflict = OnConflictStrategy.IGNORE)
      fun add_AllGasStation(vararg allGasStation: AllGasStation)
  */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add_DefaltData(DefaltData: Defalt_Data)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add_ChangeItem(changeItem: Change_Item)


    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    fun add_DefaltDataItem(DefaltDataItem: Defalt_Data_Item)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add_GasInputList(GasInputList: Gas_InputList)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add_RadiusGasStation(RadiusGasStation: RadiusGasStation)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add_SevenDayPrice(SevenDayPrice: SevenDayPrice)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add_Sido(sido: Sido)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add_SigunCode(SigunCode: Sigun_code)

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    fun add_SigunGasStation(sigunGasStation: SigunGasStation)

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    fun add_SigunLowprice(sigunLowPrice: SigunLowPrice)

    //삽입 종료

    //갱신시작
    @Update
    fun update_AllGasStation(vararg allGasStation: AllGasStation)

    @Update
    fun update_ChangeItem(vararg changeItem: Change_Item)

    @Update
    suspend fun update_DefaltData(DefaltData: Defalt_Data)

    @Update
    fun update_DefaltDataItem(vararg DefaltDataItem: Defalt_Data_Item)

    @Update
    fun update_GasInputList(vararg GasInputList: Gas_InputList)

    @Update
    fun update_RadiusGasStation(vararg RadiusGasStation: RadiusGasStation)

    @Update
    fun update_SevenDayPrice(vararg SevenDayPrice: SevenDayPrice)

    @Update
    fun update_Sido(vararg sido: Sido)

    @Update
    fun update_SigunCode(vararg SigunCode: Sigun_code)

    @Update
    fun update_SigunGasStation(vararg sigunGasStation: SigunGasStation)

    @Update
    fun update_SigunLowprice(vararg sigunLowPrice: SigunLowPrice)
    //갱신끝


    //데이터 조회시작
/*    @Query("SELECT PRODCD=:prodcd FROM AllGasStation ORDER BY rowid ASC")
    fun select_AllGasStation(prodcd: String): LiveData<List<AllGasStation>>


    @Query("SELECT * FROM Change_Item ORDER BY rowid DESC")   /// DESC LIMIT 1(내림차순 정리후 1개만 뺴는
    fun select_ChangeItem(): LiveData<List<Change_Item>>*/

    @Query("SELECT * FROM  Gas_InputList WHERE Car_Name = :carname ORDER BY rowid DESC")
    fun select_DefaltDate(carname: String): Flow<List<Gas_InputList>>


    @Query("SELECT Car_Name FROM Defalt_Data ORDER BY rowid ASC")
    fun spinnerData(): LiveData<List<String>>
    /*

       @Query("SELECT * FROM Defalt_Data ORDER BY rowid =:id DESC")
       fun select_DefaltDate_select(id: Int): LiveData<List<Defalt_Data>>



       @Query("SELECT * FROM Defalt_Data_Item ORDER BY rowid DESC")
       fun select_DefaltDateItem(): LiveData<List<Defalt_Data_Item>>

      @Query("SELECT * FROM Gas_InputList ORDER BY rowid DESC")
       fun select_GasInputList(): LiveData<List<Gas_InputList>>*/

/*
    @Query("SELECT * FROM RadiusGasStation ORDER BY DISTANCE ASC")
    fun select_RadiusGasStation(): LiveData<List<RadiusGasStation>>
*/

   /* @Query("SELECT * FROM SevenDayPrice ORDER BY Date DESC")
    fun select_SevenDayPrice(distance: Float): LiveData<List<SevenDayPrice>>

    @Query("SELECT PRODCD=:prodcd FROM SevenDayPrice ORDER BY Date DESC LIMIT :Days")
    fun select_SevenDayPrice_Limit(prodcd: String, Days: Int): LiveData<List<SevenDayPrice>>

    @Query("SELECT * FROM Sido_List ORDER BY SidoCode ASC")
    fun select_Sido(): LiveData<List<Sido>>

    @Query("SELECT * FROM Sigun_List ORDER BY SigunCode ASC")
    fun select_Sigun(): LiveData<List<Sigun_code>>

    @Query("SELECT * FROM SigunGasStation ORDER BY SIGUNCD ASC")
    fun select_SigunGasStation(): LiveData<List<SigunGasStation>>

    @Query("SELECT * FROM SigunLowPrice ORDER BY PRICE ASC")
    fun select_SigunLowPrice(): LiveData<List<SigunLowPrice>>*/


//데이터 조회 끝

    /*


        //전체 삭제시작
        @Delete
        fun del_AllGasStation(vararg allGasStation: AllGasStation)

        @Delete
        fun del_ChangeItem(vararg changeItem: Change_Item)
    */
    @Delete
    fun del_DefaltData(DefaltData: Defalt_Data)

    @Delete
    fun del_DefaltDataItem(vararg DefaltDataItem: Defalt_Data_Item)

    @Delete
    fun del_GasInputList(vararg GasInputList: Gas_InputList)

    @Delete
    fun del_RadiusGasStation(vararg RadiusGasStation: RadiusGasStation)

    @Delete
    fun del_SevenDayPrice(vararg SevenDayPrice: SevenDayPrice)

    @Delete
    fun del_Sido(vararg sido: Sido)

    @Delete
    fun del_SigunCode(vararg SigunCode: Sigun_code)

    @Delete
    fun del_SigunGasStation(vararg sigunGasStation: SigunGasStation)

    @Delete
    fun del_SigunLowprice(vararg sigunLowPrice: SigunLowPrice)

    //전체 삭제 끝

    @Query("DELETE FROM Sido_List WHERE SidoCode =:SidoCode")
    fun dels_sido_Data(SidoCode: Int): Int

    @Query("DELETE FROM Sigun_List WHERE SigunCode =:SigunCode")
    fun dels_sigun_Data(SigunCode: Int): Int

    @Query("DELETE FROM Defalt_Data WHERE rowid =:Car_id")
    fun dels_DefaltUser_Data(Car_id: Int): Int


/*
    //모두지웁니다.
    @Query("DELETE FROM Sido_List")
    fun delAllSido()

    @Query("DELETE FROM Sigun_List")
    fun delAllSigun()*/
}

/*OnConflictStrategy.ABORT	충돌이 발생할 경우 처리 중단
OnConflictStrategy.FAIL	충돌이 발생할 경우 실패처리
OnConflictStrategy.IGNORE	충돌이 발생할 경우 무시
OnConflictStrategy.REPLACE	충돌이 발생할 경우 덮어쓰기
OnConflictStrategy.ROLLBACK	충돌이 발생할 경우 이전으로 되돌리기


vararg https://roomedia.tistory.com/entry/Room%EC%97%90-%EB%8D%B0%EC%9D%B4%ED%84%B0-Insert-%EC%8B%9C-%EB%B3%B4%EB%8B%A4-%ED%9A%A8%EC%9C%A8%EC%A0%81%EC%9D%B8-%EB%B0%A9%EB%B2%95%EC%9D%80?category=838716
=> 개별적인 입력이 아닌 array 방식으로 처리
여러개의 쿼리를 @Transaction 어노테이션을 사용하여 한 개의 트랜잭션으로 묶을 수 있습니다.
*/
