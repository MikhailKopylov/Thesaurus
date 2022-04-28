package ru.amk.translate.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.amk.core.model.data_source.RoomHistory
import ru.amk.translate.R

class HistoryAdapter(
    private var data: List<RoomHistory>
) :
    RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {
    fun setData(data: List<RoomHistory>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
        RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_history, parent, false) as
                View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data.get(position).searchWord)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: String) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.history_textview_recycler_item).text = data
            }
        }
    }
}
