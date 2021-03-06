package com.farroos.movietvapp_submissionbajp.ui.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.farroos.movietvapp_submissionbajp.R
import com.farroos.movietvapp_submissionbajp.ui.home.HomeActivity
import com.farroos.movietvapp_submissionbajp.utility.constant.DELAY_TIME

@SuppressLint("CustomSplashScreen")
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