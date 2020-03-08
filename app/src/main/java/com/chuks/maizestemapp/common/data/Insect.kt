package com.chuks.maizestemapp.common.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
/**
 * This is the [Insect] data class
 * */
@Entity(tableName = "insect")
data class Insect(
    @PrimaryKey
    var id : Int,
    var insect_image: String,
    var name: String,
    var latitude: String,
    var longitude: String,
    var date: String,
    var time: String,
    var count: Int
){
    constructor() : this(0, "", "", "", "", "", "", 0)
}

/**
 * This is the [MaizePlot]  data class
 * */
@Entity(tableName = "maize_plot")
data class MaizePlot(
    @PrimaryKey
    val id: Int,
    val fall_armyworm: String,
    val egyptian_worm: String,
    val african_worm: String,
    val economic_injury_level: String,
    val economic_threshold: String
)



