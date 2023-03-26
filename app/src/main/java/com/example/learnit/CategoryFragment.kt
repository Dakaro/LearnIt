package com.example.learnit

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.toObject
import java.security.KeyStore.Entry

var categoryList: MutableSet< String >? = mutableSetOf("")
class CategoryFragment(): Fragment(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.category_fragment, container, false)

        val docRef = db.collection("users").document(auth.uid.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    println(document.data)

                    categoryList = document.data!!.keys

                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

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