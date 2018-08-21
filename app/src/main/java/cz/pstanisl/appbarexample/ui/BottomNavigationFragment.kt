package cz.pstanisl.appbarexample.ui

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import cz.pstanisl.appbarexample.R
import cz.pstanisl.appbarexample.ui.shared.RoundedBottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_navigation.*
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.widget.FrameLayout
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.navigation_menu.*
import timber.log.Timber

class BottomNavigationFragment: RoundedBottomSheetDialogFragment() {

    private val mNavigationListener = NavController.OnNavigatedListener { _, _ ->
        // Dismiss navigation view after navigation if it is visible
        if (this.isVisible) {
            this.dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog =  super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener {
            val d = it as BottomSheetDialog
            // Create behaviour to handle showing/hiding close button based on the slide offset
            val bottomSheet = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
            bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    // Show/Hide close button
                    vClose.visibility = if (slideOffset > 0.5) View.VISIBLE else View.GONE
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        dismiss()
                    }
                }

            })
            // Show/Hide close button if necessary
            vClose.visibility = if (bottomSheetBehavior.peekHeight > 0.5) View.VISIBLE else View.GONE
            // Fully expand the dialog if the device is in portrait mode
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
        }

        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        // Hide BottomSheet after click on the close button
        vClose.setOnClickListener { dismiss() }
        // Register listeners to show WWW page after click on the item
        tvPrivacyPolicy.setOnClickListener { openUri("https://www.google.cz") }
        tvFAQ.setOnClickListener { openUri("https://www.seznam.cz") }
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        Timber.d("onDismiss: %s", activity)
        if (activity == null) {
            return
        }
        val navController = Navigation.findNavController(activity as Activity, R.id.appbar_sample_nav_host_fragment)
        navController.removeOnNavigatedListener(mNavigationListener)
    }

    private fun setup() {
        // Setup lateral_navigation component
        val navController = Navigation.findNavController(activity as Activity, R.id.appbar_sample_nav_host_fragment)
        // Hide NavigationView after destination change
        navController.addOnNavigatedListener(mNavigationListener)
        // Connect NavigationView and NavController
//        vLateralNavigation.setupWithNavController(navController)
        NavigationUI.setupWithNavController(vLateralNavigation, navController)
        // Create Forward navigation items
        vForwardNavigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.preference -> navController.navigate(R.id.action_global_preference)
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun openUri(uri: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }

}