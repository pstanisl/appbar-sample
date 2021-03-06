package cz.pstanisl.appbarexample.ui.inbox

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import cz.pstanisl.appbarexample.R
import cz.pstanisl.appbarexample.model.Message
import cz.pstanisl.appbarexample.ui.shared.AutoUpdatableAdapter
import cz.pstanisl.appbarexample.ui.shared.clearUrl
import cz.pstanisl.appbarexample.ui.shared.inflate
import cz.pstanisl.appbarexample.ui.shared.loadUrl
import kotlinx.android.synthetic.main.item_message.view.*
import kotlin.properties.Delegates


class InboxAdapter constructor(private val listener: InboxAdapterListener): RecyclerView.Adapter<InboxAdapter.InboxViewHolder>(), AutoUpdatableAdapter {

    var items: List<Message> by Delegates.observable(emptyList()) {
        _, old, new -> autoNotify(old, new) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxViewHolder {
        return InboxViewHolder(parent.inflate(R.layout.item_message))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: InboxViewHolder, position: Int) {
        holder.applyClickEvents(items[position], listener)
        holder.bind(items[position])
    }

    class InboxViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun applyClickEvents(msg: Message, listener: InboxAdapterListener) = with(itemView) {
            setOnClickListener { listener.onMessageClick(msg.id) }
        }

        fun bind(msg: Message) = with(itemView) {
            from.text = msg.from
            subject.text = msg.subject
            message.text = msg.message
            timestamp.text = msg.timestamp
            // Favorite icon and color
            val favoriteIconResId = if (msg.isImportant) R.drawable.ic_star_black_24dp else R.drawable.ic_star_border_grey600_24dp
            val favoriteColorId = if (msg.isImportant) R.color.favorite else R.color.google_grey600
            favorite.setImageResource(favoriteIconResId)
            DrawableCompat.setTint(favorite.drawable, ContextCompat.getColor(context, favoriteColorId))
            // Mark unread messages
            if (!msg.isRead) {
                from.typeface = Typeface.DEFAULT_BOLD
                subject.typeface = Typeface.DEFAULT_BOLD
            }
            // Set avatar
            setAvatar(msg)
        }

        private fun setAvatar(msg: Message) = with(itemView) {
            if (msg.picture.isBlank()) {
                // Avatar icon and text
                setAvatarBackground(msg.color)
                avatarText.text = msg.from.substring(0, 1)
                avatarText.visibility = View.VISIBLE
                // Clear loading, handle problem where view is reusable with RecyclerView
                // and ImageView contains wrong image.
                avatar.clearUrl()
            } else {
                avatar.loadUrl(msg.picture)
                setAvatarBackground(Color.TRANSPARENT)
                avatarText.visibility = View.GONE
            }
        }

        private fun setAvatarBackground(color: Int) = with(itemView) {
            when (avatar.background) {
                is ColorDrawable -> (avatar.background as ColorDrawable).color = color
                is GradientDrawable -> (avatar.background as GradientDrawable).setColor(color)
                is ShapeDrawable -> (avatar.background as ShapeDrawable).paint.color = color
            }
        }

    }

    interface InboxAdapterListener {
        fun onMessageClick(id: Int)
    }
}