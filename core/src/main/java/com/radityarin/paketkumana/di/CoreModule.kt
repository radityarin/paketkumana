package com.radityarin.paketkumana.di

import androidx.room.Room
import com.radityarin.paketkumana.data.AppRepository
import com.radityarin.paketkumana.data.source.local.LocalDataSource
import com.radityarin.paketkumana.data.source.local.room.AppDatabase
import com.radityarin.paketkumana.data.source.remote.RemoteDataSource
import com.radityarin.paketkumana.data.source.remote.network.ApiService
import com.radityarin.paketkumana.domain.repository.IAppRepository
import com.radityarin.paketkumana.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    single { get<AppDatabase>().appDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("radityarin".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "Resi.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
//        val hostname = "api.themoviedb.org"
//        val certificatePinner = CertificatePinner.Builder()
//            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
//            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
//            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
//            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.binderbyte.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get(), get()) }
    single { AppExecutors() }
    single<IAppRepository> {
        AppRepository(
            get(),
            get(),
            get()
        )
    }
}