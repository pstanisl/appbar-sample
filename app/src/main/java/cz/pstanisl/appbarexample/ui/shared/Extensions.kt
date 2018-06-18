package cz.pstanisl.appbarexample.ui.shared

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, message, duration).show()
}