package cz.pstanisl.appbarexample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cz.pstanisl.appbarexample.R
import kotlinx.android.synthetic.main.fragment_navigation.*

class BottomNavigationFragment: RoundedBottomSheetDialogFragment() {

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
    }

}