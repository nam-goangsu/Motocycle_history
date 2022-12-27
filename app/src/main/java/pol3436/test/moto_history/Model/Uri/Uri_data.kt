package pol3436.test.moto_history.Model.Uri

import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class Uri_data {
    companion object {
        val uni_fromat = DecimalFormat("A0000000")//A0027042
        val data_format = DateTimeFormatter.ofPattern("yyyyMMdd")
        val data_format1 = DecimalFormat("20200000")
        val Sigun_format = DecimalFormat("0000")
        val Sido_format = DecimalFormat("00")

        fun data_format(data: String): String {
            val current = LocalDateTime.now()// 현 로컬 데이터
            val data_resultformat = data.format(data_format)
            return data_resultformat
        }

        val MainUri: String = "https://www.opinet.co.kr"
        val MyCode: String = "F220207019"
        val Retrun_type: String = "json"

        //lateinit var prodcd1: String


        val prodcd_gas: String = "&prodcd=B027"
        val prodcd_HighGas: String = "&prodcd=B034"

        val prodcd: String = "&prodcd=" + prodcd_gas // 석유 타입 고급 휘발유  B034 /휘발유 B027 / 경유 D047


        var SidoCode1 = 1
        var SidoCode2 = Sido_format.format(SidoCode1)
        var SidoCode: String = "&area=" + SidoCode2 //7일간 전국 일일 지역
        var SidoCode_gas: String = "&sido=" + Sido_format.format(SidoCode1)

        var SigunCode1 = 1
        val SigunCode: String = "&sigun=" + Sigun_format.format(SigunCode1)
        val SigunCode_area: String = "&area=" + Sigun_format.format(SigunCode1) //7일간 전국 일일 지역

        var Seven_Data1 = 20221108 // 날자 포멧
        val Seven_Data: String = "date=" + data_format1.format(Seven_Data1)

        //***************************************************************************//
        ////현재 전국 주유소 평균가격 1
        val Uri_avgAll: String = "avgAllPrice.do?"
        val AllKorean: String = MainUri + Uri_avgAll + MyCode + Retrun_type

        //***************************************************************************//
        ////시군구 주유소 평균가 3
        val Uri_Sigun: String = "avgSigunPrice.do?"

        //시군구 일반 기름
        val Sigun_Nomal_Gas: String =
            MainUri + Uri_Sigun + MyCode + Retrun_type + SidoCode_gas + SigunCode + prodcd_gas

        //시군구 고급
        val Sigun_High_Gas: String =
            MainUri + Uri_Sigun + MyCode + Retrun_type + SidoCode_gas + SigunCode + prodcd_HighGas

        //***************************************************************************//
        ////최근 7일간 전국 일일 평균가 4 //근 일주일내 일일 가격
        val Uri_Seven_avg: String = "avgRecentPrice.do?"

        //최근 7일중 평균 노말
        val Sigun_Nomal_Seven_Gas: String =
            MainUri + Uri_Seven_avg + MyCode + Retrun_type + prodcd_gas

        //최근 7일중 평균 고급
        val Sigun_High_Seven_Gas: String =
            MainUri + Uri_Seven_avg + MyCode + Retrun_type + prodcd_HighGas

        //최근 7일중 하루 노말
        val Sigun_Nomal_Seven_one_Gas: String =
            MainUri + Uri_Seven_avg + MyCode + Retrun_type + Seven_Data + prodcd_gas

        //최근 7일중 하루 고급
        val Sigun_High_Seven_oneGas: String =
            MainUri + Uri_Seven_avg + MyCode + Retrun_type + Seven_Data + prodcd_HighGas

        //    //***************************************************************************//
//    //// 전국/지역별 최저가 주유소 top20 8
        var Cnt_count: Int = 20
        val Cnt: String = "&cnt=" + Cnt_count.toString()
        val LowTop: String = "lowTop10.do?"

        //시도 일반 기름 최저가
        val LowTop20_nomal_sido: String =
            MainUri + LowTop + MyCode + Retrun_type + prodcd_gas + SidoCode + Cnt

        //시군 일반 기름 최저가
        val LowTop20_nomal_sigun: String =
            MainUri + LowTop + MyCode + Retrun_type + prodcd_gas + SigunCode_area + Cnt

        //시도 고옥탄 기름 최저가
        val LowTop20_high: String =
            MainUri + LowTop + MyCode + Retrun_type + prodcd_HighGas + SidoCode + Cnt

        //시군 고옥탄 기름 최저가
        val LowTop20_high_sigun: String =
            MainUri + LowTop + MyCode + Retrun_type + prodcd_HighGas + SigunCode_area + Cnt

        //
//    //***************************************************************************//
//    ////반경내 주유소 검색 9
//
        val Mypoint = "aroundAll.do?"
        var x_point: Float = 0.0f
        var y_point: Float = 0.0f
        val Points: String = "&x=" + x_point.toString() + "&y=" + y_point.toString()
        val radius_arr: Array<Int> = arrayOf(100, 500, 1000, 3000, 5000)
        var radius: Int = 5000
        val radius_f: String = "&radius=" + radius_arr.toString() + radius.toString()

        val sort_price = "1"
        val sort_radius = "2"

        //lateinit var sort_type: String
        val sort = "&sort=" + sort_price


        var MyPoint_radius =
            MainUri + Mypoint + MyCode + Points + radius_f + sort + prodcd + Retrun_type.toString()
//
//    //var MyPoint_radius : String = MainUri+"aroundAll.do?"+MyCode+Points+radius_f+
//    //
//    // 하기 내용은 검토
//    //***************************************************************************//
//    ////주유소 상세 정보 id 10

        val GasST_Uri = "detailById.do?"
        var UniId = 1
        var Gas_Station_UniId = uni_fromat.format(UniId)

        //    //***************************************************************************//
//    ////상호로 주유소 검색 11
        val SearchByName: String = "searchByName.do?"

        val sanghoname: String = "&osnm="
        //***************************************************************************//
        ////지역 정보 가져오기 19

        val areacd: String = "areaCode.do?"
        var area_sigun: String = ""
        val getSidoCode: String = MainUri + areacd + MyCode + Retrun_type
        val getSigunCode: String = MainUri + areacd + MyCode + Retrun_type + SidoCode
    }
}