package id.ac.stiki.doleno.absenin.data.source.network

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.AggregateQuerySnapshot
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant

interface EventParticipantStore {
    fun create(eventParticipant: EventParticipant): Task<DocumentReference>
    operator fun get(eventId: Long): Task<QuerySnapshot>
    operator fun get(eventId: Long, email: String): Task<QuerySnapshot>
    fun count(event: Event, email: String): Task<AggregateQuerySnapshot>
    fun update(eventParticipant: EventParticipant): Task<QuerySnapshot>
}