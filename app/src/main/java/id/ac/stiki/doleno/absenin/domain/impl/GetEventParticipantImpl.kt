package id.ac.stiki.doleno.absenin.domain.impl;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;
import id.ac.stiki.doleno.absenin.domain.GetEventParticipant;
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository;

public class GetEventParticipantImpl implements GetEventParticipant {
    private final EventParticipantRepository repository;

    public GetEventParticipantImpl(EventParticipantRepository repository) {
        this.repository = repository;
    }

    @Override
    public LiveData<List<EventParticipant>> execute(int eventId) {
        return repository.read(eventId);
    }
}
