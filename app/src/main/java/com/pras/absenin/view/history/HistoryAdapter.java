package com.pras.absenin.view.history;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pras.absenin.data.entity.Absent;
import com.pras.absenin.databinding.ItemHistoryBinding;

import java.text.SimpleDateFormat;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private final List<Absent> absentList;
    private final HistoryAdapterCallback historyAdapterCallback;

    public HistoryAdapter(List<Absent> absentList, HistoryAdapterCallback historyAdapterCallback) {
        this.absentList = absentList;
        this.historyAdapterCallback = historyAdapterCallback;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        Absent absent = absentList.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String formattedDate = dateFormat.format(absent.eventDate);
        holder.binding.eventTitle.setText(absent.eventTitle);
        holder.binding.eventDescription.setText(absent.eventDescription);
        holder.binding.eventDate.setText(formattedDate);
        holder.binding.cardAbsent.setOnClickListener(v -> {
            historyAdapterCallback.onClick(absent);
        });
    }

    @Override
    public int getItemCount() {
        return absentList.size();
    }

    interface HistoryAdapterCallback {
        void onClick(Absent absent);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemHistoryBinding binding;

        public ViewHolder(ItemHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
