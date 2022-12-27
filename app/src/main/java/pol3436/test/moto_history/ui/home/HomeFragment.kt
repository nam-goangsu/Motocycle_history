package pol3436.test.moto_history.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pol3436.test.moto_history.Model.DataClass.Defalt_Data
import pol3436.test.moto_history.Model.Net_data
import pol3436.test.moto_history.Model.Uri.Uri_data
import pol3436.test.moto_history.Model.Vo.Sido_vo
import pol3436.test.moto_history.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.properties.Delegates

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mDefaltViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        mDefaltViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        mDefaltViewModel.readAllData.observe(
            viewLifecycleOwner,
            Observer { Defalt_Data ->
                if (Defalt_Data.isEmpty()) {
                    // 기본 데이터 추가 화면 실행
                } else {
                    //.setData(Defalt_Data)
                    // 아닌경우 화면 노출
                }
            }
        )



        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


//test()
/*      val textView: TextView = binding.textHome
   *//*   homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*//*

        binding.button.setOnClickListener {
            var time1: LocalDateTime? = LocalDateTime.now()
            var time: String? = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }*/


/*       val call: Call<Sido_vo> = Net_data.networkService.getList(
           Uri_data.areacd,
           Uri_data.MyCode,
           Uri_data.Retrun_type,
           ""
       )//

       call?.enqueue(object : Callback<Sido_vo> {
           override fun onResponse(call: Call<Sido_vo>, response: Response<Sido_vo>) {
               if (response.isSuccessful()) {
                   println(response.toString())
                   Log.d("test_as", response.body()?.OIL.toString())
               }
           }

           override fun onFailure(call: Call<Sido_vo>, t: Throwable) {

           }
       })*/

