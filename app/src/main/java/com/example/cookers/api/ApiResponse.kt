package com.example.cookers.api

import com.example.cookers.model.Categories
import com.example.cookers.model.Meals
import com.google.gson.annotations.SerializedName

data class ApiResponse (
        @SerializedName("categories")
        val categories: ArrayList<Categories>?,

        @SerializedName("meals")
        val meals: ArrayList<Meals>?)