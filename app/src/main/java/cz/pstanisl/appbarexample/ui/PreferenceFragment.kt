package cz.pstanisl.appbarexample.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceFragmentCompat
import cz.pstanisl.appbarexample.R
import kotlinx.android.synthetic.main.activity_main.*

class PreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.sharedPreferencesName = "general_prefs"
        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.preferences_main)
    }

    override fun onDestroy() {
        super.onDestroy()

        activity!!.toolbar.title = ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = activity!!.toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_grey600_24dp)
        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).popBackStack()
            // Navigate back to the previous item in the backstack
//            view.findNavController().popBackStack()
        }
        toolbar.setTitle(R.string.title_preferences)
    }

}