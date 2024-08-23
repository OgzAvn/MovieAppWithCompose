package com.oguz.cleanmovieapp.data.di

import com.oguz.cleanmovieapp.data.remote.MovieApi
import com.oguz.cleanmovieapp.data.repository.MovieRepositoryImpl
import com.oguz.cleanmovieapp.domain.repository.MovieRepository
import com.oguz.cleanmovieapp.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //TODO: MovieApi interfacesi kullanıldıgında neyi inject edeceğini bilecek
    @Provides
    @Singleton
    fun provideMovieApi() : MovieApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api : MovieApi) : MovieRepository{
        return MovieRepositoryImpl(api = api)
    }

}