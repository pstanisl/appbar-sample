package cz.pstanisl.appbarexample.ui.shared

import android.support.v4.app.Fragment
import android.widget.Toast

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, message, duration).show()
}
