package pol3436.test.moto_history.Net

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.*
import pol3436.test.moto_history.Data.GasDatabase
import pol3436.test.moto_history.MainActivity
import pol3436.test.moto_history.Model.DataClass.OIL_
import pol3436.test.moto_history.Model.DataClass.Sido_code
import pol3436.test.moto_history.Model.DataClass.Sigun_code

import pol3436.test.moto_history.Model.DataClass.result_NET
import pol3436.test.moto_history.Net.Uri.Uri_data
import pol3436.test.moto_history.Repository.GasRepository
import pol3436.test.moto_history.Repository.ShareData
import pol3436.test.moto_history.SetupActivity
import pol3436.test.moto_history.utill.AppUtill
import pol3436.test.moto_history.utill.LayoutUtill.startActivity
import pol3436.test.moto_history.utill.Permission
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import kotlinx.coroutines.delay as delay1


class GetList(context: Context) {
    companion object {
        private lateinit var getsido_copy: Sido_code
        private lateinit var getgun_copy: Sigun_code


    }


    private var db = GasDatabase.getDatabase(context)!!
    private var Default_Dataset: Boolean =
        ShareData.prefs.getBoolean("sido", false)

    val gasDao = GasDatabase.getDatabase(context).gasDao()
    var repository: GasRepository = GasRepository(gasDao)

    var getsigun_copy: MutableList<Sigun_code> = mutableListOf()
    var getsido_copy1: MutableList<Sido_code> = mutableListOf()


    init {

        getsigun_copy.clear()
        getsido_copy1.clear()
        if (Default_Dataset == false) {
            GlobalScope.launch(Dispatchers.Default) {
                async { get_sidolist() }.await()

                async {
                    if (repository.get_sido_infor().isNotEmpty()) {
                        GlobalScope.launch(Dispatchers.Default) {
                            for (i: Int in 0..repository.get_sido_infor().size-1) {
                                get_sigunlist(
                                    repository.get_sido_infor().get(i).SidoCode.toString(),
                                    repository.get_sido_infor().get(i).SidoName.toString()
                                )
                            }

                            delay1(500)
                        }
                    }

                }.await()


            }
        }
    }

    fun get_sidolist() {
        ShareData.getApiService().get_sido(Uri_data.MyCode, Uri_data.Retrun_type).enqueue(object :
            Callback<result_NET> {
            override fun onResponse(call: Call<result_NET>, response: Response<result_NET>) {

                if (response.body()!!.RESULT.OIL.isNotEmpty()) {
                    getsido_copy1 = getsido_copy1.toMutableList()

                    for (index: Int in 0..response.body()!!.RESULT.OIL.size) {

                        CoroutineScope(Dispatchers.IO).async {
                            getsido_copy = Sido_code(
                                0,
                                response.body()!!.RESULT.OIL[index].AREACD,
                                response.body()!!.RESULT.OIL[index].AREANM
                            )
                            getsido_copy1.add(getsido_copy)
                            Log.d(
                                "test",
                                " sido code : " + getsido_copy1.get(index).SidoCode.toString()
                            )
                        }
                    }
                    CoroutineScope(Dispatchers.IO).async {
                        db.gasDao().add_Sido( //23-01-03 06:05 db 연동 잡아야지
                            *getsido_copy1.toTypedArray()
                        )
                        getsido_copy1.clear()
                    }
                }
            }


            override fun onFailure(call: Call<result_NET>, t: Throwable) {
                Log.d("test_net", "Api get failed call :" + call + " exception : " + t)
            }
        }
        )
    }
// 개선안 시도 만 받고 시군을 받는 경우에 로드

    fun get_sigunlist(sidocode: String, sidonm: String) {

        ShareData.getApiService_sigun()
            .get_sido(Uri_data.MyCode, Uri_data.Retrun_type, sidocode)
            .enqueue(object :
                Callback<result_NET> {
                override fun onResponse(
                    call: Call<result_NET>,
                    response: Response<result_NET>
                ) {
                    getsigun_copy.clear()

                    GlobalScope.launch(Dispatchers.Default) {
                        if (response.body()!!.RESULT.OIL.isNotEmpty()) {
                            getsigun_copy = getsigun_copy.toMutableList()
                            //Log.d("test", " response : "+ response.body().toString())
                            for (index: Int in 0..response.body()!!.RESULT.OIL.size) {

                                GlobalScope.async(Dispatchers.Default) {
                                    getgun_copy = Sigun_code(
                                        0,
                                        sidocode, sidonm,
                                        response.body()!!.RESULT.OIL[index].AREACD,
                                        response.body()!!.RESULT.OIL[index].AREANM
                                    )

                                    getsigun_copy.add(getgun_copy)
                                    Log.d(
                                        "test",
                                        " sigun code : " + getsigun_copy.get(index).SigunCode.toString()
                                    )
                                }
                            }
                            repository.insert_sigun_data(*getsigun_copy.toTypedArray())
                            getsigun_copy.clear()
                            /*               db.gasDao().add_SigunCode( //23-01-03 06:05 db 연동 잡아야지
                                               *getsigun_copy.toTypedArray()
                                           )*/

                        } else {
                            Log.d(
                                "test",
                                " sigun code not "
                            )
                        }

                    }
                }

                override fun onFailure(call: Call<result_NET>, t: Throwable) {
                    Log.d("test_net", "Api get failed call :" + call + " exception : " + t)
                }
            }
            )
    }

}



