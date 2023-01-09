package id.ac.stiki.doleno.absenin.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.AggregateQuerySnapshot
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.entity.Event
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant

interface EventParticipantRepository {
    fun read(eventId: Long): List<EventParticipant>
    fun create(eventParticipant: EventParticipant)
    fun create(eventParticipant: List<EventParticipant>)
    fun post(eventParticipant: EventParticipant): Task<DocumentReference>
    operator fun get(eventId: Int): Task<QuerySnapshot>
    fun count(event: Event, email: String): Task<AggregateQuerySnapshot>
}