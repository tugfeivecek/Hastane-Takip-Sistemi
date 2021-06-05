package com.tugfeivecek.hastanetakipsistemi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugfeivecek.hastanetakipsistemi.model.Search
import com.tugfeivecek.hastanetakipsistemi.model.SearchResponse
import com.tugfeivecek.hastanetakipsistemi.service.HospitalAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchViewModel : ViewModel() {

    private val searchApiService = HospitalAPIService()
    private val disposableSearch = CompositeDisposable()
    val searches = MutableLiveData<List<Search>>()

    fun refreshSearchData(kelime: String) {
        getSearchDataFromAPI(kelime)
    }

    fun getSearchDataFromAPI(kelime: String) {

        disposableSearch.add(
            searchApiService.getSearchData(kelime)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SearchResponse>() {
                    override fun onSuccess(t: SearchResponse) {
                        searches.value = t.allAranan
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }
}