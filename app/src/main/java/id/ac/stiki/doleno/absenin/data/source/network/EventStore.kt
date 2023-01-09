package id.ac.stiki.doleno.absenin.data.source.network

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.entity.Event

interface EventStore {
    fun createEvent(event: Event): Task<Void>
    fun updateEvent(event: Event): Task<Void>
    val allEvent: Task<QuerySnapshot>
    val allActiveEvent: Task<QuerySnapshot>
    fun getEventByEmail(email: String): Task<QuerySnapshot>
    fun checkEventAvailability(id: Long): Boolean
}