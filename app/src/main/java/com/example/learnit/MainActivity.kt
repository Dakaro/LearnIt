package com.example.learnit

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.quiz_fragment.*
import kotlinx.android.synthetic.main.quiz_fragment.view.*
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

val db = Firebase.firestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportActionBar?.setIcon(R.drawable.learnit)
        supportActionBar?.setLogo(R.drawable.learnit)

        val bottomNav = findViewById<BottomNavigationView>(R.id.top_nav_menu)

        val categoryFragment = CategoryFragment()
        val addFragment = AddFragment()
        val accountFragment = AccountFragment()

        setFragment(categoryFragment)

        bottomNav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.words_nav -> setFragment(categoryFragment)
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