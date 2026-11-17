package cl.bootcamp.leeconnos.di

import android.location.Geocoder
import cl.bootcamp.leeconnos.data.api.ApiClient
import cl.bootcamp.leeconnos.data.local.database.AppDatabase
import cl.bootcamp.leeconnos.data.local.datastore.ThemePreferences
import cl.bootcamp.leeconnos.data.repositorio.LibroRepository
import cl.bootcamp.leeconnos.data.repositorio.ThemeRepository
import cl.bootcamp.leeconnos.viewmodel.CheckoutViewModel
import cl.bootcamp.leeconnos.viewmodel.HomeViewModel
import cl.bootcamp.leeconnos.viewmodel.MainViewModel
import com.google.android.libraries.places.api.Places
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import java.util.Locale


val appModule = module {

    single {
        AppDatabase.getInstance(androidContext())
    }

    single {
        get<AppDatabase>().libroDao()
    }


    single {
        ApiClient.apiService
    }

    single {
        ThemePreferences(androidContext())
    }

    single {
        ThemeRepository(get())
    }
    single {
        LibroRepository(
            api = get(),
            libroDao = get()
        )
    }

    single {
        Places.createClient(androidContext())
    }

    factory {
        Geocoder(androidContext(), Locale.getDefault())
    }


    viewModel {

        MainViewModel(
            libroRepository = get(),
            themeRepository = get()
        )
    }

    viewModel {
        HomeViewModel(
            libroRepository = get()
        )
    }

    viewModel {
        CheckoutViewModel(
            libroRepository = get(),
            placesClient = get(),
            geocoder = get()
        )
    }
}