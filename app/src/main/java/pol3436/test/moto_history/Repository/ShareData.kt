package pol3436.test.moto_history.Repository

import android.app.Application
import android.content.Context
import pol3436.test.moto_history.Model.Uri.Uri_data
import pol3436.test.moto_history.Retrofit.Get_ApiCall
import pol3436.test.moto_history.utill.LayoutUtill
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// app 실행하면서 사전 동작들
class ShareData : Application() {
    //코틀린 상에서 쉐어 데이터 저장 설정
    companion object {
        var networkService: Get_ApiCall
        lateinit var prefs: PrefsManager


        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(Uri_data.MainUri)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        init {
            networkService = retrofit.create(Get_ApiCall::class.java)
        }
    }

    override fun onCreate() {
        prefs = PrefsManager(applicationContext)
        super.onCreate()
        LayoutUtill.height = resources.displayMetrics.heightPixels
        LayoutUtill.width = resources.displayMetrics.widthPixels
    }
}

class PrefsManager(context: Context) {
    private val prefs = context.getSharedPreferences("getDefalt", Context.MODE_PRIVATE)

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return prefs.getBoolean(key, defValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun getString(key: String, defValue: String): String{
        return prefs.getString(key, defValue).toString()
    }
    fun setString(key: String, value: String) {
        prefs.edit().putString(key,value).apply ()
    }


}