package id.ac.stiki.doleno.absenin.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.entity.Event

interface EventRepository {
    fun create(Event: Event)
    fun create(Event: List<Event>)
    fun read(): List<Event>
    fun readById(id: Int): Event
    fun delete()
    fun post(event: Event): Task<Void>
    fun get(): Task<QuerySnapshot>
    val activeEvent: Task<QuerySnapshot>
    fun getAllEventByEmail(email: String): Task<QuerySnapshot>
    suspend fun checkEventAvailability(id: Long): Boolean
}