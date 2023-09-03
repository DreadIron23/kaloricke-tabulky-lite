package janmokry.kaloricketabulkylite.core.ui

import android.content.Context

fun Context.dpToPx(dp: Int): Float = (dp * this.resources.displayMetrics.density)