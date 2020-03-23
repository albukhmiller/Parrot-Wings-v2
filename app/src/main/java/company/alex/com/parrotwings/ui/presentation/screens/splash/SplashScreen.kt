package company.alex.com.parrotwings.ui.presentation.screens.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.ui.presentation.screens.mainActivity.MainActivity
import org.jetbrains.anko.startActivity


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity<MainActivity>()
            this.finish()
        }, 3000)
    }
}
