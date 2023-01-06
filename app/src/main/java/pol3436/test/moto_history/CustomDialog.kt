package pol3436.test.moto_history

import android.app.Dialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pol3436.test.moto_history.Data.GasDatabase
import pol3436.test.moto_history.Model.DataClass.Defalt_Data
import pol3436.test.moto_history.Repository.ShareData
import pol3436.test.moto_history.utill.LayoutUtill


//https://angangmoddi.tistory.com/261
//https://onedaycodeing.tistory.com/155
class CustomDialog(context: Context) {
    private val dialog = Dialog(context)
    private val context: Context = context

    private var db = GasDatabase.getDatabase(context)!!
    private val toast = Toast.makeText(
        context!!,
        "기본 정보를 미 입력 하셨기에," + "\n" + "앱을 종료합니다..",
        Toast.LENGTH_SHORT
    )
    private val toast1 = Toast.makeText(context!!, "사용자 정보를 꼭 입력 해주세요.", Toast.LENGTH_LONG)
    private lateinit var default_data: Defalt_Data
    private lateinit var model_text: EditText
    private lateinit var maker_text: EditText
    private lateinit var cc_text: EditText
    private lateinit var odd_text: EditText
    private lateinit var maxliter_text: EditText
    private lateinit var maxtank_text: EditText
    private lateinit var alttankkan: EditText
    private lateinit var alttankliter: EditText

    private lateinit var bike_type: Spinner
    private lateinit var avgtype: Spinner

    private lateinit var gps_check: CheckBox
    private lateinit var opnet_check: CheckBox


    private lateinit var get_picture: Button
    private lateinit var cancel_btn: Button
    private lateinit var save_btn: Button
    private lateinit var textmodel: TextWatcher
    private lateinit var getstring: Array<String>
    private var bigPictureBitmap : Bitmap? = BitmapFactory.decodeResource(context.resources, R.drawable.noimg)
/*
    private val model_text: EditText by lazy { dialog.findViewById<EditText>(R.id.input_model) }
    private val maker_text: EditText by lazy { dialog.findViewById<EditText>(R.id.input_maker) }
    private val cc_text: EditText by lazy { dialog.findViewById<EditText>(R.id.input_CC) }
    private val odd_text: EditText by lazy { dialog.findViewById<EditText>(R.id.input_odd) }
    private val maxliter_text: EditText by lazy { dialog.findViewById<EditText>(R.id.input_maxtankliter) }
    private val maxtank_text: EditText by lazy { dialog.findViewById<EditText>(R.id.input_maxtankkan) }
    private val alttankkan: EditText by lazy { dialog.findViewById<EditText>(R.id.input_alttankkan) }
    private val alttankliter: EditText by lazy { dialog.findViewById<EditText>(R.id.input_alttankliter) }

    private val bike_type: Spinner by lazy { dialog.findViewById<Spinner>(R.id.bike_type) }
    private val avgtype: Spinner by lazy { dialog.findViewById<Spinner>(R.id.input_avgtype) }

    private val gps_check: CheckBox by lazy { dialog.findViewById<CheckBox>(R.id.gps_check) }
    private val opnet_check: CheckBox by lazy { dialog.findViewById<CheckBox>(R.id.opnet_check) }


    private val get_picture: Button by lazy { dialog.findViewById<Button>(R.id.get_picture) }
    private val cancel_btn: Button by lazy { dialog.findViewById<Button>(R.id.dialog_cancel) }
    private val save_btn: Button by lazy { dialog.findViewById<Button>(R.id.dialog_select) }*/

    fun showDia() {
        dialog.setContentView(R.layout.dialog_new_moto_type)

        dialog.apply {
            model_text = findViewById<EditText>(R.id.input_model)
            maker_text = findViewById<EditText>(R.id.input_maker)
            cc_text = findViewById<EditText>(R.id.input_CC)
            odd_text = findViewById<EditText>(R.id.input_odd)
            maxliter_text = findViewById<EditText>(R.id.input_maxtankliter)
            maxtank_text = findViewById<EditText>(R.id.input_maxtankkan)
            alttankkan = findViewById<EditText>(R.id.input_alttankkan)
            alttankliter = findViewById<EditText>(R.id.input_alttankliter)

            bike_type = findViewById<Spinner>(R.id.bike_type)
            avgtype = findViewById<Spinner>(R.id.input_avgtype)

            gps_check = findViewById<CheckBox>(R.id.gps_check)
            opnet_check = findViewById<CheckBox>(R.id.opnet_check)

            get_picture = findViewById<Button>(R.id.get_picture)
            cancel_btn = findViewById<Button>(R.id.dialog_cancel)
            save_btn = findViewById<Button>(R.id.dialog_select)


            if (LayoutUtill.width > 1200) {
                Log.d("test","get layout wed size : "+ LayoutUtill.width)
                window!!.setLayout(
                    //WindowManager.LayoutParams.WRAP_CONTENT,
                    //(LayoutUtill.width/3)*2,
                    950,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
            }
            /*else if (LayoutUtill.width > 900) {
            dialog.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        }*/
            else {
                Log.d("test","get layout wed size : "+ LayoutUtill.width)
                window!!.setLayout(
                    //(LayoutUtill.width/5)*2,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
            }
        }

        edittextinput()
        model_text!!.addTextChangedListener(textmodel)// edittext 입력 후 체크 사항
        maker_text!!.addTextChangedListener(textmodel)
        cc_text!!.addTextChangedListener(textmodel)
        odd_text!!.addTextChangedListener(textmodel)
        maxliter_text!!.addTextChangedListener(textmodel)
        maxtank_text!!.addTextChangedListener(textmodel)
        alttankkan!!.addTextChangedListener(textmodel)

       // dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
//        dialog.setCanceledOnTouchOutside(true)
        //dialog.setCancelable(true) // cancel 기능
        cancelbutton()
        saveDataButton()


        dialog.show()

    }


    fun edittextinput() {

        textmodel = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable) {
//   if (grantResults.all {it == PackageManager.PERMISSION_GRANTED}) {
                if (editable.length > 0) {
                    getstring = arrayOf(
                        model_text!!.text.toString(),maker_text!!.text.toString(),cc_text!!.text.toString(),
                        odd_text!!.text.toString(),maxliter_text!!.text.toString(),maxtank_text!!.text.toString(),
                        alttankkan!!.text.toString(),alttankliter!!.text.toString(),gps_check!!.text.toString()
                    )
                    if (getstring.all {
                            getstring.toString().isNullOrBlank()
                    })
                    /*   if (model_text!!.text.toStrin().isNullOrBlank() and maker_text!!.text.toString()
                           .isNullOrBlank() and cc_text!!.text.toString().isNullOrBlank() and
                       odd_text!!.text.toString().isNullOrBlank() and maxliter_text!!.text.toString()
                           .isNullOrBlank() and maxtank_text!!.text.toString()
                           .isNullOrBlank() and tankkan!!.text.toString().isNullOrBlank() and
                       alttankliter!!.text.toString().isNullOrBlank() and gps_check!!.text.toString()
                           .isNullOrBlank()
                   )*/

                    /*      if (!model_text!!.text.toString().isNullOrBlank()
                              and !maker_text!!.text.toString().isNullOrBlank()
                              and !cc_text!!.text.toString().isNullOrBlank()
                              and !odd_text!!.text.toString().isNullOrBlank()
                              and !maxliter_text!!.text.toString().isNullOrBlank()
                              and !maxtank_text!!.text.toString().isNullOrBlank()
                              and !tankkan!!.text.toString().isNullOrBlank()
                          )*/ {
                        Log.d(ContentValues.TAG, "main : false")
                        dialog.findViewById<Button>(R.id.dialog_select!!).setClickable(false)
                        dialog.findViewById<Button>(R.id.dialog_select!!)
                            .setBackgroundColor(Color.GRAY)

                    } else {

                        Log.d(ContentValues.TAG, "main : TRUE")
                        dialog.findViewById<Button>(R.id.dialog_select!!).setClickable(true)
                        dialog.findViewById<Button>(R.id.dialog_select!!)
                            .setBackgroundColor(R.drawable.button_rightround)
                    }
                }
            }
        }
    }
    fun cancelbutton() {
        cancel_btn!!.setOnClickListener {
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
            ShareData.prefs.setBoolean("DefaltData", false) // 이후 값이 넣어진 경우 true
            Thread.sleep(1000L)
            dialog.dismiss()

            System.exit(0)

        }
    }

    fun saveDataButton() {

        save_btn!!.setOnClickListener {
            default_data = Defalt_Data(
                0,
                model_text!!.text.toString(),
                maker_text!!.text.toString(),
                cc_text!!.text.toString().toInt(),
                bike_type!!.selectedItem.toString(),
                odd_text!!.text.toString().toLong(),
                maxliter_text!!.text.toString().toInt(),
                maxtank_text!!.text.toString().toInt(),
                alttankkan!!.text.toString().toInt(),
                alttankliter!!.text.toString().toInt(),
                avgtype!!.selectedItem.toString(),
                gps_check!!.isChecked,
                opnet_check!!.isChecked,
                bigPictureBitmap!!
            )
            CoroutineScope(Dispatchers.IO).launch {
                db.gasDao().add_DefaltData( //23-01-03 06:05 db 연동 잡아야지
                    default_data
                )
            }

            ShareData.prefs.setString("DD_Model",model_text!!.text.toString() )

            ShareData.prefs.setBoolean("DefaltData", true)
            dialog.dismiss()
        }
    }
}
