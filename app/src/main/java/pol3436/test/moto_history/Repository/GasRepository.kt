package pol3436.test.moto_history.Repository

import androidx.lifecycle.LiveData
import pol3436.test.moto_history.Data.GasDao
import pol3436.test.moto_history.Model.DataClass.Defalt_Data

class GasRepository(private val gasdao: GasDao) {

    val default_CarData: LiveData<List<Defalt_Data>> = gasdao.select_DefaltDate()


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
        suspend fun select_Defalt_Date_id(id :Int) {
            gasdao.select_DefaltDate_select(id)
        }
        //수정


}


