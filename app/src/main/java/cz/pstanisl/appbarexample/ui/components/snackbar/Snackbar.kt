package cz.pstanisl.appbarexample.ui.components.snackbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.snackbar.BaseTransientBottomBar
import cz.pstanisl.appbarexample.R
import androidx.core.view.ViewCompat
import android.R.attr.onClick
import android.widget.Button
import android.view.ViewParent
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout




class Snackbar constructor(
        parent: ViewGroup,
        content: View,
        contentViewCallback: com.google.android.material.snackbar.ContentViewCallback
): BaseTransientBottomBar<Snackbar>(parent, content, contentViewCallback) {


    companion object {

        const val LENGTH_INDEFINITE: Int = BaseTransientBottomBar.LENGTH_INDEFINITE
        const val LENGTH_LONG: Int = BaseTransientBottomBar.LENGTH_LONG
        const val LENGTH_SHORT: Int = com.google.android.material.snackbar.Snackbar.LENGTH_SHORT

        fun make(parent: ViewGroup, charSequence: CharSequence, duration: Int): Snackbar {
            var viewGroup: ViewGroup? = null
            var view2: View? = parent
            while (view2 !is CoordinatorLayout) {
//                if (view2 is FrameLayout) {
//                    if (view2.id == 16908290) {
//                        viewGroup = view2
//                        break
//                    }
//                    viewGroup = view2
//                }
                if (view2 != null) {
                    val parent = view2.parent
                    if (parent is View) {
                        view2 = parent
                        continue
                    } else {
                        view2 = null
                        continue
                    }
                }
                if (view2 == null) {
                    break
                }
            }
            viewGroup = view2 as ViewGroup?
            if (viewGroup == null) {
                throw IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.")
            }

            // inflate custom layout
            val inflater = LayoutInflater.from(viewGroup.context)
            val content = inflater.inflate(R.layout.custom_design_layout_snackbar_include, viewGroup, false)

//            val snackbar = Snackbar(
//                    parent,
//                    LayoutInflater.from(parent.context).inflate(
//                            R.layout.custom_design_layout_snackbar_include,
//                            parent, false) as SnackbarContentLayout
//            )

            val tv = content.findViewById<TextView>(R.id.snackbar_text)
            tv.text = charSequence

            val callback = ContentViewCallback(content)

            val snackbar = Snackbar(viewGroup, content, callback)
            snackbar.duration = duration

            return snackbar
        }
    }

    // set action in custom layout
    fun setAction(text: CharSequence, listener: View.OnClickListener): Snackbar {
        val actionView = getView().findViewById<Button>(R.id.snackbar_action)
        actionView.text = text
        actionView.visibility = View.VISIBLE
        actionView.setOnClickListener { view ->
            listener.onClick(view)
            // Now dismiss the Snackbar
            dismiss()
        }
        return this
    }

    private class ContentViewCallback(// view inflated from custom layout
            private val content: View) : com.google.android.material.snackbar.ContentViewCallback {

        override fun animateContentIn(delay: Int, duration: Int) {


            // add custom *in animations for your views
            // e.g. original snackbar uses alpha animation, from 0 to 1
//            content.scaleY = 0f
            ViewCompat.setScaleY(content, 0f)
            ViewCompat.animate(content)
                    .scaleY(1f).setDuration(duration.toLong()).startDelay = delay.toLong()
        }

        override fun animateContentOut(delay: Int, duration: Int) {
            // add custom *out animations for your views
            // e.g. original snackbar uses alpha animation, from 1 to 0
//            content.scaleY = 1f
            ViewCompat.setScaleY(content, 1f)
            ViewCompat.animate(content)
                    .scaleY(0f)
                    .setDuration(duration.toLong()).startDelay = delay.toLong()
        }
    }

}