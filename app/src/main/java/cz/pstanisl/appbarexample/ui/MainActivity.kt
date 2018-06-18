package cz.pstanisl.appbarexample.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cz.pstanisl.appbarexample.R
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    private fun setup() {
        bottomBar.replaceMenu(R.menu.bottomappbar_menu)
        bottomBar.setOnMenuItemClickListener { item ->
            Timber.d("BottomBar menu item clicked: %s", item.itemId)
            when (item.itemId) {
                R.id.app_bar_fav -> toast("Fav menu item is clicked!")
                R.id.app_bar_search -> toast("Search menu item is clicked!")
                R.id.app_bar_settings -> toast("Settings menu item is clicked!")
            }
            true
        }
        bottomBar.setNavigationOnClickListener {
            Timber.d("Showing navigation")
//            drawerLayout.openDrawer(Gravity.BOTTOM)
            val navigationFragment = BottomNavigationFragment()
            navigationFragment.show(supportFragmentManager, navigationFragment.tag)
        }
    }

    private fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}
