package id.ac.stiki.doleno.absenin.repository.impl;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.data.source.local.EventDao;
import id.ac.stiki.doleno.absenin.repository.EventRepository;

public class EventRepositoryImpl implements EventRepository {
    private final EventDao eventDao;

    public EventRepositoryImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public void newEvent(Event event) {
        eventDao.insert(event);
    }

    @Override
    public List<Event> getAllEvent() {
        return eventDao.getAll();
    }

    @Override
    public Event getEventById(int id) {
        return eventDao.getById(id);
    }
}
