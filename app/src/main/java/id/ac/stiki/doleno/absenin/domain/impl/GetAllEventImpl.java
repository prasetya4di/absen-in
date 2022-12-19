package id.ac.stiki.doleno.absenin.domain.impl;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.domain.GetAllEvent;
import id.ac.stiki.doleno.absenin.repository.EventRepository;

public class GetAllEventImpl implements GetAllEvent {
    private final EventRepository eventRepository;

    public GetAllEventImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> execute() {
        return eventRepository.getAllEvent();
    }
}
