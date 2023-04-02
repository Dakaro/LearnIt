package com.example.learnit

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.toObject
import java.security.KeyStore.Entry

var categoryList: ArrayList< String > = arrayListOf<String>()
class CategoryFragment(): Fragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadCategoryList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.category_fragment, container, false)

        var recyclerView = view.findViewById<RecyclerView>(R.id.categoryList_recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)
        val myAdapter = CategoryAdapter()
        recyclerView.adapter = myAdapter

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}

fun loadCategoryList(){
    val docRef = db.collection("users").document(auth.uid.toString())
    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                println(document.data)

                var templist = arrayListOf<String>()

                document.data!!.keys.forEach {
                    templist.add(it.toString())
                }

                categoryList = templist

                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
            } else {
                Log.d(TAG, "No such document")
            }
        }
        .addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
}
