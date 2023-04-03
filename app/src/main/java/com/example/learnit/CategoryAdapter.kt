package com.example.learnit

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CategoryAdapter: RecyclerView.Adapter<MyViewHolder>() {
    lateinit var categoryRow: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        categoryRow = layoutInflater.inflate(R.layout.category_row, parent, false)
        return MyViewHolder(categoryRow)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    @OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val title = holder.view.findViewById<TextView>(R.id.category_text_row)

        title.text = categoryList[position]

        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity

                var wordList: MutableList<DocumentSnapshot> = mutableListOf()

                val docRef = db.collection("users").document(auth.uid.toString()).collection(title.text.toString())

                docRef.get().addOnSuccessListener { document ->

                    println("OK!")
                    if (document != null) {
                        println(document.documents)
                        wordList = document.documents



                        Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.documents}")

                if( wordList.size > 4 ) {
                    val newFragment = WordsFragment( wordList )
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_view, newFragment)
                        .commit()
                }
                else{
                    Toast.makeText(activity.baseContext, "Not enough words! ${wordList.size} of at least 5 ", Toast.LENGTH_LONG).show()
                }
                    }
                }

            }

        })
    }
}

class MyViewHolder(val view: View): RecyclerView.ViewHolder(view)

