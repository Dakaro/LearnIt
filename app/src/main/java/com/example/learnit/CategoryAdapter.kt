package com.example.learnit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter: RecyclerView.Adapter<MyViewHolder>() {
    lateinit var categoryRow: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        categoryRow = layoutInflater.inflate(R.layout.category_row, parent, false)
        return MyViewHolder(categoryRow)
    }

    override fun getItemCount(): Int {
        println(" wielkosc listy:  " + categoryList.size)
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val title = holder.view.findViewById<TextView>(R.id.category_text_row)

        title.text = categoryList[position]

        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity
                 var newFragment = WordsFragment(title.text.toString())
                activity.supportFragmentManager.beginTransaction().replace(R.id.main_fragment_view, newFragment)
                    .commit()
            }
        })
    }
}

class MyViewHolder(val view: View): RecyclerView.ViewHolder(view)