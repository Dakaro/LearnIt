package com.example.learnit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val bottomNav = findViewById<BottomNavigationView>(R.id.top_nav_menu)

        val wordsFragment = WordsFragment()
        val addFragment = AddFragment()
        val accountFragment = AccountFragment()

        setFragment(wordsFragment)

        bottomNav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.words_nav -> setFragment(wordsFragment)
                R.id.add_nav -> setFragment(addFragment)
                R.id.account_nav -> setFragment(accountFragment)
            }
            true
        }

    }

    fun setFragment( fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_view , fragment)
            .commit()
    }
}