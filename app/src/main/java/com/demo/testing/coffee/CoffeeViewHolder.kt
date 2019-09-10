package com.demo.testing.coffee

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.coffee_layout.view.coffee_image_id
import kotlinx.android.synthetic.main.coffee_layout.view.coffee_text

class CoffeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  var coffeeImageView = view.coffee_image_id
  var coffeeNameView = view.coffee_text
}