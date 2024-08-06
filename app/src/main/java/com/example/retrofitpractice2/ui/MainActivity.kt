package com.example.retrofitpractice2.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitpractice2.R
import com.example.retrofitpractice2.data.model.CurrencyResponse
import com.example.retrofitpractice2.data.network.RetrofitClient
import com.example.retrofitpractice2.databinding.ActivityMainBinding
import com.example.retrofitpractice2.ui.adapter.CurrencyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

        private lateinit var binding: ActivityMainBinding
        private lateinit var adapter: CurrencyAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setupRecyclerView()
            fetchCurrencyRates()
        }

        private fun setupRecyclerView() {
            adapter = CurrencyAdapter(emptyList())
            binding.currencyRecyclerView.layoutManager = LinearLayoutManager(this)
            binding.currencyRecyclerView.adapter = adapter
        }

        private fun fetchCurrencyRates() {
            RetrofitClient.instance.getCurrencyRates().enqueue(object : Callback<CurrencyResponse> {
                override fun onResponse(call: Call<CurrencyResponse>, response: Response<CurrencyResponse>) {
                    if (response.isSuccessful) {
                        val currencyResponse = response.body()
                        currencyResponse?.Valute?.values?.let {
                            adapter = CurrencyAdapter(it.toList())
                            binding.currencyRecyclerView.adapter = adapter
                        }
                    }
                }

                override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }
