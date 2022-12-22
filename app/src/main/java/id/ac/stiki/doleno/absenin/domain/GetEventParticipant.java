package id.ac.stiki.doleno.absenin.domain;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

public interface GetEventParticipant {
    Task<QuerySnapshot> execute(int eventId);
}
