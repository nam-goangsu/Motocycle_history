package pol3436.test.moto_history.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pol3436.test.moto_history.Data.GasDatabase
import pol3436.test.moto_history.Model.DataClass.Defalt_Data
import pol3436.test.moto_history.Repository.GasRepository


class HomeViewModel(application: Application): AndroidViewModel(application) {

    /*val userDao = UserDatabase.getDatabase(application).userDao()
    repository = UserRepository(userDao) //이니셜라이즈 해줍니다.
    readAllData = repository.readAllData // readAlldata는 repository에서 만들어줬던 livedata입니다.*/


    val readAllData: LiveData<List<Defalt_Data>>
    private val repository: GasRepository

    init {
        val gasDao = GasDatabase.getDatabase(application).gasDao()
        repository = GasRepository(gasDao)
        readAllData = repository.default_CarData // 자동차 정보

    }

    fun addDefaltData(DefaltData: Defalt_Data){// 파라미터에 만든 데이터클래스가 들어갑니다.
        viewModelScope.launch(Dispatchers.IO) { //코루틴 활성화 dispatcherIO는 백그라운드에서 실행
            repository.insert_defalt_data(DefaltData) //repository에 adduser함수 불러오기
        }
    }



}