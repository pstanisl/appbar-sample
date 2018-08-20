package cz.pstanisl.appbarexample.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.preference.PreferenceFragmentCompat
import cz.pstanisl.appbarexample.R
import kotlinx.android.synthetic.main.pref_screen.*

class PreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.sharedPreferencesName = "general_prefs"
        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.preferences_main)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener {
            // Navigate back to the previous item in the backstack
            Navigation.findNavController(view).popBackStack()
        }
    }

}