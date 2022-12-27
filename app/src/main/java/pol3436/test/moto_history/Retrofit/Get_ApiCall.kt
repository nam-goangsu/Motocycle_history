package pol3436.test.moto_history.Retrofit

import pol3436.test.moto_history.Model.Vo.RESULT_LIST
import pol3436.test.moto_history.Model.Vo.Sido_vo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Get_ApiCall {
    @GET("/api/areaCode.do")
    fun getList(
    @Query("code")code:String?,
    @Query("out")out:String?
    ): Call<RESULT_LIST>
}