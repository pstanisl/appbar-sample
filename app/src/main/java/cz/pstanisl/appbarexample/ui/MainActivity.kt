package cz.pstanisl.appbarexample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import androidx.navigation.findNavController
import cz.pstanisl.appbarexample.R
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    override fun onSupportNavigateUp() = findNavController(R.id.appbar_sample_nav_host_fragment).navigateUp()

    private fun setup() {
        bottomBar.setNavigationOnClickListener {
            val navigationFragment = BottomNavigationFragment()
            navigationFragment.show(supportFragmentManager, navigationFragment.tag)
        }
    }
}
