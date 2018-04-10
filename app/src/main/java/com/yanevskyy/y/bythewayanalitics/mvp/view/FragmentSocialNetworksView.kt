package com.yanevskyy.y.bythewayanalitics.mvp.view

interface FragmentSocialNetworksView {
    fun showNetworks(countAnyNetworks: Int, countsNetworks: Map<String, Int>, percentsNetworks: Map<String, Int>)
}