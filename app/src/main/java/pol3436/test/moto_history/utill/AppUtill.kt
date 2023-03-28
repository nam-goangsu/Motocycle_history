package pol3436.test.moto_history.utill


import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

import pol3436.test.moto_history.R


class AppUtill(context: Context? ) {
    operator fun invoke() {
        //TODO("Not yet implemented")
    }

    lateinit var mContext :Context
    lateinit var mmsg : String
    init{
        this.mContext = context!!
    }

    fun checkPermission(permissions: Array<String>): Boolean {
        return permissions.all { // 퍼미션 list 값을 비교함 {} 내부분이 조건 전부 매칭시 true
            ContextCompat.checkSelfPermission(mContext, it) == PackageManager.PERMISSION_GRANTED
            //퍼미션 체크 단계 it => permissions list
        }
    }


    constructor(context: Context?, msg: String?):this(context){
        this.mmsg = msg!!

        if (mmsg != null){
            val view: Activity = mContext as Activity
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                val toast = Toast.makeText(mContext, mmsg, Toast.LENGTH_SHORT)
                toast.show()
            } else {
                val mSnackbar =Snackbar.make(mContext!!,view.findViewById(R.id.drawer),  mmsg,Toast.LENGTH_SHORT)
                mSnackbar.show()
            }
        }
    }


    constructor(context: Context?, msg: String?, gravity: Int, xInt: Int, yInt: Int):this(context,msg){
        this.mmsg = msg!!
        if (mmsg != null){
                val view: Activity = mContext as Activity
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                    val toast = Toast.makeText(mContext, mmsg, Toast.LENGTH_SHORT)
                    toast.setGravity(gravity,xInt,yInt)
                    toast.show()
                } else {
                    val mSnackbar =Snackbar.make(mContext!!,view.findViewById(R.id.drawer),  mmsg,Toast.LENGTH_SHORT)
                    mSnackbar.show()
                }
            }
    }


}
