package pol3436.test.moto_history.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import pol3436.test.moto_history.Data.*
import pol3436.test.moto_history.Model.DataClass.*
import pol3436.test.moto_history.Net.GetList
import pol3436.test.moto_history.Repository.*
import pol3436.test.moto_history.utill.Utills


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    /*val userDao = UserDatabase.getDatabase(application).userDao()
    repository = UserRepository(userDao) //이니셜라이즈 해줍니다.
    readAllData = repository.readAllData // readAlldata는 repository에서 만들어줬던 livedata입니다.*/

    // 오늘 날자 기준 정보 가져오는거
    // 마지막 주유 했던 정보
    // 이번달 주유 정보
    // 없음 null


     lateinit var spinnerData: LiveData<List<String>>
    val spinnerData1: LiveData<List<Defalt_Data>>

    //val sigunData : LiveData<List<Sido_code>>
    private val repository: GasRepository

    init {
        val gasDao = GasDatabase.getDatabase(application).gasDao()
        repository = GasRepository(gasDao)
        //spinnerData = repository.default_carname as MutableStateFlow<List<String>>
        spinnerData1 = repository.default_carname1
    }

    fun spinnerData_fun(){
        viewModelScope.launch {
            spinnerData = repository.default_carname
        }
    }
    fun insert_defalt_data(DefaltData: Defalt_Data) {// 파라미터에 만든 데이터클래스가 들어갑니다.
        viewModelScope.launch(Dispatchers.IO) { //코루틴 활성화 dispatcherIO는 백그라운드에서 실행
            repository.insert_defalt_data(DefaltData) //repository에 adduser함수 불러오기
        }
    }

/*    fun select_Gasinput_carname(carname: String): LiveData<List<Defalt_Data>> {
        return repository.select_Gasinput_carname(carname).asLiveData()
    }*/


}