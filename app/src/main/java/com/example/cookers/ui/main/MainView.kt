package com.example.cookers.ui.main

import com.example.cookers.model.Categories


interface MainView {
    fun showLoading()
    fun onResponse(list: ArrayList<Categories>?)
    fun onError(message: String?)
    fun hideLoading()
}