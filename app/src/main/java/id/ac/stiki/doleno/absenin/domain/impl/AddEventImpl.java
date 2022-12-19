package id.ac.stiki.doleno.absenin.domain.impl;

import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.domain.AddEvent;
import id.ac.stiki.doleno.absenin.repository.EventRepository;

public class AddEventImpl implements AddEvent {
    private final EventRepository repository;

    public AddEventImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Event event) {
        repository.newEvent(event);
    }
}
