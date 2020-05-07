package com.example.lastfm.tracks.detailTrack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.usesCases.tracks.DetailTrackCaseUse
import com.example.lastfm.utils.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DetailTracksViewModel(private val detailTrackCaseUse: DetailTrackCaseUse) : BaseViewModel() {

    private val _mutableDetailTrack = MutableLiveData<DetailTrackState>()
    val detailTrackLiveData: LiveData<DetailTrackState>
        get() = _mutableDetailTrack

    fun getInfTrack(artist: String, track: String) {

        detailTrackCaseUse.getInfoTrack(artist, track)
            .doOnSubscribe {
                _mutableDetailTrack.postValue(DetailTrackState.Loading)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _mutableDetailTrack.value = DetailTrackState.ShowInformationTrack(it)
                },
                onError = {
                    _mutableDetailTrack.value = DetailTrackState.Error(it.message.toString())
                }
            ).addTo(disposeBag)
    }
}