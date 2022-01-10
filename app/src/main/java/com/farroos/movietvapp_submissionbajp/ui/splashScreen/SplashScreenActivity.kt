package com.farroos.movietvapp_submissionbajp.ui.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.farroos.movietvapp_submissionbajp.R
import com.farroos.movietvapp_submissionbajp.ui.home.HomeActivity
import com.farroos.movietvapp_submissionbajp.utility.DELAY_TIME

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, DELAY_TIME)
    }

}