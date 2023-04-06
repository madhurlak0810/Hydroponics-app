package com.example.hydoponicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hydoponicapp.databinding.ActivityConditionsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateConditions : AppCompatActivity() {
    private lateinit var binding: ActivityConditionsBinding
    private lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConditionsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.updateBtn.setOnClickListener{
            val waterCon=binding.cWaterCon.text.toString().toFloatOrNull()
            val tempCon=binding.cTempCon.text.toString().toFloatOrNull()
            val lightCon=binding.cLightCon.text.toString().toFloatOrNull()



            if (waterCon != null) {
                if (tempCon != null) {
                    if (lightCon != null) {
                        updateData(waterCon,tempCon,lightCon)
                    }
                }
            }



        }


    }

    private fun updateData(waterCon: Float, tempCon: Float, lightCon: Float) {
        database=FirebaseDatabase.getInstance().getReference("Conditions")
        val conditions= mapOf(
            "WaterCon" to waterCon,
            "TempCon" to tempCon,
            "LightCon" to lightCon

        )
        database.updateChildren(conditions).addOnSuccessListener {
            binding.cTempCon.text.clear()
            binding.cLightCon.text.clear()
            binding.cWaterCon.text.clear()
            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"failed to update",Toast.LENGTH_SHORT).show()
        }

    }
}


