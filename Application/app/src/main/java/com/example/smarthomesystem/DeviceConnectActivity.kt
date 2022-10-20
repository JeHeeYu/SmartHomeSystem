package com.example.smarthomesystem

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.smarthomesystem.databinding.ActivityDeviceConnectBinding
import com.example.smarthomesystem.databinding.ActivityDeviceConnectBinding.inflate

class DeviceConnectActivity : AppCompatActivity() {

    lateinit var deviceConnectBinding : ActivityDeviceConnectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        deviceConnectBinding = ActivityDeviceConnectBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(deviceConnectBinding.root)

        val connection = NetworkConnection(application)

        connection.observe(this, Observer { isConnected ->
            if(isConnected) {
                // WiFI에 연결되어 있을 때
            }
            else {
                // WiFI에 연결되어 있지 않을 때
            }
        })
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

}
