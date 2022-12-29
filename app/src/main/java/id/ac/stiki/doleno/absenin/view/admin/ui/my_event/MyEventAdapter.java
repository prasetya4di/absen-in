package id.ac.stiki.doleno.absenin.view.admin.ui.my_event;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.databinding.ItemMyEventBinding;

public class MyEventAdapter extends RecyclerView.Adapter<MyEventAdapter.ViewHolder> {
    private final List<Event> eventList;
    private final MyEventAdapterCallback callback;

    public MyEventAdapter(List<Event> eventList, MyEventAdapterCallback callback) {
        this.eventList = eventList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MyEventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemMyEventBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyEventAdapter.ViewHolder holder, int position) {
        Event event = eventList.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String formattedDate = dateFormat.format(event.eventDate);
        holder.binding.eventTitle.setText(event.eventTitle);
        holder.binding.eventDescription.setText(event.eventDescription);
        holder.binding.eventDate.setText(formattedDate);
        holder.binding.cardEvent.setOnClickListener(v -> {
            callback.onClick(event);
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    interface MyEventAdapterCallback {
        void onClick(Event event);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemMyEventBinding binding;

        public ViewHolder(ItemMyEventBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
