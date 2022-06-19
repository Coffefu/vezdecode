package com.example.foodies


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class MenuAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    var categories: Int = 0
    lateinit var productsList: List<Products>
    lateinit var categoriesList: List<Categories>
    lateinit var tagsList: List<Tags>
    override fun getItemCount(): Int = categories


    override fun createFragment(position: Int): Fragment {
        val fragment = MenuFragment(this.productsList, this.categoriesList, this.tagsList)
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position)
//            putIntArray(ARG_TAG,IntArray())
        }
        return fragment
    }


    fun setData(
        productsList: List<Products>,
        categoriesList: List<Categories>,
        tagsList: List<Tags>
    ) {
        this.categories = categoriesList.size
        this.productsList = productsList
        this.categoriesList = categoriesList
        this.tagsList = tagsList
    }

}