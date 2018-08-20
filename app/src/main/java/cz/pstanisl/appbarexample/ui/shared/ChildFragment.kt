package cz.pstanisl.appbarexample.ui.shared

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import cz.pstanisl.appbarexample.R
import kotlinx.android.synthetic.main.activity_main.*

open class ChildFragment : Fragment() {

    override fun onDestroy() {
        super.onDestroy()
//        activity!!.toolbar.title = ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val toolbar = activity!!.toolbar
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_grey600_24dp)
//        toolbar.setNavigationOnClickListener {
//            Navigation.findNavController(view).popBackStack()
//            // Navigate back to the previous item in the backstack
////            view.findNavController().popBackStack()
//        }
    }

//    fun setTitle(id: Int) {
//        activity!!.toolbar.setTitle(id)
//    }

}
