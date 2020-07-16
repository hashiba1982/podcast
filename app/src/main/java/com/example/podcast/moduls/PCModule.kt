package com.example.podcast.moduls

import com.example.podcast.vm.HomeViewModel
import com.example.podcast.vm.MusicListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pcModule = module{
    viewModel { HomeViewModel() }
    viewModel { MusicListViewModel() }
}