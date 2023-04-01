package com.example.hydoponicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var fRecyclerview : RecyclerView
    private lateinit var fArrayList : ArrayList<Plants>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fRecyclerview = findViewById(R.id.plantList)
        fRecyclerview.layoutManager = LinearLayoutManager(this)
        fRecyclerview.setHasFixedSize(true)

        arrayListOf<Plants>().also { fArrayList = it }
        getUserData()
    }
    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().reference

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (locSnapshot in snapshot.children){


                        val loc = locSnapshot.getValue(Plants::class.java)
                        val t=fArrayList.binarySearchBy(loc?.PlantName) { it.PlantName }
                        if(t!=-1) {
                            fArrayList.removeAt(t)
                        }
                        fArrayList.add(loc!!)


                    }

                    fRecyclerview.adapter = MyAdapter(fArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}