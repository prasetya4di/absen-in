package id.ac.stiki.doleno.absenin.di;

import com.google.firebase.firestore.FirebaseFirestore;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import id.ac.stiki.doleno.absenin.data.source.network.AbsentStore;
import id.ac.stiki.doleno.absenin.data.source.network.EventStore;
import id.ac.stiki.doleno.absenin.data.source.network.UserStore;
import id.ac.stiki.doleno.absenin.data.source.network.impl.AbsentStoreImpl;
import id.ac.stiki.doleno.absenin.data.source.network.impl.EventStoreImpl;
import id.ac.stiki.doleno.absenin.data.source.network.impl.UserStoreImpl;

@Module
@InstallIn(ViewModelComponent.class)
public class FirestoreModule {
    @Provides
    public static FirebaseFirestore provideFireStore() {
        return FirebaseFirestore.getInstance();
    }

    @Provides
    public UserStore provideUserStore(FirebaseFirestore firestore) {
        return new UserStoreImpl(firestore);
    }

    @Provides
    public EventStore provideEventStore(FirebaseFirestore firestore) {
        return new EventStoreImpl(firestore) {
        };
    }

    @Provides
    public AbsentStore provideAbsentStore(FirebaseFirestore firestore) {
        return new AbsentStoreImpl(firestore);
    }
}
