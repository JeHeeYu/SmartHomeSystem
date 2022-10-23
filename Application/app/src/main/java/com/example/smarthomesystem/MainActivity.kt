package com.example.smarthomesystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smarthomesystem.databinding.ActivityDeviceConnectBinding
import com.example.smarthomesystem.databinding.ActivityLoginBinding
import com.example.smarthomesystem.databinding.ActivityMainBinding
import com.example.smarthomesystem.navigation.HomeFragment
import com.example.smarthomesystem.navigation.LightFragment

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        mainBinding.bottomNavigation.setOnItemSelectedListener { it ->
            when (it.itemId) {
                R.id.navigation_home -> {
                    var homeViewFramgent = HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.main_content, homeViewFramgent).commit()
                    true
                }
                R.id.navigation_light -> {
                    var lightViewFragment = LightFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.main_content, lightViewFragment).commit()
                    true
                }
                else -> false

            }
        }

    }
}