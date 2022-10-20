package com.example.smarthomesystem

import android.annotation.TargetApi
import android.content.*
import android.net.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.smarthomesystem.databinding.ActivityDeviceConnectBinding
import com.example.smarthomesystem.databinding.ActivityDeviceConnectBinding.inflate

class DeviceConnectActivity : AppCompatActivity() {

    lateinit var deviceConnectBinding : ActivityDeviceConnectBinding
    var lastTimeBackPressed : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        deviceConnectBinding = ActivityDeviceConnectBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(deviceConnectBinding.root)

        val connection = NetworkConnection(application)

        deviceConnectBinding.connectButton.setOnClickListener {
            openConnectDialog()

//            val items = arrayOf("WiFi", "Bluetooth")
//            var selectedItem : String? = null
//            val builder = AlertDialog.Builder(this)
//                .setTitle("Select Item")
//                .setSingleChoiceItems(items, -1) { dialog, which ->
//                    selectedItem = items[which]
//                }
//                .setPositiveButton("OK") { dialog, which ->
//                    Toast.makeText(this,"'Selected",Toast.LENGTH_LONG).show()
//                }
//                .show()

        }

        connection.observe(this, Observer { isConnected ->
            if(isConnected) {
                // WiFI에 연결되어 있을 때
                Log.e("jehee", "jehee2")
            }
            else {
                // WiFI에 연결되어 있지 않을 때
            }
        })
    }

    // 뒤로 가기 버튼 클릭
    override fun onBackPressed() {
        if(System.currentTimeMillis() - lastTimeBackPressed >= 1500) {
            lastTimeBackPressed = System.currentTimeMillis()
            Toast.makeText(this,"'뒤로' 버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_LONG).show()
        }
        else {
            finish()
        }
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
                        0 -> moveWifiSettingScreen()
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
