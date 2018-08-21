package cz.pstanisl.appbarexample.ui.inbox

import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import cz.pstanisl.appbarexample.R
import cz.pstanisl.appbarexample.model.Message
import cz.pstanisl.appbarexample.ui.shared.AutoUpdatableAdapter
import cz.pstanisl.appbarexample.ui.shared.inflate
import kotlinx.android.synthetic.main.item_message.view.*
import kotlin.properties.Delegates

class InboxAdapter: RecyclerView.Adapter<InboxAdapter.InboxViewHolder>(), AutoUpdatableAdapter {

    var items: List<Message> by Delegates.observable(emptyList()) {
        _, old, new -> autoNotify(old, new) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxViewHolder {
        return InboxViewHolder(parent.inflate(R.layout.item_message))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: InboxViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class InboxViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(msg: Message) = with(itemView) {
            from.text = msg.from
            subject.text = msg.subject
            message.text = msg.message
            timestamp.text = msg.timestamp

            val favoriteIconResId = if (msg.isImportant) R.drawable.ic_star_black_24dp else R.drawable.ic_star_border_grey600_24dp
            val favoriteColorId = if (msg.isImportant) R.color.favorite else R.color.google_grey600

            favorite.setImageResource(favoriteIconResId)
            DrawableCompat.setTint(favorite.drawable, ContextCompat.getColor(context, favoriteColorId))
//            favorite.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, favoriteColorId))
        }

    }

}