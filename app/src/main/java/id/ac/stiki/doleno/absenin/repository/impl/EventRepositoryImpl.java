package id.ac.stiki.doleno.absenin.repository.impl;

import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.data.source.local.EventDao;
import id.ac.stiki.doleno.absenin.data.source.network.EventStore;
import id.ac.stiki.doleno.absenin.repository.EventRepository;

public class EventRepositoryImpl implements EventRepository {
    private final EventDao eventDao;
    private final EventStore eventStore;

    public EventRepositoryImpl(EventDao eventDao, EventStore eventStore) {
        this.eventDao = eventDao;
        this.eventStore = eventStore;
    }

    @Override
    public void create(Event event) {
        eventDao.insert(event);
    }

    @Override
    public void create(List<Event> event) {
        eventDao.insert(event);
    }

    @Override
    public LiveData<List<Event>> read() {
        return eventDao.getAll();
    }

    @Override
    public Event readById(int id) {
        return eventDao.getById(id);
    }

    @Override
    public void delete() {
        eventDao.delete();
    }

    @Override
    public Task<Void> post(Event event) {
        return eventStore.createEvent(event);
    }

    @Override
    public Task<QuerySnapshot> get() {
        return eventStore.getAllEvent();
    }

    @Override
    public Task<QuerySnapshot> getActiveEvent() {
        return eventStore.getAllActiveEvent();
    }
}
