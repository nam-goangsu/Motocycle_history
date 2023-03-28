package pol3436.test.moto_history.ui.home


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import kotlinx.android.synthetic.main.activity_main_avgdata.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pol3436.test.moto_history.Model.DataClass.*
import pol3436.test.moto_history.R
import pol3436.test.moto_history.Repository.ShareData
import pol3436.test.moto_history.databinding.FragmentHomeBinding
import java.util.*
import pol3436.test.moto_history.utill.Utills as times


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    // val adapter = Main_ChangeItem_Adapter()
    private lateinit var mDefaltViewModel: HomeViewModel

    private var match: Int = 0
    private lateinit var spinner: Spinner
    private var arraylist_spinner = arrayListOf<String>()

    private var defaltData = arrayListOf<Defalt_Data>()

    private lateinit var adapter1: ArrayAdapter<String>
    private var lastselect: String = ShareData.prefs.getString("DD_Model", "")

    private var get_time: Long = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDefaltViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

    }


    //bike_select //(val string_array
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        spinner = binding.spinner

        adapter1 = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            arraylist_spinner
        )
        val paint: Paint? = Paint(Paint.ANTI_ALIAS_FLAG)
        binding.run {
            a.textView1.setTextColor(R.color.black)
            Log.d("test", "time : " + get_time)
            a.textView2.text = times.retrun_time().allday(get_time).toString()
            a.HeadText.text = "최근 주유 연비"
            b.HeadText.text = "이번달 평균 연비"

        }

        binding.button.setOnClickListener(ButtonclickListener())



        recyclerView = binding!!.recyclerview
        //     recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())



/*        defaltData.clear()
        arraylist_spinner.clear()
        lifecycleScope.launchWhenStarted {

        }
        */
        mDefaltViewModel.spinnerData1.observe(viewLifecycleOwner,
            Observer { Defalt_Data ->
                defaltData.clear()
                arraylist_spinner.clear()
                if (Defalt_Data.isNotEmpty()) {

                    defaltData.addAll(Defalt_Data)
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter1
                    //Log.d("test", ""+  Defalt_Data.groupBy { it.Car_Name })
                    val list: List<String> = Defalt_Data.map { it.Car_Name }
                    //Log.d("test", "list : " + list)
                    arraylist_spinner.addAll(list)
                    //Log.d("test", " arraylist : "+ arraylist_spinner)
                    adapter1.notifyDataSetChanged()
                    //Log.d("test", " select  string: " + lastselect.toString())
                    match = arraylist_spinner.indexOfFirst { it.equals(lastselect) }
                    //Log.d("test", " select index: " + match.toString())
                    spinner.setSelection(match)
                    //Log.d("test", " select index img: " + defaltData.get(match).Image)
                    Glide.with(this@HomeFragment).load(Defalt_Data.get(match).Image)
                        .into(binding.imageView2)
                }
            })

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //Log.d("test", " select spinner : " + arraylist_spinner[position].toString())
                ShareData.prefs.setString("DD_Model", arraylist_spinner[position])
                // 선택한 차량 모델의 주유정보 노출

                //Log.d("test", " select index img: " + defaltData.get(position).Image)


                val requestOptions = RequestOptions()
                requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE)
                requestOptions.skipMemoryCache(false)
                requestOptions.centerCrop()
                requestOptions.circleCrop()
                requestOptions.signature(ObjectKey(System.currentTimeMillis()))

                Glide.with(this@HomeFragment).load(defaltData.get(position).Image)
                    .apply { requestOptions }
                    .into(binding.imageView2)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        return binding!!.root


    }


    val callIntent: Intent = Uri.parse("tel:01020220293").let { number ->
        Intent(Intent.ACTION_DIAL, number)
    }
    val mapIntent: Intent = Uri.parse(
        "geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California"
        // google.maps에서 적용
    ).let { location ->
        // Or map point based on latitude/longitude
        // val location: Uri = Uri.parse("geo:37.422219,-122.08364?z=14") // z param is zoom level
        Intent(Intent.ACTION_VIEW, location)
    }


    val webIntent: Intent = Uri.parse("https://www.naver.com").let { webpage ->
        Intent(Intent.ACTION_VIEW, webpage)
    }

    val intent = Uri.parse("geo:37.7749,127.4194").let { location ->
        Intent(Intent.ACTION_VIEW, location)
    }

    inner class ButtonclickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.button -> {
                    Log.d("test", "click")
                    startActivity(intent)
                    // startActivity( mapIntent)
                }

            }
        }

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

