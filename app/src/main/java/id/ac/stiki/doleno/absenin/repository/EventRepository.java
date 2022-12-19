package id.ac.stiki.doleno.absenin.repository;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;

public interface EventRepository {
    void newEvent(Event Event);

    List<Event> getAllEvent();

    Event getEventById(int id);
}
