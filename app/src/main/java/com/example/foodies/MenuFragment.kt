package com.example.foodies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


const val ARG_OBJECT = "object"
const val ARG_TAG = "tag"

class MenuFragment(
    val productsList: List<Products>,
    val categoriesList: List<Categories>,
    val tagsList: List<Tags>
) : Fragment() {
    private lateinit var resyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resyclerView = view.findViewById(R.id.recyclerView)!!
        val llm = GridLayoutManager(context, 2)
        llm.orientation = LinearLayoutManager.VERTICAL
        resyclerView.layoutManager = llm

        val stateClickListener: OnStateClickListener = object : OnStateClickListener {
            override fun onStateClick(state: Products, position: Int) {
                val intent = Intent(context, TabElementActivity::class.java)
                startActivity(intent)

                Toast.makeText(
                    context,
                    "Был выбран пункт " + state.name,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        val tag = 0
        arguments.takeIf { it!!.containsKey(ARG_OBJECT) }?.apply {
            val items = productsList.filter {
                it.category_id == categoriesList[getInt(ARG_OBJECT)].id
            }
            if (tag == 0) {
                resyclerView.adapter = FoodAdapter(this, items, stateClickListener)
            } else {
                val items_tags = items.filter { tag in it.tag_ids }
                resyclerView.adapter = FoodAdapter(this, items_tags, stateClickListener)
            }

        }

    }
}