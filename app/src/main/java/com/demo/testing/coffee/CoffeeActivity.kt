package com.demo.testing.coffee

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.demo.testing.R

import kotlinx.android.synthetic.main.activity_coffee.*
import kotlinx.android.synthetic.main.content_coffee.descText

class CoffeeActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_coffee)
    setSupportActionBar(toolbar)

    descText.text  = intent.getStringExtra("Desc Text")

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show()
    }

    fab1.setOnClickListener{
            startActivity(Intent(this,CoffeeListActivity::class.java))
    }
  }
}
