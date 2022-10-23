package com.example.smarthomesystem

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.smarthomesystem.databinding.ActivityDeviceConnectBinding

class DeviceConnectActivity : AppCompatActivity() {

    lateinit var deviceConnectBinding : ActivityDeviceConnectBinding
    var lastTimeBackPressed : Long = 0

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    var networkConnectionStatus : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        deviceConnectBinding = ActivityDeviceConnectBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(deviceConnectBinding.root)

        val connection = NetworkConnection(application)

        deviceConnectBinding.connectButton.setOnClickListener {
            //openConnectDialog()

//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
        }

        connection.observe(this, Observer { isConnected ->
            // WiFI에 연결되어 있을 때
            when(isConnected) {
                true -> networkConnectionStatus = true
                false -> networkConnectionStatus = false
            }
        })
    }

    // 뒤로 가기 버튼 클릭
    override fun onBackPressed() {
        if(System.currentTimeMillis() - lastTimeBackPressed >= 1500) {
            lastTimeBackPressed = System.currentTimeMillis()
            Toast.makeText(this,"'뒤로' 버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_LONG).show()
            //startActivity(Intent(this, MainActivity::class.java))
        }
        else {
            finish()
        }
    }


    fun getNetworkName(context: Context): String? {
        val manager = context.applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        val info = manager.connectionInfo
        return info.ssid
    }


    fun moveWifiSettingScreen()
    {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS )
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        overridePendingTransition(0, 0)

    }

    fun moveBluetoothSettingScreen()
    {
        val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS  )
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    fun openConnectDialog()
    {
        val items = arrayOf("WiFi", "Bluetooh")
        AlertDialog.Builder(this).run {
            setTitle("items test")
            setIcon(android.R.drawable.ic_dialog_info)
            setSingleChoiceItems(items, -1, object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {
                        0 -> {
                            if(networkConnectionStatus == true) {
                                moveWifiSettingScreen()
                            }
                            else {

                            }

                        }

                        1 -> moveBluetoothSettingScreen()
                        else -> finish()
                    }
                }
            })
            setPositiveButton("선택", null)
            show()
        }
    }
}
