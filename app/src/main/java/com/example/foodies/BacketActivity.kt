package com.example.foodies

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class BacketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backet)
        val buttonreturn = findViewById<ImageButton>(R.id.buttonreturn)
        buttonreturn.setOnClickListener {
            finish()
        }
    }
}