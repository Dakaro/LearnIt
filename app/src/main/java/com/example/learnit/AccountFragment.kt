package com.example.learnit

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AccountFragment(): Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.account_fragment, container, false)
        val signOutButton = view.findViewById<Button>(R.id.sign_out_button)
        val accountNameText = view.findViewById<TextView>(R.id.account_name)

        var account = auth

        if(  account.currentUser != null )
            accountNameText.text = account.currentUser!!.displayName

        signOutButton.setOnClickListener {
            account.signOut()

            requireActivity().finish()
            var intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}