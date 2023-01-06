package pol3436.test.moto_history.Repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import pol3436.test.moto_history.Data.GasDao
import pol3436.test.moto_history.Model.DataClass.Defalt_Data
import pol3436.test.moto_history.Model.DataClass.Gas_InputList

class GasRepository(private val gasdao: GasDao) {


    val default_carname:LiveData<List<String>> = gasdao.spinnerData()
        //자동차 기본 정보 로드
        suspend fun insert_defalt_data(defaltData: Defalt_Data) {
            gasdao.add_DefaltData(defaltData)
        }

        suspend fun update_Defalt_Data(defaltData: Defalt_Data) {
            gasdao.update_DefaltData(defaltData)
        }

         fun delete_Defalt_Date(defaltData: Defalt_Data) {
            gasdao.del_DefaltData(defaltData)
        }

        //추가
/*        suspend fun select_Defalt_Date_id(id :Int) {
            gasdao.select_DefaltDate_select(id)
        }*/

        fun select_Gasinput_carname(carname :String) :Flow<List<Gas_InputList>> {
           return gasdao.select_DefaltDate(carname)
        }

        //수정


}


