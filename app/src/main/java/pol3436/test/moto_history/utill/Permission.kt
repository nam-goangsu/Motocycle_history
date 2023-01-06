package pol3436.test.moto_history.utill

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pol3436.test.moto_history.MainActivity
import pol3436.test.moto_history.R

class Permission: AppCompatActivity() {
    companion object {
        val permissionsList = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA
        )
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            MainActivity.REQUEST_CODE_PERMISSIONS -> {
                if (grantResults.all {it == PackageManager.PERMISSION_GRANTED}) {//전부 되는 경우

                } else {

                    if (grantResults.any { it == PackageManager.PERMISSION_DENIED }) {// 1개라도 TRUE인경우
                        lateinit var mText : Map<String, String>

                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                            mText = mapOf("상세 미허가" to stringPrint(R.string.msg_n_Notdetail),"위치 미허가" to stringPrint(
                                R.string.msg_n_Notgps),"상세 허가" to stringPrint(R.string.msg_n_detail) )
                        }else{
                            mText = mapOf("상세 미허가" to stringPrint(R.string.msg__Notdetail),"위치 미허가" to stringPrint(
                                R.string.msg__Notgps),"상세 허가" to stringPrint(R.string.msg__detail) )
                        }


                        if (grantResults.get(0) == PackageManager.PERMISSION_DENIED && grantResults.get(1) == PackageManager.PERMISSION_GRANTED) {
                            AppUtill(this, (mText.get("상세 미허가")).toString() , Gravity.CENTER_VERTICAL, 0, 0)
                        } else if (grantResults.get(0) == PackageManager.PERMISSION_DENIED && grantResults.get(1) == PackageManager.PERMISSION_DENIED) {
                            AppUtill(this, (mText.get("위치 미허가")).toString() , Gravity.CENTER_VERTICAL, 0, 0)
                        } else if (grantResults.get(0) == PackageManager.PERMISSION_GRANTED && grantResults.get(1) == PackageManager.PERMISSION_DENIED) {
                            AppUtill(this, (mText.get("상세 허가")).toString() , Gravity.CENTER_VERTICAL, 0, 0)
                        } else if (grantResults.get(2) == PackageManager.PERMISSION_DENIED) {
                            Toast.makeText(this, "카메라를 사용하지 않습니다.", Toast.LENGTH_SHORT).show()
                        }


                    } else {
                        Toast.makeText(this, "reject", Toast.LENGTH_SHORT).show()
                    }
//https://velog.io/@jaeyunn_15/Android-Android-%EC%9C%84%EC%B9%98-%EA%B6%8C%ED%95%9C-%EB%B3%80%EA%B2%BD-%EC%82%AC%ED%95%AD
//https://debbi.tistory.com/31

                }
            }
        }
    }


    fun stringPrint(mid:Int):String {
        var print_string =resources.getString(mid)
        return print_string.toString()
    }
}