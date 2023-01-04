package id.ac.stiki.doleno.absenin.view.participant.ui.all_event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.databinding.ItemAllEventBinding
import java.text.SimpleDateFormat

class AllEventAdapter(
    private val events: List<Event>,
    private val callback: AllEventAdapterCallback
) : RecyclerView.Adapter<AllEventAdapter.ViewHolder>() {
    interface AllEventAdapterCallback {
        fun onClick(event: Event)
    }

    inner class ViewHolder(val binding: ItemAllEventBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemAllEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event: Event = events[position]
        val dateFormat = SimpleDateFormat("dd MMMM yyyy")
        val formattedDate = dateFormat.format(event.eventDate)
        holder.binding.eventTitle.text = event.eventTitle
        holder.binding.eventDescription.text = event.eventDescription
        holder.binding.eventDate.text = formattedDate
        holder.binding.cardEvent.setOnClickListener { callback.onClick(event) }
    }

    override fun getItemCount(): Int = events.size
}