package cz.pstanisl.appbarexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cz.pstanisl.appbarexample.ui.BottomNavigationFragment
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    private fun setup() {
        bottomBar.setNavigationOnClickListener {
            Timber.d("Showing navigation")
            val navigationFragment = BottomNavigationFragment()
            navigationFragment.show(supportFragmentManager, navigationFragment.tag)
        }
    }
}
