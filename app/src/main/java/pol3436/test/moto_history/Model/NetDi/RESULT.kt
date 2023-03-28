package pol3436.test.moto_history.Model.DataClass

import com.google.gson.annotations.SerializedName


data class result_NET(
    @SerializedName("RESULT")
    val RESULT: RESULT
)
data class RESULT(
    @SerializedName("OIL")
    val OIL: List<OIL_>
)

data class OIL_(
    @SerializedName("AREA_CD")
    val AREACD: String,
    @SerializedName("AREA_NM")
    val AREANM: String
)