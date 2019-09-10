package com.demo.testing.coffee

import android.graphics.drawable.Drawable

data class Coffee(var imageSrc:Drawable,var name:String)

data class CoffeeActivityData(val title:String, val imageSrc:String, val desc:String)