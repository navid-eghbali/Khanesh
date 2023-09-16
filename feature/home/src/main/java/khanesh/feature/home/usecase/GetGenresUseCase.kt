package khanesh.feature.home.usecase

import khanesh.shared.storage.daos.GenresDao
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val genresDao: GenresDao,
) {

    operator fun invoke() = genresDao.getGenres()
}
