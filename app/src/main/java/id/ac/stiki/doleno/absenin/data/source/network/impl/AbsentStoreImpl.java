package id.ac.stiki.doleno.absenin.data.source.network.impl;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import id.ac.stiki.doleno.absenin.data.database.table.Table;
import id.ac.stiki.doleno.absenin.data.entity.Absent;
import id.ac.stiki.doleno.absenin.data.source.network.AbsentStore;

public class AbsentStoreImpl implements AbsentStore {
    private final FirebaseFirestore firestore;

    public AbsentStoreImpl(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public Task<Void> createAbsent(Absent absent, String email) {
        return getCollection(email).document(absent.getDocumentPath()).set(absent.toMap());
    }

    @Override
    public Task<Void> updateAbsent(Absent absent, String email) {
        return getCollection(email).document(absent.getDocumentPath()).set(absent.toMap());
    }

    @Override
    public Task<QuerySnapshot> get(String email) {
        return getCollection(email).get();
    }

    private CollectionReference getCollection(String email) {
        return firestore.collection(Table.ABSENT.getText() + "_" + email);
    }
}
