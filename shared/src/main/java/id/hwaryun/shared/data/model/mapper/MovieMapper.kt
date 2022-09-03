package id.hwaryun.shared.data.model.mapper

import id.hwaryun.shared.data.model.response.MovieResponse
import id.hwaryun.shared.data.model.viewparam.MovieViewParam
import id.hwaryun.shared.utils.mapper.ViewParamMapper

object MovieMapper : ViewParamMapper<MovieResponse, MovieViewParam> {

    override fun toViewParam(dataObject: MovieResponse?): MovieViewParam = MovieViewParam(
        cast = dataObject?.cast.orEmpty(),
        category = dataObject?.category.orEmpty(),
        director = dataObject?.director.orEmpty(),
        filmRate = dataObject?.filmRate.orEmpty(),
        id = dataObject?.id ?: -1,
        isUserWatchlist = dataObject?.isUserWatchlist ?: false,
        overview = dataObject?.overview.orEmpty(),
        posterUrl = dataObject?.posterUrl.orEmpty(),
        releaseDate = dataObject?.releaseDate.orEmpty(),
        runtime = dataObject?.runtime ?: -1,
        title = dataObject?.title.orEmpty(),
        trailerUrl = dataObject?.trailerUrl.orEmpty(),
        videoUrl = dataObject?.videoUrl.orEmpty()
    )
}