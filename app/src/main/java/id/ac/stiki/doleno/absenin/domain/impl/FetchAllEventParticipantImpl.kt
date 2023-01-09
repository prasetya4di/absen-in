package id.ac.stiki.doleno.absenin.domain.impl;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;
import id.ac.stiki.doleno.absenin.domain.FetchAllEventParticipant;
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository;

public class FetchAllEventParticipantImpl implements FetchAllEventParticipant {
    private final EventParticipantRepository eventParticipantRepository;

    public FetchAllEventParticipantImpl(EventParticipantRepository eventParticipantRepository) {
        this.eventParticipantRepository = eventParticipantRepository;
    }

    @Override
    public Task<QuerySnapshot> execute(int eventId) {
        return eventParticipantRepository.get(eventId)
                .addOnSuccessListener(result -> {
                    List<EventParticipant> eventParticipants = new ArrayList<>();
                    result.getDocuments().forEach(document -> eventParticipants.add(new EventParticipant(document.getData())));
                    eventParticipantRepository.create(eventParticipants);
                });
    }
}
