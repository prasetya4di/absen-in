package id.ac.stiki.doleno.absenin.view.participant.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.databinding.ItemHistoryBinding
import id.ac.stiki.doleno.absenin.util.enums.AbsentStatus.*
import java.text.SimpleDateFormat

class HistoryAdapter(
    private val absentList: List<Absent>,
    private val historyAdapterCallback: HistoryAdapterCallback
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val absent = absentList[position]
        val dateFormat = SimpleDateFormat("dd MMMM yyyy")
        val formattedDate = dateFormat.format(absent.absentDate)
        holder.binding.eventTitle.text = absent.absentTitle
        holder.binding.eventDescription.text = absent.absentDescription
        holder.binding.eventDate.text = formattedDate
        holder.binding.cardAbsent.setOnClickListener {
            historyAdapterCallback.onClick(
                absent
            )
        }
        when (absent.status) {
            REGISTERED -> {}
            DID_NOT_ATTEND -> holder.binding.txtDidNotAttend.cardViewDidNotAttend.visibility =
                View.VISIBLE
            ATTENDED -> holder.binding.txtAttend.cardViewAttend.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return absentList.size
    }

    interface HistoryAdapterCallback {
        fun onClick(absent: Absent)
    }

    inner class ViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)
}