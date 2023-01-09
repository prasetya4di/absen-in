package id.ac.stiki.doleno.absenin.data.source.network

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.entity.Absent

interface AbsentStore {
    fun createAbsent(absent: Absent, email: String): Task<Void>
    fun updateAbsent(absent: Absent, email: String): Task<Void>
    operator fun get(email: String): Task<QuerySnapshot>
    operator fun get(id: Long, email: String): Absent
}