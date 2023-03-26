package com.example.learnit

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.android.synthetic.main.quiz_fragment.*
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import logic.checkResult
import logic.correctAns
import logic.randWords
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

var wordsList: MutableList< DocumentSnapshot > = mutableListOf()

class WordsFragment(var myCategory: String): Fragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.quiz_fragment, container, false)

        var title = view.findViewById<TextView>(R.id.main_word)
        var button1 = view.findViewById<Button>(R.id.button1)
        var button2 = view.findViewById<Button>(R.id.button2)
        var button3 = view.findViewById<Button>(R.id.button3)
        var button4 = view.findViewById<Button>(R.id.button4)


        fun setText() {
            var tempList = randWords(wordsList)
            title.text = tempList[correctAns].get("translation").toString()
            button1.text = tempList[0].get("word").toString()
            button2.text = tempList[1].get("word").toString()
            button3.text = tempList[2].get("word").toString()
            button4.text = tempList[3].get("word").toString()
        }

        val docRef = db.collection("users").document(auth.uid.toString()).collection( myCategory.toString() )

        docRef.get().addOnSuccessListener { document ->

            if (document != null) {
                println(document.documents)
                wordsList = document.documents
                Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.documents}")
            } else {
                Log.d(ContentValues.TAG, "No such document")
            }

            setText()
        }

        button1.setOnClickListener {
            if ( checkResult(0) ) {
                setText()
            }
        }

        button2.setOnClickListener {
            if ( checkResult(1) ) {
                setText()
            }
        }
        button3.setOnClickListener {
            if ( checkResult(2) ) {
                setText()
            }
        }
        button4.setOnClickListener {
            if ( checkResult(3) ) {
                setText()
            }
        }

        return view
    }
    override fun onDestroy() {
        super.onDestroy()
    }

    fun setWords(): ArrayList< DocumentSnapshot > {

        var result = arrayListOf<DocumentSnapshot>()

        var myList = arrayListOf<Int>()

        Toast.makeText(requireContext(), "tablica: " + myList.size.toString(), Toast.LENGTH_SHORT).show()

        myList.forEach {
            result.add( wordsList[it] )
        }

        println(myList)

        return result
    }

}