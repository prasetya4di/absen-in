package id.ac.stiki.doleno.absenin.domain.impl;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;
import id.ac.stiki.doleno.absenin.domain.RegisterEvent;
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository;

public class RegisterEventImpl implements RegisterEvent {
    private final EventParticipantRepository repository;

    public RegisterEventImpl(EventParticipantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task<DocumentReference> execute(EventParticipant eventParticipant) {
        return repository.post(eventParticipant)
                .addOnSuccessListener(result -> {
                    repository.create(eventParticipant);
                });
    }
}
