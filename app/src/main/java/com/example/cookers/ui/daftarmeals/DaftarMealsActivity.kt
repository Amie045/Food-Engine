package com.example.cookers.ui.daftarmeals

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookers.R
import com.example.cookers.adapter.AdapterMeals
import com.example.cookers.api.ApiConfig
import com.example.cookers.model.Meals
import com.example.cookers.ui.detailmeals.DetailMealsActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class DaftarMealsActivity : AppCompatActivity(), DaftarMealsView {
    private lateinit var daftarMealsPresenter: DaftarMealsPresenter
    private lateinit var adapterMeals: AdapterMeals
    private val listMeals: MutableList<Meals> = mutableListOf()
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_meals)
        title = "Food List"

        ApiConfig.getApi(this)
        daftarMealsPresenter = DaftarMealsPresenter(this)

        val intent = intent
        intent?.let {
            category = it.getStringExtra("category")
        }

        swipeRefresh.post {
            loadData(category)
        }

        swipeRefresh.setOnRefreshListener {
            loadData(category)
        }
    }

    private fun loadData(category: String?) {
        daftarMealsPresenter.getMeals(category)
        adapterMeals = AdapterMeals(this, listMeals) {
            val intent = Intent(this, DetailMealsActivity::class.java)
            intent.putExtra("id", it.idMeal)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterMeals
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun onResponse(list: ArrayList<Meals>?) {
        listMeals.clear()
        list?.let {
            listMeals.addAll(list)
        }
        adapterMeals.notifyDataSetChanged()
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }
}
