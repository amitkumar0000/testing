package com.demo.testing.coffee

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.testing.R

class CoffeeAdapter : RecyclerView.Adapter<CoffeeViewHolder>() {

  var listOfCoffee = mutableListOf<Coffee>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    CoffeeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.coffee_layout, parent, false))

  override fun getItemCount() = listOfCoffee.size

  override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
    holder.coffeeImageView.setImageDrawable(listOfCoffee[position].imageSrc)
    holder.coffeeNameView.text = listOfCoffee[position].name

    holder.coffeeNameView.setOnClickListener{
      holder.coffeeImageView.context.startActivity(Intent(holder.coffeeNameView.context,CoffeeActivity::class.java))
    }
  }
}