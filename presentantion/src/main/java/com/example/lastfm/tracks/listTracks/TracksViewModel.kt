package com.example.lastfm.tracks.listTracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.DataApp
import com.example.domain.usesCases.tracks.TracksUseCase
import com.example.lastfm.utils.BaseViewModel
import com.example.lastfm.utils.isInternetAvailable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class TracksViewModel(
    private val tracksUseCase: TracksUseCase,
    private val app: DataApp
) : BaseViewModel() {

    private val _tracksMutableLiveData = MutableLiveData<TracksState>()
    val tracksLiveData: LiveData<TracksState>
        get() = _tracksMutableLiveData

    fun getTracks() {

        val getTracks = if (app.isInternetAvailable())
            tracksUseCase.getTracks()
        else
            tracksUseCase.getTracksLocal()

        getTracks
            .doOnSubscribe {
                _tracksMutableLiveData.postValue(TracksState.Loading)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _tracksMutableLiveData.value = TracksState.ShowTracks(it)
                },
                onError = {
                    _tracksMutableLiveData.value = TracksState.Error(it.message.toString())
                }
            ).addTo(disposeBag)
    }

}