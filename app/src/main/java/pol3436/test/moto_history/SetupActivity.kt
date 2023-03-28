package pol3436.test.moto_history

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.defaltdata_input.*
import kotlinx.android.synthetic.main.defaltdata_input.view.*
import kotlinx.android.synthetic.main.dialog_new_moto_type.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pol3436.test.moto_history.Data.GasDatabase
import pol3436.test.moto_history.Model.DataClass.Defalt_Data
import pol3436.test.moto_history.Net.GetList
import pol3436.test.moto_history.Repository.GasRepository
import pol3436.test.moto_history.Repository.ShareData
import pol3436.test.moto_history.databinding.DialogNewMotoTypeBinding
import pol3436.test.moto_history.utill.AppUtill
import pol3436.test.moto_history.utill.LayoutUtill
import pol3436.test.moto_history.utill.LayoutUtill.getExternalFilesDir
import pol3436.test.moto_history.utill.Permission
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class SetupActivity : AppCompatActivity() {

    private var _binding: DialogNewMotoTypeBinding? = null
    private val binding get() = _binding!!
    private val context: Context = this
    lateinit var filePath: String

    companion object {
        lateinit var bigPictureBitmap: Bitmap
    }

    private lateinit var default_data: Defalt_Data

    private lateinit var textmodel: TextWatcher
    private lateinit var getstring: Array<String>

    private var get_time: Long = System.currentTimeMillis()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GetList(applicationContext)

        _binding = DialogNewMotoTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

 /*       if (!AppUtill(this).checkPermission(Permission.permissionsList)) {
            requestPermissions(Permission.permissionsList, MainActivity.REQUEST_CODE_PERMISSIONS)
        }*/
        //permissionsimage
        showDia()

    }

    fun showDia() {
        CustomDialog.bigPictureBitmap =
            BitmapFactory.decodeResource(context.resources, R.drawable.noimg) //리소스 -> 비트맵
        val manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        binding.apply {
            edittextinput()
            root.input_model!!.requestFocus()
            //manager.showSoftInput(root.input_model!!, InputMethodManager.SHOW_IMPLICIT)
            //manager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0)

            root.input_model!!.addTextChangedListener(textmodel)// edittext 입력 후 체크 사항
            root.input_maker!!.addTextChangedListener(textmodel)
            root.input_CC!!.addTextChangedListener(textmodel)
            root.input_odd!!.addTextChangedListener(textmodel)
            root.input_maxtankliter!!.addTextChangedListener(textmodel)
            root.input_maxtankkan!!.addTextChangedListener(textmodel)
            root.input_alttankliter!!.addTextChangedListener(textmodel)

            dialogCancel.setOnClickListener(buttonclickListener())
            dialogSelect.setOnClickListener(buttonclickListener())
            root.gps_check.setOnCheckedChangeListener(checkBoxListener())
            root.opnet_check.setOnCheckedChangeListener(checkBoxListener())

            root.get_picture2.setOnClickListener(buttonclickListener())
            root.get_picture.setOnClickListener(buttonclickListener())

            if (LayoutUtill.width > 1200) {
                Log.d("test", "get layout wed size : " + LayoutUtill.width)
                window!!.setLayout(
                    //WindowManager.LayoutParams.WRAP_CONTENT,
                    //(LayoutUtill.width/3)*2,
                    950,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
            } else {
                Log.d("test", "get layout wed size : " + LayoutUtill.width)
                window!!.setLayout(
                    //(LayoutUtill.width/5)*2,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
            }
        }

    }


    fun edittextinput(){
        textmodel = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable) {
//   if (grantResults.all {it == PackageManager.PERMISSION_GRANTED}) {
                Log.d("tes", "test time " + get_time)
                if (editable.length > 0) {
                    binding.root.apply {
                        getstring = arrayOf(
                            input_model!!.text.toString(),
                            input_maker!!.text.toString(),
                            input_CC!!.text.toString(),
                            input_odd!!.text.toString(),
                            input_maxtankliter!!.text.toString(),
                            input_maxtankkan!!.text.toString(),
                            input_alttankkan!!.text.toString(),
                            input_alttankliter!!.text.toString(),
                            gps_check!!.text.toString()
                        )
                    }
                    if (getstring.all {
                            getstring.toString().isNullOrBlank()
                        }) {
                        Log.d(ContentValues.TAG, "main : false")

                        binding.dialogSelect.setClickable(false)
                        binding.dialogSelect.setBackgroundColor(Color.GRAY)


                    } else {
                        binding.dialogCancel.setClickable(true)
                        binding.dialogSelect.setBackgroundColor(R.drawable.button_rightround)

                        Log.d(ContentValues.TAG, "main : TRUE")
                    }
                }
            }
        }


    }

    inner class checkBoxListener : CompoundButton.OnCheckedChangeListener{
        override fun onCheckedChanged(p: CompoundButton?, isChecked: Boolean) {
            when(p?.id) {
                R.id.gps_check -> {
                    if (!AppUtill(context).checkPermission(Permission.permissionsList)) {
                        requestPermissions(
                            Permission.permissionsList,
                            MainActivity.REQUEST_CODE_PERMISSIONS
                        )
                    }
                }

            }
        }

    }
     fun recallcamera(){
         Toast.makeText(context,"카메라 사용 승락 해주세요.",Toast.LENGTH_SHORT).show()
        if (!AppUtill(context).checkPermission(Permission.permissionsimage)) {
            requestPermissions(
                Permission.permissionsimage,
                MainActivity.REQUEST_CODE_PERMISSIONS
            )

        }else {
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()) // 파일명 설정
            val storageDir: File? =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)// 디렉토리 생성 여부 확인 후 생성
            val file = File.createTempFile( // 파일 생성
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath
            val photoURI: Uri = FileProvider.getUriForFile(
                context,
                "pol3436.test.moto_history.fileprovider", file
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            getCamera.launch(intent)
        }
    }
    inner class buttonclickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.get_picture -> { // 카메라
                recallcamera()
                }
                R.id.get_picture2 -> {
                    readImage.launch(takePhotoFromAlbumIntent)
                }

                R.id.dialog_cancel -> {
                    cancelbutton()
                }
                R.id.dialog_select -> {
                    saveDataButton()
                }
            }
        }

    }

    private val readImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    )
    {
        try { // FILE PATH 추가 되는 내용 없음
            // inSampleSize 비율 계산, 지정
            val calRatio = calculateInSampleSize(
                it!!.data!!.data!!,
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            ) // 리 샘플링 값 계산
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio

            // 이미지 로딩
            var inputStream = contentResolver.openInputStream(it!!.data!!.data!!)
            val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
            inputStream!!.close()
            inputStream = null


            CustomDialog.bigPictureBitmap = rotatedBitmap(it!!.data!!.data!!, bitmap!!)!!

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private val getCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        val calRatio = calculateInSampleSize( // 이미지 샘플링 처리
            Uri.fromFile(File(filePath)),
            resources.getDimensionPixelSize(R.dimen.imgSize),
            resources.getDimensionPixelSize(R.dimen.imgSize)
        )
        val option = BitmapFactory.Options()
        option.inSampleSize = calRatio
        val bitmap = BitmapFactory.decodeFile(filePath, option) // 파일경로 매개변수로 데이터를 읽는 stream 을 이용
        val bitmap1: Bitmap = rotatedBitmap(bitmap!!, filePath)!!
        CustomDialog.bigPictureBitmap = bitmap1
    }


    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true // 옵션 설정 true bitmap 미제작 이미지의 각종정보가 저장됨

        try {
            var inputStream = contentResolver.openInputStream(fileUri)
            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options) // 스트림-> 비트맵 생성
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산 // 이미지 샘플 사이즈 값을 줄임으로 메모리 이슈 해결
        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    private val takePhotoFromAlbumIntent =
        Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
            putExtra(
                Intent.EXTRA_MIME_TYPES,
                arrayOf("image/jpeg", "image/png", "image/bmp", "image/webp")
            )
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)

        }

/*    @Suppress("DEPRECATION", "NewApi")
    private fun Uri.parseBitmap(context: Context): Bitmap {
        return when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) { // 28
            true -> {
                val source = ImageDecoder.createSource(context.contentResolver, this)
                ImageDecoder.decodeBitmap(source)
            }
            else -> {
                MediaStore.Images.Media.getBitmap(context.contentResolver, this)
            }
        }
    }*/


    fun cancelbutton() {
        dialog_cancel!!.setOnClickListener {
            //toast.setGravity(Gravity.CENTER, 0, 0)
            // toast.show()
            ShareData.prefs.setBoolean("DefaltData", false)
            Thread.sleep(1000L)
            //dialog.dismiss()
            System.exit(0)
        }
    }

    fun saveDataButton() {
        val repository: GasRepository
        val gasDao = GasDatabase.getDatabase(context).gasDao()
        repository = GasRepository(gasDao)
        dialog_select!!.setOnClickListener {
            default_data = Defalt_Data(
                0,
                input_model!!.text.toString(),
                input_maker!!.text.toString(),
                input_CC!!.text.toString().toInt(),
                bike_type!!.selectedItem.toString(),
                input_odd!!.text.toString().toLong(),
                input_maxtankliter!!.text.toString().toInt(),
                input_maxtankkan!!.text.toString().toInt(),
                input_alttankkan!!.text.toString().toInt(),
                input_alttankliter!!.text.toString().toInt(),
                input_avgtype!!.selectedItem.toString(),
                gps_check!!.isChecked,
                opnet_check!!.isChecked,
                CustomDialog.bigPictureBitmap!!,
                get_time
            )
            CoroutineScope(Dispatchers.IO).launch {
                repository.insert_defalt_data(default_data)
                /*db.gasDao().add_DefaltData( //23-01-03 06:05 db 연동 잡아야지
                    default_data
                )*/
            }
            ShareData.prefs.setString("DD_Model", input_model!!.text.toString())
            ShareData.prefs.setBoolean("DefaltData", true)

            finish()
            // dialog.dismiss()

        }
    }


    fun getOrientationOfImage(filepath: String): Int? { // 로테이션 이슈 (여기선 string gksl file path를 스트링으로 읽음
        var exif: ExifInterface? = null
        var result: Int? = null
        try {
            exif = ExifInterface(filepath)
        } catch (e: Exception) {
            e.printStackTrace()
            return -1
        }
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)
        print(orientation)
        if (orientation != -1) {
            result = when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> 90
                ExifInterface.ORIENTATION_ROTATE_180 -> 180
                ExifInterface.ORIENTATION_ROTATE_270 -> 270
                ExifInterface.ORIENTATION_FLIP_VERTICAL -> 10
                ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> 20
                ExifInterface.ORIENTATION_NORMAL -> 30
                ExifInterface.ORIENTATION_TRANSPOSE -> 40
                ExifInterface.ORIENTATION_TRANSVERSE -> 50
                else -> 0
            }
        }
        return result
    }

    //카메라
    private fun rotatedBitmap(bitmap: Bitmap?, filepath: String): Bitmap? {
        val matrix = Matrix()
        var resultBitmap: Bitmap? = null
        when (getOrientationOfImage(filepath)) {
            0 -> matrix.setRotate(0F)
            90 -> matrix.setRotate(90F)
            180 -> matrix.setRotate(180F)
            270 -> matrix.setRotate(-90F)
            10 -> {
                matrix.setRotate(180F)
                matrix.postScale(-1F, 1F)
            }
            20 -> matrix.postScale(-1F, 1F)
            30 -> matrix.setRotate(0F)
            40 -> {
                matrix.setRotate(90F)
                matrix.postScale(-1F, 1F)
            }
            50 -> {
                matrix.setRotate(-90F)
                matrix.postScale(-1F, 1F)
            }

        }
        resultBitmap = try {
            bitmap?.let {
                Bitmap.createBitmap(
                    it!!,
                    0,
                    0,
                    bitmap.width,
                    bitmap.height,
                    matrix,
                    true
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        return resultBitmap
    }

    //앨범
    private fun rotatedBitmap(uri: Uri, bitmap: Bitmap?): Bitmap? {
        var inputStream = contentResolver.openInputStream(uri)
        var exif =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ExifInterface(inputStream!!)
            } else {
                TODO("VERSION.SDK_INT < N")
            }
        inputStream.close()

        var orientation =
            exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        Log.d("image orient", orientation.toString())
        val matrix = Matrix()

        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
            matrix.setRotate(90F)
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
            matrix.setRotate(180F)
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
            matrix.setRotate(270F)
        } else if (orientation == ExifInterface.ORIENTATION_FLIP_VERTICAL) {
            matrix.setRotate(180F)
            matrix.postScale(-1F, 1F)
        } else if (orientation == ExifInterface.ORIENTATION_FLIP_HORIZONTAL) {
            matrix.postScale(-1F, 1F)

        } else if (orientation == ExifInterface.ORIENTATION_TRANSPOSE) {
            matrix.setRotate(90F)
            matrix.postScale(-1F, 1F)
        } else if (orientation == ExifInterface.ORIENTATION_TRANSVERSE) {
            matrix.setRotate(-90F)
            matrix.postScale(-1F, 1F)
        } else if (orientation == ExifInterface.ORIENTATION_NORMAL) {
            matrix.setRotate(0F)
        }

        return Bitmap.createBitmap(bitmap!!, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}