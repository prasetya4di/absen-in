package id.ac.stiki.doleno.absenin.domain

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface FetchAllEventByEmail {
    fun execute(email: String): Task<QuerySnapshot>
}