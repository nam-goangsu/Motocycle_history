package pol3436.test.moto_history.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import pol3436.test.moto_history.Data.GasDao
import pol3436.test.moto_history.Model.DataClass.Defalt_Data
import pol3436.test.moto_history.Model.DataClass.Sido_code
import pol3436.test.moto_history.Model.DataClass.Sigun_code

class GasRepository(private val gasdao: GasDao) {



    val default_carname = gasdao.spinnerData()

    val default_carname1 = gasdao.spinnerData1()

//    val sidoCode = gasdao.sido_code()
//    val sigunCode = gasdao.sigun_code()*/
    //자동차 기본 정보 로드
    suspend fun insert_defalt_data(defaltData: Defalt_Data) {
        gasdao.add_DefaltData(defaltData)
    }

    fun insert_sido_data(sidoData: Array<Sido_code>) {
        gasdao.add_Sido(*sidoData)
    }
    fun insert_sigun_data(sigunData: Array<Sigun_code>) {
        gasdao.add_SigunCode(*sigunData)
    }

    suspend fun update_Defalt_Data(defaltData: Defalt_Data) {
        gasdao.update_DefaltData(defaltData)
    }

    fun delete_Defalt_Date(defaltData: Defalt_Data) {
        gasdao.del_DefaltData(defaltData)
    }


    fun get_sido_infor(): List<Sido_code>{
        return gasdao.sido_code()
    }
    //추가
/*        suspend fun select_Defalt_Date_id(id :Int) {
            gasdao.select_DefaltDate_select(id)
        }*/

/*    fun select_Gasinput_carname(carname: String): LiveData<List<Defalt_Data>> {
        return gasdao.select_DefaltDate(carname)
    }*/


    //수정


}


