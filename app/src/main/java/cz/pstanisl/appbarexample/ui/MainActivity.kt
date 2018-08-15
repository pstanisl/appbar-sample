package cz.pstanisl.appbarexample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
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
        // Handle navigation changes (e.g. hide bottom bar)
        val navController = findNavController(R.id.appbar_sample_nav_host_fragment)
        navController.addOnNavigatedListener { controller, destination ->
            when (destination.id) {
                R.id.detail, R.id.preference -> changeAppBarVisibility(false)
                else -> changeAppBarVisibility(true)
            }
        }

    }

    private fun changeAppBarVisibility(visible: Boolean) {
        Timber.d("Changing appbar visibility: %s", visible)
        bottomBar.visibility = if (visible) View.VISIBLE else View.GONE
        bottomBarShadow.visibility = if (visible) View.VISIBLE else View.GONE
        toolbar.visibility = if (visible) View.GONE else View.VISIBLE
    }
}
