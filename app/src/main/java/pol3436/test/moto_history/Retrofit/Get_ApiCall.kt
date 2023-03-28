package pol3436.test.moto_history.Retrofit



import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import pol3436.test.moto_history.Model.DataClass.*
import pol3436.test.moto_history.Net.Uri.Uri_data
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*



interface Get_ApiCall {
    @GET("/api/areaCode.do")
    fun get_sido(
    @Query("code")code:String?,
    @Query("out")out:String?
    ): Call<result_NET>
}
interface Get_ApiCall_sigun {
    @GET("/api/areaCode.do")
    fun get_sido(
        @Query("code")code:String?,
        @Query("out")out:String?,
        @Query("area")area:String?
    ): Call<result_NET>
}


interface Get_Api_DefaultCall{
    @GET("/api/{Path}")
    fun get_default(
        @Path("Path")uripath:String?,
        @QueryMap queryval:Map<String, String>?
    ): Call<result_NET>
    //mapOf<String, String>("" to "", "" to "")
}


/**
when 문으로 api 동작
 */
//val api: Get_ApiCall = retrofit.create(Get_ApiCall::class.java)
