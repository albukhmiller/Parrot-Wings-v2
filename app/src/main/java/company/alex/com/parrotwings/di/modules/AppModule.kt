package company.alex.com.parrotwings.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesContext() = context

    @Provides
    @Singleton
    fun providesSharedPreferences() = context.getSharedPreferences("sharp_dev", Context.MODE_PRIVATE)
}