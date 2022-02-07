package com.example.exchangerates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
    private val viewModel: RateViewModel
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var listRate: List<Valute> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemRate = listRate[position]
        holder.bind(itemRate)
    }
    override fun getItemCount(): Int = listRate.size

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshList(listRate: List<Valute>) {
        this.listRate = listRate
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val twNumCode = view.findViewById<TextView>(R.id.twNumCode)
        private val tvCharCode = view.findViewById<TextView>(R.id.tvCharCode)
        private val tvNominal = view.findViewById<TextView>(R.id.tvNominal)
        private val tvName = view.findViewById<TextView>(R.id.tvName)
        private val tvValue = view.findViewById<TextView>(R.id.tvValue)
        private val button = view.findViewById<Button>(R.id.butReload)
        fun bind(itemRate: Valute) {
            twNumCode.text = itemRate.numCode
            tvCharCode.text = itemRate.charCode
            tvNominal.text = itemRate.nominal.toString()
            tvName.text = itemRate.name
            tvValue.text = itemRate.value.toString()
        }
    }
}
