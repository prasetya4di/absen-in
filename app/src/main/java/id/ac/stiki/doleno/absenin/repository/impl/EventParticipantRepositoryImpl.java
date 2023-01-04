package id.ac.stiki.doleno.absenin.repository.impl;

import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;
import id.ac.stiki.doleno.absenin.data.source.local.EventParticipantDao;
import id.ac.stiki.doleno.absenin.data.source.network.EventParticipantStore;
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository;

public class EventParticipantRepositoryImpl implements EventParticipantRepository {
    private final EventParticipantDao eventParticipantDao;
    private final EventParticipantStore eventParticipantStore;

    public EventParticipantRepositoryImpl(EventParticipantDao eventParticipantDao, EventParticipantStore eventParticipantStore) {
        this.eventParticipantDao = eventParticipantDao;
        this.eventParticipantStore = eventParticipantStore;
    }

    @Override
    public LiveData<List<EventParticipant>> read(int eventId) {
        return eventParticipantDao.getAll(eventId);
    }

    @Override
    public void create(EventParticipant eventParticipant) {
        eventParticipantDao.insert(eventParticipant);
    }

    @Override
    public void create(List<EventParticipant> eventParticipant) {
        eventParticipantDao.insert(eventParticipant);
    }

    @Override
    public Task<DocumentReference> post(EventParticipant eventParticipant) {
        return eventParticipantStore.create(eventParticipant);
    }

    @Override
    public Task<QuerySnapshot> get(int eventId) {
        return eventParticipantStore.get(eventId);
    }

    @Override
    public Long count(Event event, String email) {
        return eventParticipantStore.count(event, email);
    }
}
