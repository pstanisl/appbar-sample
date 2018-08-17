package cz.pstanisl.appbarexample.ui.inbox


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import cz.pstanisl.appbarexample.R
import cz.pstanisl.appbarexample.di.Injectable
import cz.pstanisl.appbarexample.model.Message
import cz.pstanisl.appbarexample.ui.shared.snack
import cz.pstanisl.appbarexample.ui.shared.toast
import cz.pstanisl.appbarexample.vo.Resource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_inbox.*
import timber.log.Timber
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DashboardFragment : Fragment(), Injectable {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    @Inject lateinit var mViewModelFactory: ViewModelProvider.Factory

    private lateinit var mInboxViewModel: InboxViewModel

    private val mStateObserver = Observer<Resource<List<Message>>> {
        Timber.d("Loaded data $it")
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mInboxViewModel = ViewModelProviders.of(this, mViewModelFactory).get(InboxViewModel::class.java)
        mInboxViewModel.resourceLiveData.observe(this, mStateObserver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inbox, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mInboxViewModel.resourceLiveData.removeObserver(mStateObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.bottomBar.replaceMenu(R.menu.dashboard_menu)
        activity!!.bottomBar.setOnMenuItemClickListener { item ->
            Timber.d("BottomBar menu item clicked: %s", item.itemId)
            when (item.itemId) {
                R.id.app_bar_settings -> toast("Settings menu item is clicked!")
            }
            mInboxViewModel.getInbox()
            true
        }

        btnShowDetail.setOnClickListener {
            // Send data to the destination fragment
//            val action = DashboardFragmentDirections.actionDashboardToDetailFragment("testId")
//            view.findNavController().navigate(action)
//            mInboxViewModel.getInbox()
            inboxContainer.snack("test") {}
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//                DashboardFragment().apply {
//                    arguments = Bundle().apply {
//                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
//                    }
//                }
    }
}