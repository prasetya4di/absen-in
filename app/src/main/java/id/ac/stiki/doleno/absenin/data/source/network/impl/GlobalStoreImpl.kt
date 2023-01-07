package id.ac.stiki.doleno.absenin.data.source.network.impl

import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.FirebaseFirestore
import id.ac.stiki.doleno.absenin.data.source.network.GlobalStore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GlobalStoreImpl @Inject constructor(private val firestore: FirebaseFirestore) : GlobalStore {
    override suspend fun getLatestId(table: String): Long {
        return firestore.collection(table).count().get(AggregateSource.SERVER).await().count
    }
}