package id.ac.stiki.doleno.absenin.domain.impl;

import android.os.AsyncTask;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.domain.FetchAllEvent;
import id.ac.stiki.doleno.absenin.repository.EventRepository;

public class FetchAllEventImpl implements FetchAllEvent {
    private final EventRepository eventRepository;

    public FetchAllEventImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Task<QuerySnapshot> execute() {
        return eventRepository.get()
                .addOnSuccessListener(querySnapshots -> AsyncTask.execute(() -> {
                    List<Event> listEvent = new ArrayList<>();
                    querySnapshots.getDocuments().forEach(document -> listEvent.add(new Event(document.getData())));
                    eventRepository.create(listEvent);
                }));
    }
}
