package com.example.learnit

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.learnit.databinding.AddFragmentBinding


class AddFragment(): Fragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.add_fragment, container, false)

     //   val binding = AddFragmentBinding.inflate(inflater)

        val wordInputText = view.findViewById<EditText>(R.id.text_input_word)
        val translationInputText = view.findViewById<EditText>(R.id.text_input_translation)
        val categoryInputText = view.findViewById<EditText>(R.id.text_input_category)
        val descInputText = view.findViewById<EditText>(R.id.text_input_desc)


        val button = view.findViewById<ImageButton>(R.id.imageButton)

        button.setOnClickListener {

            val wordText = wordInputText.text.toString()
            val translationText = translationInputText.text.toString()
            val categoryText = categoryInputText.text.toString()
            val descText = descInputText.text.toString()

            if (wordText.equals("") || translationText.equals("") || categoryText.equals("")) {
                Toast.makeText(context, "PLEASE FILL THE NECCESSARY FIELDS", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }


            println(
                "TEXT: " + wordText + " " +
                        translationText + " " +
                        categoryText + " " +
                        descText
            )

            val newWord = WordModel(
                wordText,
                translationText,
                categoryText,
                descText
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