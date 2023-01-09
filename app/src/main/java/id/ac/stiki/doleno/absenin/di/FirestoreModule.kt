package id.ac.stiki.doleno.absenin.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.ac.stiki.doleno.absenin.data.source.network.*
import id.ac.stiki.doleno.absenin.data.source.network.impl.*

@Module
@InstallIn(ViewModelComponent::class)
class FirestoreModule {
    @Provides
    fun provideUserStore(firestore: FirebaseFirestore): UserStore {
        return UserStoreImpl(firestore)
    }

    @Provides
    fun provideEventStore(firestore: FirebaseFirestore): EventStore {
        return object : EventStoreImpl(firestore) {}
    }

    @Provides
    fun provideAbsentStore(firestore: FirebaseFirestore): AbsentStore {
        return AbsentStoreImpl(firestore)
    }

    @Provides
    fun provideEventParticipantStore(firestore: FirebaseFirestore): EventParticipantStore {
        return EventParticipantStoreImpl(firestore)
    }

    @Provides
    fun provideGlobalStore(firestore: FirebaseFirestore): GlobalStore {
        return GlobalStoreImpl(firestore)
    }

    companion object {
        @JvmStatic
        @Provides
        fun provideFireStore(): FirebaseFirestore {
            return FirebaseFirestore.getInstance()
        }
    }
}