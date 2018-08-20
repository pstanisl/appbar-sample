package cz.pstanisl.appbarexample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import cz.pstanisl.appbarexample.R
import cz.pstanisl.appbarexample.ui.shared.ChildFragment
import cz.pstanisl.appbarexample.ui.shared.snack
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment: ChildFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        btnDetailShowSnackbar.setOnClickListener {
            val detailId = DetailFragmentArgs.fromBundle(arguments).detailId
            detailContainer.snack(detailId)
        }
    }

}