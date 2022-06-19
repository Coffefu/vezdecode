package com.example.foodies

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

interface OnStateClickListener {
    fun onStateClick(state: Products, position: Int)
}

class FoodAdapter(
    val context: Bundle,
    val productsList: List<Products>,
    val onClickListener: OnStateClickListener
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder?>() {


    class FoodViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var cv: CardView
        var nameProduct: TextView
        var information: TextView
        var cartPhoto: ImageView
        var addCart: Button

        init {
            cv = itemView.findViewById(R.id.cv) as CardView
            nameProduct = itemView.findViewById(R.id.name) as TextView
            information = itemView.findViewById(R.id.info) as TextView
            cartPhoto = itemView.findViewById(R.id.imageProduct) as ImageView
            addCart = itemView.findViewById(R.id.button) as Button
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return FoodViewHolder(v)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.nameProduct.text = productsList[position].name
        holder.cartPhoto.setBackgroundResource(R.drawable.eda)
        val a = productsList[position].measure
        val b = productsList[position].measure_unit
        holder.information.text = "$a $b"

        val a_2 = productsList[position].price_current.toString()
        var b_2 = productsList[position].price_old.toString()
        if (b_2 == "0") {
            b_2 = ""
        } else {
            b_2 += " Р"
        }
        val htmlTaggedString = "$a_2 Р <s><small><font color=\"#8E8E93\">$b_2</font></small></s>"
        val textSpan = Html.fromHtml(htmlTaggedString)
        holder.addCart.text = textSpan

        holder.cv.setOnClickListener {
            onClickListener.onStateClick(productsList[position], position)
            PRODUCT = productsList[position]
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}