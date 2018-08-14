package cz.pstanisl.appbarexample.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import cz.pstanisl.appbarexample.R
import cz.pstanisl.appbarexample.ui.shared.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*
import timber.log.Timber

class DetailFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailId = DetailFragmentArgs.fromBundle(arguments).detailId
        tvDetail.text = detailId

        val parentActivity = activity as MainActivity
        val toolbar = parentActivity.toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_grey600_24dp)
        toolbar.setNavigationOnClickListener {
            // Navigate back to the previous item in the backstack
            view.findNavController().popBackStack()
        }

    }

}