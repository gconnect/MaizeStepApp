package com.chuks.maizestemapp.common.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.chuks.maizestemapp.R

/**
 * The SplashScreenActivity is the welcome screen
 * */
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed( {
        startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 2000)
    }
}
