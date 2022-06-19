package com.example.foodies

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.foodies.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val tabLayout = binding.tabLayout
        viewPager = binding.viewpager
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                Toast.makeText(
//                    this@MainActivity, position.toString(),
//                    Toast.LENGTH_SHORT
//                ).show()
//
//            }
//        })

        val jsonTextProducts = readText(this, R.raw.products)
        val typeTokenProducts = object : TypeToken<List<Products>>() {}.type
        val productsList =
            Gson().fromJson<List<Products>>(jsonTextProducts, typeTokenProducts)

        val jsonTextCategories = readText(this, R.raw.categories)
        val typeTokenCategories = object : TypeToken<List<Categories>>() {}.type
        val categoriesList =
            Gson().fromJson<List<Categories>>(jsonTextCategories, typeTokenCategories)

        val jsonTextTags = readText(this, R.raw.tags)
        val typeTokenTags = object : TypeToken<List<Tags>>() {}.type
        val tagsList =
            Gson().fromJson<List<Tags>>(jsonTextTags, typeTokenTags)

        val adapter = MenuAdapter(this)
        adapter.setData(productsList, categoriesList, tagsList)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = categoriesList[position].name
        }.attach()
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

}


data class Products(
    val carbohydrates_per_100_grams: Double,
    val category_id: Int,
    val description: String,
    val energy_per_100_grams: Double,
    val fats_per_100_grams: Double,
    val id: Int,
    val image: String,
    val measure: Int,
    val measure_unit: String,
    val name: String,
    val price_current: Int,
    val price_old: Int,
    val proteins_per_100_grams: Double,
    val tag_ids: List<Int>
)


class Categories(
    var id: Int,
    var name: String
)

class Tags(
    var id: Int,
    var name: String
)


private fun readText(context: Context, resId: Int): String {
    val Is: InputStream = context.resources.openRawResource(resId)
    val br = BufferedReader(InputStreamReader(Is))
    val sb = StringBuilder()
    var s: String?
    while (br.readLine().also { s = it } != null) {
        sb.append(s)
        sb.append("\n")
    }
    return sb.toString()
}