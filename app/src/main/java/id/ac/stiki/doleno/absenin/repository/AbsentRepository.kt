package id.ac.stiki.doleno.absenin.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.entity.Absent

interface AbsentRepository {
    fun create(absent: Absent)
    fun create(absent: List<Absent>)
    fun read(): List<Absent>
    fun readByStatus(status: List<String>): List<Absent>
    fun read(id: Int): Absent
    fun delete()
    fun post(absent: Absent, email: String): Task<Void>
    fun put(absent: Absent, email: String): Task<Void>
    fun get(email: String): Task<QuerySnapshot>
    fun getById(id: Long, email: String): Absent
}