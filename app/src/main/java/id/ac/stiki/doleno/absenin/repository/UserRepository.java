package id.ac.stiki.doleno.absenin.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import id.ac.stiki.doleno.absenin.data.entity.User;

public interface UserRepository {
    void create(User user);

    void update(User user);

    User read();

    Task<Void> post(User user);

    Task<Void> put(User user);

    Task<DocumentSnapshot> get();
}
