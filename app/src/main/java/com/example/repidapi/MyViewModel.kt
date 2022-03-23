package com.example.repidapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.repidapi.data.SearchQueryRequestBody
import com.example.repidapi.data.SearchQueryResponse
import com.example.repidapi.resultat.SearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyViewModel : ViewModel() {
    private val myRetrofit = RapidRetrofit()
    private val serviceApi = myRetrofit.retrofitRapid.create(Service::class.java)

    private val _queryLivedata: MutableLiveData<String> = MutableLiveData()
    val queryLivedata: LiveData<String> = _queryLivedata
    private val _loaderLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loaderLiveData: LiveData<Boolean> = _loaderLiveData

    val serpLiveDataResponse: LiveData<SearchQueryResponse?> =
        Transformations.switchMap(_queryLivedata){

            _loaderLiveData.value = true

       val liveData = MutableLiveData<SearchQueryResponse?>()

        val res = serviceApi.rapidSerp(SearchQueryRequestBody(it,"https://rapidapi.com"))

        res.enqueue(object : Callback<SearchQueryResponse>{
            override fun onResponse(
                call: Call<SearchQueryResponse>,
                response: Response<SearchQueryResponse>
            ) {
                if (response.isSuccessful){
                    liveData.value = response.body()
                }
                else
                    liveData.value = null
                _loaderLiveData.value =false

            }

            override fun onFailure(call: Call<SearchQueryResponse>, t: Throwable) {
                liveData.value = null
                _loaderLiveData.value =false
            }
        })

        liveData
    }

    val scholarLiveData : LiveData<SearchResult> =
        Transformations.switchMap(_queryLivedata){

            _loaderLiveData.value =true

            val liveData = MutableLiveData<SearchResult?>()

            val res = serviceApi.rapidScholar(it)
            res.enqueue(object : Callback<SearchResult>{
                override fun onResponse(
                    call: Call<SearchResult>,
                    response: Response<SearchResult>
                ) {if (response.isSuccessful){
                    liveData.value = response.body()
                }else{
                    liveData.value = null
                }
                    _loaderLiveData.value =false
                }

                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    liveData.value = null
                    _loaderLiveData.value =false
                }

            })

            liveData
        }

    fun setValue(query: String) {
        _queryLivedata.value = query
    }

}