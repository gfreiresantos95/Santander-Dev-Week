package br.com.santanderdevweek.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.santanderdevweek.data.Conta
import br.com.santanderdevweek.data.local.Mockup

class MainViewModel : ViewModel() {

    private val mContaMutableLiveData: MutableLiveData<Conta> = MutableLiveData()

    fun buscarConta(): LiveData<Conta> {
        mContaMutableLiveData.value = Mockup().getConta()
        return mContaMutableLiveData
    }
}