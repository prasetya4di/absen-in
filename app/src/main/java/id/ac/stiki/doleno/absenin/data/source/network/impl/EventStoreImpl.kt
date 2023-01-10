package id.ac.stiki.doleno.absenin.data.source.network.impl

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.database.table.Column
import id.ac.stiki.doleno.absenin.data.database.table.Table
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.data.source.network.EventStore
import id.ac.stiki.doleno.absenin.util.date.DateUtil
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

open class EventStoreImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : EventStore {
    override fun createEvent(event: Event): Task<Void> {
        return collection.document(event.documentPath).set(event.toMap())
    }

    override fun updateEvent(event: Event): Task<Void> {
        return collection.document(event.documentPath).set(event.toMap())
    }

    override val allEvent: Task<QuerySnapshot>
        get() = collection.get()
    override val allActiveEvent: Task<QuerySnapshot>
        get() = collection.whereGreaterThanOrEqualTo(
            Column.Event.DATE.columnName,
            DateUtil.removeTime(Date())
        ).get()
    override fun getEventByEmail(email: String): Task<QuerySnapshot> {
        return collection.whereEqualTo(Column.Event.ORGANIZER_MAIL.columnName, email).get()
    }

    override suspend fun checkEventAvailability(id: Long): Boolean {
        return collection.document("${Table.EVENT.text}_$id").get().await().data.isNullOrEmpty()
    }

    private val collection: CollectionReference
        private get() = firestore.collection(Table.EVENT.text)
}