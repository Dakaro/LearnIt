package com.example.learnit

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.SetOptions

class AddFragment(): Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.add_fragment, container, false)

        val wordInputText = view.findViewById<EditText>(R.id.text_input_word)
        val translationInputText = view.findViewById<EditText>(R.id.text_input_translation)
        val categoryInputText = view.findViewById<Spinner>(R.id.category_selector)
        val descInputText = view.findViewById<EditText>(R.id.text_input_desc)

        val newCategoryInputText = view.findViewById<EditText>(R.id.text_input_new_category)

        //SPINNER
        val dataAdapter =  ArrayAdapter( requireContext(), android.R.layout.simple_spinner_item, categoryList)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryInputText.setAdapter(dataAdapter)

        val button = view.findViewById<Button>(R.id.imageButton)
        val newCategoryButton = view.findViewById<Button>(R.id.add_new_category)

        var selectedCategory = categoryInputText.onItemClickListener

        newCategoryButton.setOnClickListener {
            val newCategoryText = newCategoryInputText.text.toString()

            if( newCategoryText.equals("") ){
                Toast.makeText(context, "Please fill the necessary fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val id = db.collection("users").document(auth.uid.toString()).collection(newCategoryText)
            Toast.makeText(context, "Added new category!", Toast.LENGTH_SHORT).show()

            val value = hashMapOf( newCategoryText to id.path)
            db.collection("users").document(auth.uid.toString()).set(value, SetOptions.merge())

            newCategoryInputText.setText("")

        }

        button.setOnClickListener {

            val wordText = wordInputText.text.toString()
            val translationText = translationInputText.text.toString()
            val categoryText = selectedCategory.toString()
            val descText = descInputText.text.toString()

            if (wordText.equals("") || translationText.equals("") || categoryText.equals("")) {
                Toast.makeText(context, "PLEASE FILL THE NECESSARY FIELDS", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }


            val newWord = WordModel(
                wordText,
                translationText,
                categoryText,
                descText,
                0f
            )

            db.collection("users").document(auth.uid.toString()).collection(categoryText)
                .add(newWord)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(context, "The word has been added", Toast.LENGTH_SHORT).show()
                    wordInputText.setText("")
                    translationInputText.setText("")
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference}")
                }

                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}