package com.example.imobiliare

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.imobiliare.databinding.ActivityAddDetailsBinding
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase

import com.google.firebase.database.DatabaseReference

class AddDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDetailsBinding


    val firebase = FirebaseDatabase.getInstance().reference.child("anunturi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView.text=getIntent().getExtras()?.getString("title")
        binding.textView2.text=getIntent().getExtras()?.getString("description")
        binding.textView3.text=getIntent().getExtras()?.getString("price")+"  €"
        binding.button.setOnClickListener {
         firebase .child(intent.extras?.getString("id") ?: "").ref.setValue(null)

            Toast.makeText(this,"Anunt sters", Toast.LENGTH_LONG).show()
            finish()
        }

    }

}