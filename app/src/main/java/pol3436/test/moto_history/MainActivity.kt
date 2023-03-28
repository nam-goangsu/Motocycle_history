package pol3436.test.moto_history


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import pol3436.test.moto_history.Repository.ShareData
import pol3436.test.moto_history.databinding.ActivityMainBinding
import pol3436.test.moto_history.utill.AppUtill
import pol3436.test.moto_history.utill.Permission

/*
* 작업 필요 내용
* 1. app 한번 실행 여부 // 쉐어 false
* 2. 퍼미션
* 3. db 생성 체크
*/

class MainActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE_PERMISSIONS = 1001
        const val REQUEST_CODE_PERMISSIONS_GPS = 1002
        var Default_Dataset : Boolean =  ShareData.prefs.getBoolean("DefaltData",false)
        lateinit var toggle: ActionBarDrawerToggle
    }


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (Default_Dataset == false) {
            //ShareData.prefs.setBoolean("DefaltData", false) // 이후 값이 넣어진 경우 true
            val setupIntent: Intent = Intent(this, SetupActivity::class.java)
            setupIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(setupIntent)

            /*
            val dialog = CustomDialog(this)
            dialog.showDia()*/
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(binding.toolbar)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                 R.id.navigation_dashboard,R.id.navigation_home, R.id.navigation_notifications
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onResume() {
        super.onResume()

    }
}