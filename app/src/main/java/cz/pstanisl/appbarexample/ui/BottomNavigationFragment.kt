package cz.pstanisl.appbarexample.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cz.pstanisl.appbarexample.R
import kotlinx.android.synthetic.main.fragment_navigation.*
import timber.log.Timber

class BottomNavigationFragment: BottomSheetDialogFragment() {

//    @SuppressLint("RestrictedApi")
//    override fun setupDialog(dialog: Dialog, style: Int) {
//        super.setupDialog(dialog, style)
//
//        val contentView = View.inflate(context, R.layout.fragment_navigation, null)
//        dialog.setContentView(contentView)
//
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        navigationContainer.setOnClickListener {
            this.dismiss()
            Toast.makeText(context, "navigation layout click", Toast.LENGTH_SHORT).show()
        }

        button1.setOnClickListener {
            this.dismiss()
            Toast.makeText(context, "button 1 click", Toast.LENGTH_SHORT).show()
        }

        button3.setOnClickListener {
            this.dismiss()
            Toast.makeText(context, "button 3 click", Toast.LENGTH_SHORT).show()
        }
    }

}