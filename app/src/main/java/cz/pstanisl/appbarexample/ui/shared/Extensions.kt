package cz.pstanisl.appbarexample.ui.shared

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.snackbar.Snackbar
import cz.pstanisl.appbarexample.R
import cz.pstanisl.appbarexample.px

// Toast

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, message, duration).show()
}

// SnackBar

fun ViewGroup.snack(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG) {
    snack(resources.getString(messageRes), length)
}

fun ViewGroup.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    snack(message, length) {}
}

inline fun ViewGroup.snack(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    snack(resources.getString(messageRes), length, f)
}

inline fun ViewGroup.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    // NOTE: Little bit of hack to show SnackBar in the right position based on the design specification.
    // Skip if the BottomAppBar is hidden
    if (isBottomAppBarVisible(this@snack)) {
        snack.apply {
            // Place the SnackBar over the BottomAppBar
            val params = (view.layoutParams as CoordinatorLayout.LayoutParams).apply {
                anchorId = R.id.bottomBar
                anchorGravity = Gravity.TOP
                gravity = Gravity.TOP
                // Change bottom margin to show space between BottomAppBar and the SnackBar
                setMargins(leftMargin, topMargin, rightMargin, bottomMargin + 56.px)
            }

            view.layoutParams = params
        }
    }
    snack.f()
    snack.show()
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}

// BottomAppBar

fun isBottomAppBarVisible(viewGroup: ViewGroup): Boolean {
    var parent: View? = viewGroup
    var bottomAppBar: BottomAppBar?

    while (parent != null) {
        bottomAppBar = parent.findViewById(R.id.bottomBar)
        if (bottomAppBar != null) {
            return bottomAppBar.visibility == View.VISIBLE
        }

        parent = parent.parent as View
    }

    return false
}

// ImageView

fun ImageView.loadUrl(url: String) {
    GlideApp.with(context).load(url)
            .error(R.drawable.ic_help_grey600_24dp)
            .thumbnail(.5f)
            .apply(RequestOptions.circleCropTransform())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
}

fun ImageView.clearUrl() {
    GlideApp.with(context).clear(this)
}

// ViewGroup

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}