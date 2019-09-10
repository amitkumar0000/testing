package com.demo.testing.stock

data class Stock(val name:String,val stockId:String,val quantity:Int)

interface StockService{
  fun getPrice(stock:Stock):Double
}

class Portfolio(val stockService:StockService,val stocks:List<Stock>){
  fun getMarketValue():Double{
    var marketValue = 0.0
    for(stk in stocks){
      marketValue += stockService.getPrice(stk) * stk.quantity
    }
    return marketValue
  }
}