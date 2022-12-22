package id.ac.stiki.doleno.absenin.domain;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;

public interface RegisterEvent {
    Task<DocumentReference> execute(EventParticipant eventParticipant);
}
