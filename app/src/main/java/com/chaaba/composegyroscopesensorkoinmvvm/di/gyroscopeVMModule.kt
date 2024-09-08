package com.chaaba.composegyroscopesensorkoinmvvm.di

import com.chaaba.composegyroscopesensorkoinmvvm.vm.GyroscopeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    //provide gyroscope vm
    viewModel { GyroscopeViewModel(get()) }

}