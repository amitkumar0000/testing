package com.demo.testing

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.testing.coffee.Coffee
import com.demo.testing.coffee.CoffeeAdapter
import kotlinx.android.synthetic.main.activity_main.coffee_list as coffeeList

/**
 * [MainActivity]
 */
class MainActivity : AppCompatActivity() {

  val coffeeDrawableList:MutableList<Drawable>  by lazy {
   mutableListOf( resources.getDrawable(R.drawable.cof),
     resources.getDrawable(R.drawable.coff),
     resources.getDrawable(R.drawable.coffee),
     resources.getDrawable(R.drawable.coffeeee),
     resources.getDrawable(R.drawable.coffewq),
     resources.getDrawable(R.drawable.cofgeer),
     resources.getDrawable(R.drawable.coffewq),
     resources.getDrawable(R.drawable.coff),
     resources.getDrawable(R.drawable.cof)
   )
  }

  var coffeeName = listOf(
    "Coffee 1",
    "Coffee 2",
    "Coffee 3",
    "Coffee 4",
    "Coffee 5",
    "Coffee 6",
    "Coffee 7",
    "Coffee 8",
    "Coffee 9"
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setCoffeeList()
  }

  fun setCoffeeList(){
    var listOfCoffee = mutableListOf<Coffee>()
    var i =0
    for(coffeedrabable in coffeeDrawableList)
      listOfCoffee.add(Coffee(coffeedrabable,coffeeName[i++]))

    var adapter = CoffeeAdapter()

    coffeeList.adapter = adapter
    var linearLayoutManager = LinearLayoutManager(this)
    linearLayoutManager.orientation = RecyclerView.VERTICAL
    coffeeList.layoutManager = linearLayoutManager
    coffeeList.addItemDecoration(DividerItemDecoration(this,RecyclerView.HORIZONTAL))


    adapter.listOfCoffee = listOfCoffee
    adapter.notifyDataSetChanged()

  }
}
