package com.example.hydoponicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.hydoponicapp.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var dbref : DatabaseReference
    private lateinit var binding:ActivityMainBinding



    private lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        button=findViewById(R.id.button)
        button.setOnClickListener(this)
        getUserData()

    }
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.button->{
                val intent= Intent(this,UpdateConditions::class.java)
                startActivity(intent)
                // do some work here
            }
        }
    }
    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("Plants")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val waterLevel=snapshot.child("WaterLevel").value
                    val temperature=snapshot.child("Temperature").value
                    val humidity=snapshot.child("Humidity").value
                    val lightSen=snapshot.child("LightSensitivity").value
                    val plantName=snapshot.child("PlantName").value
                    binding.mTemp.text=temperature.toString()
                    binding.mHumidity.text=humidity.toString()
                    binding.mLightSense.text=lightSen.toString()
                    binding.mWaterLevel.text=waterLevel.toString()
                    binding.mPlantName.text=plantName.toString()




                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity,"Failed",Toast.LENGTH_SHORT).show()

                TODO("Not yet implemented")
            }


        })

    }
}

