package id.ac.stiki.doleno.absenin.data.source.network;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QuerySnapshot;

import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;

public interface EventParticipantStore {
    Task<DocumentReference> create(EventParticipant eventParticipant);

    Task<QuerySnapshot> get(Long eventId);

    Task<AggregateQuerySnapshot> count(Event event, String email);
}
