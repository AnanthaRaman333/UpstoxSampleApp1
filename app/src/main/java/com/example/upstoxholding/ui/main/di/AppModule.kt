package com.example.upstoxholding.ui.main.di

import android.content.Context
import com.example.upstoxholding.ui.main.model.repo.HoldingsAPIInterface
import com.example.upstoxholding.ui.main.model.repo.db.HoldingsDAO
import com.example.upstoxholding.ui.main.model.repo.db.HoldingsRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl(): String {
        return HoldingsAPIInterface.BASE_URL
    }

    @Provides
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun providesOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    @Provides
    fun provideRetrofit(
        @Named("BASE_URL") baseUrl: String,
        convertor: Converter.Factory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(convertor)
            .client(client)
            .build()
    }

    @Provides
    fun providesRetrofitService(retrofit: Retrofit): HoldingsAPIInterface {
        return retrofit.create(HoldingsAPIInterface::class.java)
    }

    @Provides
    fun providesHoldingsRoomDatabase(database: HoldingsRoomDatabase) : HoldingsDAO{
        return database.holdingsDao()
    }

    @Provides
    fun providesHoldingDAO(@ApplicationContext appContext: Context) : HoldingsRoomDatabase {
        return HoldingsRoomDatabase.getDatabase(appContext.applicationContext)
    }
}