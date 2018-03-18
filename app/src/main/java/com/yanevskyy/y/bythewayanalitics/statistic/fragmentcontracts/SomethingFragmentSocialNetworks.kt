package com.yanevskyy.y.bythewayanalitics.statistic.fragmentcontracts

interface SomethingFragmentSocialNetworks {
    fun showNetworks(countAnyNetworks: Int, countsNetworks: Map<String, Int>, percentsNetworks: Map<String, Int>)
}