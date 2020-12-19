package com.example.cookers.ui.detailmeals

import com.example.cookers.model.Meals


interface DetailMealsView {
    fun showLoading()
    fun onResponse(list: ArrayList<Meals>?)
    fun onError(message: String?)
    fun hideLoading()
}