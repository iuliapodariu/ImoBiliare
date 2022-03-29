package com.example.imobiliare

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.example.imobiliare.databinding.FragmentAddAdBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseReference
import androidx.core.content.PackageManagerCompat.LOG_TAG

import androidx.annotation.NonNull
import androidx.core.content.PackageManagerCompat

import com.google.android.gms.tasks.OnFailureListener

class AddAdFragment : Fragment(R.layout.fragment_add_ad) {

    private lateinit var binding: FragmentAddAdBinding

    val dataBase = FirebaseDatabase.getInstance()
    val ref = dataBase.reference.child("anunturi")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAddAdBinding.bind(view)

        binding.send.setOnClickListener {
            val sendObject = Ad(
                title = binding.title.text.toString(),
                description = binding.description.text.toString(),
                price = binding.price.text.toString()
            )
            ref.push().setValue(sendObject).addOnSuccessListener {
                Toast.makeText(activity!!,"Anunt adaugat cu succes", LENGTH_LONG).show()
                activity?.onBackPressed()
            }.addOnCanceledListener {
                Toast.makeText(activity!!,"A aparut o eroare incearca din nou", LENGTH_LONG).show()
            }
        }
    }
}