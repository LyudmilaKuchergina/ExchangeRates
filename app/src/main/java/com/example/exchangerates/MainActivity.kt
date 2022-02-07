package com.example.exchangerates

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var model: RateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProvider(this).get(RateViewModel::class.java)

        val rcView = findViewById<RecyclerView>(R.id.rcView)
        rcView.hasFixedSize()

        rcView.layoutManager = LinearLayoutManager(this)
        val myAdapter = MyAdapter(model)
        rcView.adapter = myAdapter

        val button = findViewById<Button>(R.id.butReload)
        button.setOnClickListener {
            model.getValutes()
        }

        model.liveData.observe(this, {
            myAdapter.refreshList(it)
        })
    }

    override fun onResume() {
        super.onResume()
        model.getValutes()
    }
}