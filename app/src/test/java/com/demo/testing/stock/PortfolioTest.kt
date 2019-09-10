package com.demo.testing.stock

import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PortfolioTest {

  @Mock
  lateinit var stockService: StockService

  lateinit var stockList:List<Stock>

  lateinit var portfolio :Portfolio

  @Before
  fun setup(){
    stockList = getListOfStocks()

    portfolio = Portfolio(stockService,stockList)

    whenever(stockService.getPrice(Stock("veg","veg-1",40))).thenReturn(10.0)
    whenever(stockService.getPrice(Stock("veg","veg-2",50))).thenReturn(20.0)
    whenever(stockService.getPrice(Stock("veg","veg-3",60))).thenReturn(10.0)
    whenever(stockService.getPrice(Stock("veg","veg-4",70))).thenReturn(20.0)
  }

  @Test
  fun getMarketValue() {
    val price =  portfolio.getMarketValue()

    assertEquals(price,3400.0,0.0)
  }


  fun getListOfStocks():List<Stock>{
    var list = ArrayList<Stock>()
    list.add(Stock("veg","veg-1",40))
    list.add(Stock("veg","veg-2",50))
    list.add(Stock("veg","veg-3",60))
    list.add(Stock("veg","veg-4",70))
    return list
  }
}