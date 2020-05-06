package com.example.lastfm.Artists.detailArtist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.usesCases.Artists.DetailArtistsUc
import com.example.lastfm.utils.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DetailArtistViewModel(private val detailArtistsUc: DetailArtistsUc) : BaseViewModel() {

    private val _mutableDetailArtist = MutableLiveData<DetailArtistState>()
    val detailArtistLiveData: LiveData<DetailArtistState>
        get() = _mutableDetailArtist

    fun getInfoArtist(name: String, mbid: String) {
        detailArtistsUc.getInfoArtist(name, mbid)
            .doOnSubscribe {
                _mutableDetailArtist.postValue(DetailArtistState.Loading)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _mutableDetailArtist.value = DetailArtistState.ShowInformationArtist(it)
                },
                onError = {
                    _mutableDetailArtist.value = DetailArtistState.Error(it.message.toString())
                }
            ).addTo(disposeBag)
    }
}