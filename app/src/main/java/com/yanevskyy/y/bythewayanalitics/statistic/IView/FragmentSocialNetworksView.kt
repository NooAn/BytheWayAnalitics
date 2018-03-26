package com.yanevskyy.y.bythewayanalitics.statistic.IView

interface FragmentSocialNetworksView {
    fun showNetworks(countAnyNetworks: Int, countsNetworks: Map<String, Int>, percentsNetworks: Map<String, Int>)
}