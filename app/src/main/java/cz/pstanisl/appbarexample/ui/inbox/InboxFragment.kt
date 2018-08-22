package cz.pstanisl.appbarexample.ui.inbox


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cz.pstanisl.appbarexample.R
import cz.pstanisl.appbarexample.di.Injectable
import cz.pstanisl.appbarexample.model.Message
import cz.pstanisl.appbarexample.ui.shared.DividerItemDecoration
import cz.pstanisl.appbarexample.ui.shared.snack
import cz.pstanisl.appbarexample.ui.shared.toast
import cz.pstanisl.appbarexample.vo.Resource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_inbox.*
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InboxFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class InboxFragment : Fragment(), Injectable, SwipeRefreshLayout.OnRefreshListener {
        // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    @Inject lateinit var mViewModelFactory: ViewModelProvider.Factory

    private lateinit var mInboxViewModel: InboxViewModel
    private lateinit var mInboxAdapter: InboxAdapter


    private val mInboxAdapterListener = object : InboxAdapter.InboxAdapterListener {

        override fun onMessageClick(id: Int) {
            val action = InboxFragmentDirections.actionDashboardToDetailFragment(id)
            Navigation.findNavController(view!!).navigate(action)
        }

    }

    private val mStateObserver = Observer<Resource<List<Message>>> {
        Timber.d("Loaded data $it")
        when (it) {
            is Resource.Loading -> swlInboxContainer.isRefreshing = true
            is Resource.Success -> {
                mInboxAdapter.items = it.data!!
                swlInboxContainer.isRefreshing = false
                if (it.data.isEmpty()) {
                    showError(IllegalStateException("Empty data"))
                }
            }
            is Resource.Failure -> {
                showError(it.e)
                swlInboxContainer.isRefreshing = false
            }
        }
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

        mInboxViewModel.getInbox(false)
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
            true
        }
        // Register refresh listener, after refresh is initialized messages are fetched.
        swlInboxContainer.setOnRefreshListener(this)
        // Create and set an adapter and the recycler view for the messages
        mInboxAdapter = InboxAdapter(mInboxAdapterListener)
        rvInbox.layoutManager = LinearLayoutManager(context)
//        rvInbox.itemAnimator
        rvInbox.addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
        rvInbox.adapter = mInboxAdapter
    }

    override fun onRefresh() {
        // Refresh requested, fetch the data
        mInboxViewModel.getInbox(true)
    }

    private fun showError(error: Throwable) {
        val message = when (error) {
            is SocketTimeoutException -> getString(R.string.error_message_data_error_timeout)
            is UnknownHostException -> getString(R.string.error_message_data_error_host)
            else -> error.localizedMessage ?: error.toString()
        }

        inboxContainer.snack(message)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InboxFragment.
         */
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//                InboxFragment().apply {
//                    arguments = Bundle().apply {
//                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
//                    }
//                }
    }
}
