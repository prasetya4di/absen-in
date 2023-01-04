package id.ac.stiki.doleno.absenin.view.participant.ui.my_event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.databinding.ItemParticipantEventBinding
import java.text.SimpleDateFormat

class MyEventAdapter(
    private val absents: List<Absent>,
    private val callback: MyEventAdapterCallback
) : RecyclerView.Adapter<MyEventAdapter.ViewHolder>() {
    interface MyEventAdapterCallback {
        fun onClick(absent: Absent)
    }

    inner class ViewHolder(val binding: ItemParticipantEventBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemParticipantEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val absent: Absent = absents[position]
        val dateFormat = SimpleDateFormat("dd MMMM yyyy")
        val formattedDate = dateFormat.format(absent.absentDate)
        holder.binding.eventTitle.text = absent.absentTitle
        holder.binding.eventDescription.text = absent.absentDescription
        holder.binding.eventDate.text = formattedDate
        holder.binding.cardEvent.setOnClickListener { callback.onClick(absent) }
    }

    override fun getItemCount(): Int = absents.size
}