package com.example.matdis.api.town

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matdis.BuildConfig
import com.example.matdis.POD.PODRetrofitImpl
import com.example.matdis.POD.PODServerResponseData
import com.example.matdis.POD.PictureOfTheDayData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTownViewModel
    (private val liveDataForViewToObserve: MutableLiveData<PictureOfTheDayData> = MutableLiveData(),
     private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl(),
     val apiKey: String = BuildConfig.NASA_API_KEY): ViewModel() {

    fun getMyTownPic(): LiveData<PictureOfTheDayData> {
        sendTownRequest()
        return liveDataForViewToObserve
    }

    private fun sendTownRequest() {

        liveDataForViewToObserve.value = PictureOfTheDayData.Loading(null)
        val lon: Float = 55f
        val lat: Float = 36.43f
        val dat: String= "2020-06-12"
        val dim: Float = 0.15f

        if (apiKey.isBlank()) {
            PictureOfTheDayData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl.getRetrofitImpl().getPictureOfMyTown(lon, lat, dat, dim, apiKey).enqueue(object :
                Callback<PODServerResponseData> {
                override fun onResponse(
                    call: Call<PODServerResponseData>,
                    response: Response<PODServerResponseData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                            PictureOfTheDayData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                PictureOfTheDayData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                PictureOfTheDayData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                    liveDataForViewToObserve.value = PictureOfTheDayData.Error(t)
                }
            })
        }

    }
}