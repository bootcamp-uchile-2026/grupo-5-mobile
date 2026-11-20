package cl.bootcamp.leeconnos.di

import android.location.Geocoder
import cl.bootcamp.leeconnos.data.api.ApiClient
import cl.bootcamp.leeconnos.data.local.database.AppDatabase
import cl.bootcamp.leeconnos.data.local.datastore.AuthPreferences
import cl.bootcamp.leeconnos.data.local.datastore.ThemePreferences
import cl.bootcamp.leeconnos.data.repositorio.AuthRepository
import cl.bootcamp.leeconnos.data.repositorio.DireccionRepository
import cl.bootcamp.leeconnos.data.repositorio.LibroRepository
import cl.bootcamp.leeconnos.data.repositorio.ThemeRepository
import cl.bootcamp.leeconnos.viewmodel.CheckoutViewModel
import cl.bootcamp.leeconnos.viewmodel.HomeViewModel
import cl.bootcamp.leeconnos.viewmodel.LoginViewModel
import cl.bootcamp.leeconnos.viewmodel.MainViewModel
import com.google.android.libraries.places.api.Places
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import java.util.Locale


val appModule = module {

    //Room
    single {
        AppDatabase.getInstance(androidContext())
    }

    single {
        get<AppDatabase>().libroDao()
    }

    single {
        get<AppDatabase>().direccionDao()
    }


    //API
    single {
        ApiClient(
            authPreferences = get()
        )
    }

    single {
        get<ApiClient>().apiService
    }

    single {
        AuthPreferences(androidContext())
    }


    //Repositorios

    single {
        AuthRepository(
            api = get(),
            authPreferences = get()
        )
    }

    single {
        LibroRepository(
            api = get(),
            libroDao = get()
        )
    }

    single {
        DireccionRepository(
            api = get(),
            direccionDao = get()
        )
    }

    //Preferencias usuario (tema)
    single {
        ThemePreferences(androidContext())
    }

    single {
        ThemeRepository(get())
    }

    //GOOGLE MAPS API
    single {
        Places.createClient(androidContext())
    }

    factory {
        Geocoder(androidContext(), Locale.getDefault())
    }


    //ViewModels
    viewModel {
        MainViewModel(
            libroRepository = get(),
            themeRepository = get(),
            authRepository = get()
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
            direccionRepository = get(),
            placesClient = get(),
            geocoder = get()
        )
    }

    viewModel {
        LoginViewModel(
            authRepository = get()
        )
    }
}