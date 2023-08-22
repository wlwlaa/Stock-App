package com.example.project1.util

import com.example.project1.data.local.IntradayInfo


fun getStockChartPoints(prices: List<Double>, time: List<Long>) : List<IntradayInfo> {
    val list = mutableListOf<IntradayInfo>()
    for (i in prices.indices) {
        list.add(
            IntradayInfo(
                prices[i],
                timestampToTime(time[i])
            )
        )
    }
    return list
}
