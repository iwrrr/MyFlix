package id.hwaryun.shared.utils

import android.content.Context
import android.content.Intent
import id.hwaryun.shared.data.model.viewparam.MovieViewParam
import id.hwaryun.styling.ProjectDrawable

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object CommonUtils {

    fun getWatchlistIcon(isUserWatchlist: Boolean): Int {
        return if (isUserWatchlist) ProjectDrawable.ic_check else ProjectDrawable.ic_add
    }

    fun shareFilm(context: Context, movieViewParam: MovieViewParam) {
        val shareIntent = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Watch this ! ${movieViewParam.title} ${movieViewParam.posterUrl}"
            )
            type = "text/plain"
        }, null)
        context.startActivity(shareIntent)
    }
}