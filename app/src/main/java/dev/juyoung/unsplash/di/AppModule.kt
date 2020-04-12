package dev.juyoung.unsplash.di

import dev.juyoung.unsplash.BuildConfig
import dev.juyoung.unsplash.data.network.interceptor.UnsplashHeaderInterceptor
import dev.juyoung.unsplash.data.network.services.UnsplashService
import dev.juyoung.unsplash.data.source.UnsplashDataSource
import dev.juyoung.unsplash.data.source.UnsplashLocalDataSource
import dev.juyoung.unsplash.data.source.UnsplashRemoteDataSource
import dev.juyoung.unsplash.data.source.UnsplashRepository
import dev.juyoung.unsplash.extensions.isDebuggable
import dev.juyoung.unsplash.ui.detail.DetailViewModel
import dev.juyoung.unsplash.ui.main.MainViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

enum class Qualifiers {
    // network qualifiers
    LOGGER_INTERCEPTOR,
    HEADER_INTERCEPTOR,

    // repository qualifiers
    LOCAL_DATA,
    REMOTE_DATA,
    REPOSITORY
}

val networkModule = module {
    single<Interceptor>(named(Qualifiers.LOGGER_INTERCEPTOR)) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<Interceptor>(named(Qualifiers.HEADER_INTERCEPTOR)) {
        UnsplashHeaderInterceptor()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder().run {
            if (androidContext().isDebuggable) {
                addInterceptor(get<Interceptor>(named(Qualifiers.LOGGER_INTERCEPTOR)))
            }

            addInterceptor(get<Interceptor>(named(Qualifiers.HEADER_INTERCEPTOR)))
            build()
        }
    }

    single<Retrofit> {
        Retrofit.Builder().run {
            baseUrl(BuildConfig.API_URI)
            client(get())
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            build()
        }
    }

    single<UnsplashService> { get<Retrofit>().create(UnsplashService::class.java) }
}

val repositoryModule = module {
    single<UnsplashDataSource>(named(Qualifiers.LOCAL_DATA)) { UnsplashLocalDataSource() }
    single<UnsplashDataSource>(named(Qualifiers.REMOTE_DATA)) { UnsplashRemoteDataSource(get()) }
    single<UnsplashDataSource>(named(Qualifiers.REPOSITORY)) {
        UnsplashRepository(
            get(named(Qualifiers.LOCAL_DATA)),
            get(named(Qualifiers.REMOTE_DATA))
        )
    }
}

val viewModelModule = module {
    viewModel { MainViewModel(get(named(Qualifiers.REPOSITORY))) }
    viewModel { (id: String) -> DetailViewModel(id, get(named(Qualifiers.REPOSITORY))) }
}
