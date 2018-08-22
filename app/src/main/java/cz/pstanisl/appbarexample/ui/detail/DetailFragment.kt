package cz.pstanisl.appbarexample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import cz.pstanisl.appbarexample.R
import cz.pstanisl.appbarexample.di.Injectable
import cz.pstanisl.appbarexample.model.Message
import cz.pstanisl.appbarexample.ui.shared.ChildFragment
import cz.pstanisl.appbarexample.ui.shared.snack
import cz.pstanisl.appbarexample.vo.Resource
import kotlinx.android.synthetic.main.fragment_detail.*
import timber.log.Timber
import javax.inject.Inject

class DetailFragment: ChildFragment(), Injectable {

    @Inject lateinit var mViewModelFactory: ViewModelProvider.Factory

    private lateinit var mDetailViewModel: DetailViewModel

    private val mResourceObserver = Observer<Resource<Message>> {
        Timber.d("Loaded message: %s", it)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mDetailViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailViewModel::class.java)
        mDetailViewModel.resourceLiveData.observe(this, mResourceObserver)

        val id = DetailFragmentArgs.fromBundle(arguments).id
        mDetailViewModel.getMessage(id)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mDetailViewModel.resourceLiveData.removeObservers(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        btnDetailShowSnackbar.setOnClickListener {
            val id = DetailFragmentArgs.fromBundle(arguments).id
            detailContainer.snack(id)
        }
    }

}