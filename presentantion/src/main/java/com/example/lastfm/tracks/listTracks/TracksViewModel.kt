package com.example.lastfm.tracks.listTracks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.usesCases.tracks.TracksUseCase
import com.example.lastfm.utils.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class TracksViewModel(private val tracksUseCase: TracksUseCase) : BaseViewModel() {

    private val _tracksMutableLiveData = MutableLiveData<TracksState>()
    val tracksLiveData: LiveData<TracksState>
        get() = _tracksMutableLiveData

    fun getTracks() {
        tracksUseCase.getTracks()
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
                    Log.e("error", it.message.toString())
                    _tracksMutableLiveData.value = TracksState.Error(it.message.toString())
                }
            ).addTo(disposeBag)
    }

}