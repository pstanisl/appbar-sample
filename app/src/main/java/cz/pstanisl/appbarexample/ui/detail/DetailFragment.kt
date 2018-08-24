package cz.pstanisl.appbarexample.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import cz.pstanisl.appbarexample.R
import cz.pstanisl.appbarexample.di.Injectable
import cz.pstanisl.appbarexample.model.Message
import cz.pstanisl.appbarexample.ui.shared.ChildFragment
import cz.pstanisl.appbarexample.ui.shared.loadUrl
import cz.pstanisl.appbarexample.vo.Resource
import kotlinx.android.synthetic.main.fragment_detail.*
import timber.log.Timber
import javax.inject.Inject

class DetailFragment: ChildFragment(), Injectable {

    @Inject lateinit var mViewModelFactory: ViewModelProvider.Factory

    private lateinit var mDetailViewModel: DetailViewModel

    private val mResourceObserver = Observer<Resource<Message>> { result ->
        when (result) {
            is Resource.Success -> fillIn(result.data!!)
            else -> Timber.d("Resource: %s", result)
        }
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

        toolbar.inflateMenu(R.menu.detail_menu)
    }

    private fun fillIn(msg: Message) {
        from.text = msg.from
        message.text = msg.message
        timestamp.text = msg.timestamp
        subject.text = msg.subject
        // Favorite icon and color
        val menuItem = toolbar.menu.findItem(R.id.detailFavorite)
        val favoriteIconResId = if (msg.isImportant) R.drawable.ic_star_black_24dp else R.drawable.ic_star_border_grey600_24dp
        val favoriteColorId = if (msg.isImportant) R.color.favorite else R.color.google_grey600
        menuItem.icon = ContextCompat.getDrawable(context!!, favoriteIconResId)
        DrawableCompat.setTint(menuItem.icon, ContextCompat.getColor(context!!, favoriteColorId))
        // Set Avatar content
        setAvatar(msg)
    }

    private fun setAvatar(msg: Message) {
        if (msg.picture.isBlank()) {
            setAvatarBackground(msg.color)
            avatarText.text = msg.from.substring(0, 1)
            avatarText.visibility = View.VISIBLE
        } else {
            avatar.loadUrl(msg.picture)
            setAvatarBackground(Color.TRANSPARENT)
            avatarText.visibility = View.GONE
        }
    }

    private fun setAvatarBackground(color: Int) {
        when (avatar.background) {
            is ColorDrawable -> (avatar.background as ColorDrawable).color = color
            is GradientDrawable -> (avatar.background as GradientDrawable).setColor(color)
            is ShapeDrawable -> (avatar.background as ShapeDrawable).paint.color = color
        }
    }

}