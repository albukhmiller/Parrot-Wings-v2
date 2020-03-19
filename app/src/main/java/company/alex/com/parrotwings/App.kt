package company.alex.com.parrotwings

import android.app.Application
import company.alex.com.parrotwings.di.components.AppComponent
import company.alex.com.parrotwings.di.components.DaggerAppComponent
import company.alex.com.parrotwings.di.modules.AppModule

open class App : Application() {

    companion object {

        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}