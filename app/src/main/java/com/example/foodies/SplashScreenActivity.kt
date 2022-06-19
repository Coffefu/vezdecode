package com.example.foodies

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash)

        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 2600)
        }
    }
}