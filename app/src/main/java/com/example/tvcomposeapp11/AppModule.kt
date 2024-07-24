package com.example.tvcomposeapp11

import com.example.tvcomposeapp11.interfaces.MovieApiService
import com.example.tvcomposeapp11.interfaces.MovieRepository
import com.example.tvcomposeapp11.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://api.themoviedb.org/3/")
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

    @Provides
    @Singleton
    fun provideTmdbApi(): MovieApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun movieRepository(tmdbServie : MovieApiService) : MovieRepository {
         return MovieRepositoryImpl(tmdbServie)
    }
}

//@Provides
//@Singleton
//fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//    return Retrofit.Builder()
//        .baseUrl("https://api.themoviedb.org/3/")
//        .client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//}
//
//@Provides
//@Singleton
//fun provideTmdbApi(retrofit: Retrofit): MovieApiService {
//    return retrofit.create(MovieApiService::class.java)
//}