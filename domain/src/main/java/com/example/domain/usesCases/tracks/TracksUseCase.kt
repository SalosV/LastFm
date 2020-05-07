package com.example.domain.usesCases.tracks

import com.example.domain.models.Track
import com.example.domain.repository.tracks.TracksRepository
import io.reactivex.Single

interface TracksUseCase {
    fun getTracks(): Single<List<Track>>
}

class TracksUseCaseImpl(
    private val tracksRepository: TracksRepository
) : TracksUseCase {

    override fun getTracks() =
        tracksRepository.getTracks()
}