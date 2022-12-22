package id.ac.stiki.doleno.absenin.domain.impl;

import com.google.android.gms.tasks.Task;

import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.domain.AddEvent;
import id.ac.stiki.doleno.absenin.repository.EventRepository;

public class AddEventImpl implements AddEvent {
    private final EventRepository repository;

    public AddEventImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task<Void> execute(Event event) {
        return repository
                .post(event)
                .addOnSuccessListener(result -> {
                    repository.create(event);
                });
    }
}
