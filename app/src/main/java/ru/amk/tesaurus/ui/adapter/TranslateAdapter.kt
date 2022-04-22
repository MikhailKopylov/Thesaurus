package ru.amk.tesaurus.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.LoadRequest
import ru.amk.tesaurus.R
import ru.amk.tesaurus.model.network.data.DataModel

class TranslateAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>
) :
    RecyclerView.Adapter<TranslateAdapter.RecyclerItemViewHolder>() {
    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
        RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_translate, parent, false) as
                View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.header_textview_recycler_item).text = data.text
                itemView.findViewById<TextView>(R.id.description_textview_recycler_item).text =
                    data.meanings?.get(0)?.translation?.translation
                itemView.setOnClickListener { openInNewWindow(data) }
                loadImage(
                    itemView.findViewById<ImageView>(R.id.imageview_recycler_item),
                    data.meanings?.get(0)?.imageUrl
                )
            }
        }

        private fun loadImage(imageView: ImageView?, imageUrl: String?) {
            imageView?.let { imageView ->
                val request = LoadRequest.Builder(imageView.context)
                    .data("https:$imageUrl")
                    .target(
                        onStart = {},
                        onSuccess = { drawable ->
                            imageView.setImageDrawable(drawable)
                        },
                        onError = { imageView.setImageResource(com.google.android.material.R.drawable.mtrl_ic_error) }
                    )
                    .build()
                ImageLoader(imageView.context).execute(request)
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}
