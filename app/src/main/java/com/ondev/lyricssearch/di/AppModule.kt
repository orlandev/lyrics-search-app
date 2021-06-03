package com.ondev.lyricssearch.di

import android.content.Context
import com.ondev.lyricssearch.data.AppDatabase
import com.ondev.lyricssearch.data.repository.LyricsRepository
import com.ondev.lyricssearch.data.source.local.ILyricsDao
import com.ondev.lyricssearch.data.source.remote.ILyricsApi
import com.ondev.lyricssearch.data.source.remote.LyricsRemoteDataSource
import com.ondev.lyricssearch.utils.API_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    fun provideLyricsApiService(retrofit: Retrofit): ILyricsApi =
        retrofit.create(ILyricsApi::class.java)

    @Singleton
    @Provides
    fun provideLyricsRemoteDataSource(lyricsApi: ILyricsApi) =
        LyricsRemoteDataSource(lyricsApi)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideLyricsDao(db: AppDatabase) = db.lyricsDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: LyricsRemoteDataSource,
        localDataSource: ILyricsDao
    ) = LyricsRepository(remoteDataSource, localDataSource)


}