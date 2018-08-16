package cz.pstanisl.appbarexample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.pstanisl.appbarexample.R
import cz.pstanisl.appbarexample.ui.shared.ChildFragment
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment: ChildFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailId = DetailFragmentArgs.fromBundle(arguments).detailId
        tvDetail.text = detailId
    }

}