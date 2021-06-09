package br.com.santanderdevweek.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.santanderdevweek.R
import gr.net.maroulis.library.EasySplashScreen

class SplashScreenActivity : AppCompatActivity() {

    private val splashTimeOut = 3_000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashView = EasySplashScreen(this)
            .withFullScreen()
            .withTargetActivity(MainActivity::class.java)
            .withSplashTimeOut(splashTimeOut)
            .withLogo(R.drawable.ic_savings_foreground)
            .create()

        setContentView(splashView)
    }
}