package id.ac.stiki.doleno.absenin.data.source.network.impl;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import id.ac.stiki.doleno.absenin.data.database.table.Table;
import id.ac.stiki.doleno.absenin.data.entity.User;
import id.ac.stiki.doleno.absenin.data.source.network.UserStore;

public class UserStoreImpl implements UserStore {
    private final FirebaseFirestore firestore;

    public UserStoreImpl(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public Task<Void> createUser(User user) {
        return getCollection()
                .document(user.getDocumentPath())
                .set(user.toMap());
    }

    @Override
    public Task<Void> updateUser(User user) {
        return getCollection()
                .document(user.getDocumentPath())
                .set(user.toMap());
    }

    @Override
    public Task<DocumentSnapshot> getUser(String email) {
        return getCollection().document("user-" + email).get();
    }

    private CollectionReference getCollection() {
        return firestore.collection(Table.USERS.getText());
    }
}
