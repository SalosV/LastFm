package com.example.lastfm.Artists.listArtist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.DataApp
import com.example.domain.usesCases.Artists.ArtistsUc
import com.example.lastfm.utils.BaseViewModel
import com.example.lastfm.utils.isInternetAvailable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ArtistsViewModel(
    private val artistsUc: ArtistsUc,
    private val app: DataApp
) : BaseViewModel() {

    private val _mutableLiveData = MutableLiveData<ArtistsState>()
    val artistsLiveData: LiveData<ArtistsState>
        get() = _mutableLiveData

    fun getArtists() {

        val getArtists =
            if (app.isInternetAvailable())
                artistsUc.getArtists()
            else
                artistsUc.getArtistsLocal()

        getArtists
            .doOnSubscribe {
                _mutableLiveData.postValue(ArtistsState.Loading)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _mutableLiveData.value =
                        ArtistsState.ShowArtists(it)
                },
                onError = {
                    _mutableLiveData.value =
                        ArtistsState.Error(it.message.toString())
                }
            ).addTo(disposeBag)
    }

    fun searchArtists(query: String) {

        val getSearchArtists =
            if (app.isInternetAvailable())
                artistsUc.getSearchArtists(query)
            else
                artistsUc.getSearchArtistsLocal(query)

        getSearchArtists
            .doOnSubscribe {
                _mutableLiveData.postValue(ArtistsState.Loading)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _mutableLiveData.value =
                        ArtistsState.ShowArtists(it)
                },
                onError = {
                    _mutableLiveData.value =
                        ArtistsState.Error(it.message.toString())
                }
            ).addTo(disposeBag)
    }
}