package com.example.itemtrade80

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.itemtrade80.chat.ChatFragment
import com.example.itemtrade80.home.HomeFragment
import com.example.itemtrade80.myinfo.MyInfoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val myInfoFragment = MyInfoFragment()
        val chatFragment = ChatFragment()

        replaceFragment(homeFragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.page_home -> replaceFragment(homeFragment)
                R.id.page_chat -> replaceFragment(chatFragment)
                R.id.page_myinfo -> replaceFragment(myInfoFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer, fragment)
                commit()
            }
    }
}