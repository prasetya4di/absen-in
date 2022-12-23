package id.ac.stiki.doleno.absenin.data.source.network.impl;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import id.ac.stiki.doleno.absenin.data.database.table.Table;
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;
import id.ac.stiki.doleno.absenin.data.source.network.EventParticipantStore;

public class EventParticipantStoreImpl implements EventParticipantStore {
    private final FirebaseFirestore firestore;

    public EventParticipantStoreImpl(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public Task<DocumentReference> create(EventParticipant eventParticipant) {
        return getCollection(eventParticipant.eventId)
                .add(eventParticipant.toMap());
    }

    @Override
    public Task<QuerySnapshot> get(int eventId) {
        return null;
    }

    private CollectionReference getCollection(int eventId) {
        return firestore.collection(Table.EVENT_PARTICIPANT.getText() + eventId);
    }
}