package id.ac.stiki.doleno.absenin.domain.impl;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;
import id.ac.stiki.doleno.absenin.domain.ReadEventParticipant;
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository;

public class ReadEventParticipantImpl implements ReadEventParticipant {
    private final EventParticipantRepository repository;

    public ReadEventParticipantImpl(EventParticipantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EventParticipant> execute(int eventId) {
        return repository.read(eventId);
    }
}
