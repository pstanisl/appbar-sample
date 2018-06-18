package cz.pstanisl.appbarexample.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import cz.pstanisl.appbarexample.R
import cz.pstanisl.appbarexample.ui.shared.RoundedBottomSheetDialogFragment
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
        // Setup navigation component
        val navController = Navigation.findNavController(activity as Activity, R.id.appbar_sample_nav_host_fragment)
        // Hide NavigationView after destination change
        navController.addOnNavigatedListener { _, _ ->
            if (this.isVisible) {
                this.dismiss()
            }
        }
        // Connect NavigationView and NavController
        vNavigation.setupWithNavController(navController)
    }



}