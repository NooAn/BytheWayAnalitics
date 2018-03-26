package com.yanevskyy.y.bythewayanalitics.statistic

fun calculatePercents(calculatingValue: Int, fullValue: Int) =
        Math.round(calculatingValue.toDouble() / fullValue * 100).toInt()

