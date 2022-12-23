package id.ac.stiki.doleno.absenin.domain;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public interface DoLogin {
    Task<DocumentSnapshot> execute(String email, String password);
}
