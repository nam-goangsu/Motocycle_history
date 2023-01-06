package pol3436.test.moto_history.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pol3436.test.moto_history.CustomDialog
import pol3436.test.moto_history.Repository.ShareData
import pol3436.test.moto_history.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mDefaltViewModel: HomeViewModel
    private lateinit var spinner: Spinner
    private var arraylist_spinner  = arrayListOf<String>()
    private lateinit var adapter1 : ArrayAdapter<String>
    private var lastselect:String=ShareData.prefs.getString("DD_Model","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDefaltViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

    }


    //bike_select //(val string_array
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        spinner = binding.spinner

       adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,arraylist_spinner)

        mDefaltViewModel.spinnerData.observe(
            viewLifecycleOwner,
            Observer { Defalt_Data ->
                if (Defalt_Data.isEmpty()) {
         /*           val dialog = CustomDialog(requireContext())
                    dialog.showDia()*/
                    // 기본 데이터 추가 화면 실행
                } else {
                    adapter1 .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter1
                    arraylist_spinner.clear()
                    arraylist_spinner.addAll(Defalt_Data)
                    adapter1.notifyDataSetChanged()
                    Log.d("test" , " select  string: "+ lastselect.toString())
                    var match :Int = arraylist_spinner.indexOfFirst{it.equals(lastselect)}

                    Log.d("test" , " select index: "+ match.toString())
                    spinner.setSelection(match)



                }
            }
        )
        mDefaltViewModel.select_Gasinput_carname(lastselect).observe(
            viewLifecycleOwner,Observer
            {
                   it.let {
                        Log.d("test" , " select" + it.toString())
                       println(it.toString())
                    }
            }

        )

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d("test" , " select spinner : "+ arraylist_spinner[position].toString())
              ShareData.prefs.setString("DD_Model",arraylist_spinner[position])
                // 선택한 차량 모델의 주유정보 노출
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }


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

