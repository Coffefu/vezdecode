package com.example.foodies

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TabElementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_element)
        val position = intent.getSerializableExtra("position")
        Toast.makeText(
            this,
            "Был выбран пункт " + PRODUCT.name,
            Toast.LENGTH_SHORT
        ).show()
        val name = findViewById<TextView>(R.id.name)
        name.text = PRODUCT.name

        val description = findViewById<TextView>(R.id.description)
        description.text = PRODUCT.description

        val weight = findViewById<TextView>(R.id.weight_2)
        weight.text = PRODUCT.measure.toString() + " " + PRODUCT.measure_unit

        val energy = findViewById<TextView>(R.id.energy_2)
        energy.text = PRODUCT.energy_per_100_grams.toString()

        val protein = findViewById<TextView>(R.id.protein_2)
        protein.text = PRODUCT.proteins_per_100_grams.toString()

        val fats = findViewById<TextView>(R.id.fats_2)
        fats.text = PRODUCT.fats_per_100_grams.toString()

        val carbohydrates = findViewById<TextView>(R.id.carbohydrates_2)
        carbohydrates.text = PRODUCT.energy_per_100_grams.toString()

        val buttonreturn = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        buttonreturn.setOnClickListener {
            finish()
        }
    }
}