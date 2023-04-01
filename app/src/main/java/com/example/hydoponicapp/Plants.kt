package com.example.hydoponicapp

data class Plants(
    var Temperature : String ?= null,
    var Humidity : String ?= null,
    var WaterLevel : String ?= null,
    var LightSensitivity: String ?= null,
    var PlantName : String ?= null
)
