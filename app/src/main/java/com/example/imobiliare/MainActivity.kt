package com.example.imobiliare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imobiliare.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity(),ClickInterface{

    private lateinit var binding: ActivityMainBinding
    val referance = FirebaseDatabase.getInstance().getReference("anunturi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //se afiseaza imaginea de inceput cu casa
        Handler(Looper.getMainLooper()).postDelayed({
         binding.layout.imageView.visibility = View.GONE
         binding.layout.textView2.visibility = View.GONE
         binding.layout.textView3.visibility = View.GONE
          binding.frame.visibility = View.VISIBLE
            binding.recycler.visibility=View.VISIBLE
        }, 7000)

        binding.floating.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(AddAdFragment()::class.java.name)
                .add(R.id.content,AddAdFragment(),"fragment")
                .commit()
        }

        binding.recycler.layoutManager = LinearLayoutManager(this)

        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val data = ArrayList<Ad>()

                for ( childDataSnapshot in dataSnapshot.children) {

                    val add = Ad(title = childDataSnapshot.child("title").getValue().toString(),
                        description = childDataSnapshot.child("description").getValue().toString(),
                        price = childDataSnapshot.child("price").getValue().toString(),
                    id = childDataSnapshot.key)

                    data.add(add)

                }
                val adapter = RecyclerAd(data,this@MainActivity)

                binding.recycler.adapter = adapter

            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        referance.addValueEventListener(menuListener)

    }

    override fun clickAd(ad: Ad) {

        val intent = Intent(this, AddDetailsActivity::class.java)
        intent.putExtra("title", ad.title)
        intent.putExtra("description", ad.description)
        intent.putExtra("price", ad.price)
        intent.putExtra("id", ad.id)
        startActivity(intent)
    }
}