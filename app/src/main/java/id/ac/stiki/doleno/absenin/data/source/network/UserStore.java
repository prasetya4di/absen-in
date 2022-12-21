package id.ac.stiki.doleno.absenin.data.source.network;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import id.ac.stiki.doleno.absenin.data.entity.User;

public interface UserStore {
    Task<Void> createUser(User user);

    Task<Void> updateUser(User user);

    Task<DocumentSnapshot> getUser(String email);
}
