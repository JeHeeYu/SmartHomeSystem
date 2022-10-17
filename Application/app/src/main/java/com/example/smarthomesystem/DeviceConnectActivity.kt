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


        deviceConnectBinding.wifi.setOnClickListener {

        }

    }
}

// WiFI 연결 상태를 확인하기 위한 Class
class NetworkConnection(private val context: Context) : LiveData<Boolean>()
{
    private var connectivityManager : ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    override fun onActive()
    {
        super.onActive()
        updateConnection()

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallback())
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                lollipopNetworkRequest()
            }

            else -> {
                context.registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
            }
        }
    }

    override fun onInactive()
    {
        super.onInactive()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager.unregisterNetworkCallback(connectivityManagerCallback())
        }
        else {
            context.unregisterReceiver(networkReceiver)
        }
    }

    @TargetApi (Build.VERSION_CODES.LOLLIPOP)
    private fun lollipopNetworkRequest()
    {
        val requestBuilder = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        connectivityManager.registerNetworkCallback(requestBuilder.build(), connectivityManagerCallback())
    }

    private fun connectivityManagerCallback() : ConnectivityManager.NetworkCallback
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)
                    postValue(false)
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    postValue(true)
                }
            }

            return networkCallback
        }

        else {
            throw IllegalAccessError("Error")
        }
    }

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            updateConnection()
        }
    }

    private fun updateConnection()
    {
        val activityNetwork : NetworkInfo? = connectivityManager.activeNetworkInfo
        postValue(activityNetwork?.isConnected == true)
    }

}
