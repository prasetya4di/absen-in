package id.ac.stiki.doleno.absenin.domain.impl;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import id.ac.stiki.doleno.absenin.domain.GetEventParticipant;
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository;

public class GetEventParticipantImpl implements GetEventParticipant {
    private final EventParticipantRepository repository;

    public GetEventParticipantImpl(EventParticipantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task<QuerySnapshot> execute(int eventId) {
        return repository.get(eventId);
    }
}
