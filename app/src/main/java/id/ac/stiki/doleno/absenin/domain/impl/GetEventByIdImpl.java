package id.ac.stiki.doleno.absenin.domain.impl;

import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.domain.GetEventById;
import id.ac.stiki.doleno.absenin.repository.EventRepository;

public class GetEventByIdImpl implements GetEventById {
    private final EventRepository eventRepository;

    public GetEventByIdImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event execute(int id) {
        return eventRepository.getEventById(id);
    }
}
