package com.example.hydoponicapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val fList : ArrayList<Plants>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = fList[position]

        holder.plantName.text=currentItem.PlantName
        holder.temperature.text=currentItem.Temperature
        holder.humidity.text = currentItem.Humidity
        holder.waterLevel.text = currentItem.WaterLevel
        holder.light.text=currentItem.LightSensitivity

    }

    override fun getItemCount(): Int {

        return fList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val plantName: TextView =itemView.findViewById(R.id.plantName)
        val temperature : TextView = itemView.findViewById(R.id.temp)
        val humidity : TextView = itemView.findViewById(R.id.humidity)
        val waterLevel : TextView = itemView.findViewById(R.id.waterLevel)
        val light: TextView = itemView.findViewById(R.id.lightSensitivity)


    }

}