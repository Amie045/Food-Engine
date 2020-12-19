package com.example.cookers.ui.daftarmeals

import com.example.cookers.model.Meals


interface DaftarMealsView {
    fun showLoading()
    fun onResponse(list: ArrayList<Meals>?)
    fun onError(message: String?)
    fun hideLoading()
}