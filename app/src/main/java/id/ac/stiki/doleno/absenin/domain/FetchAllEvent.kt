package id.ac.stiki.doleno.absenin.domain

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface FetchAllEvent {
    fun execute(): Task<QuerySnapshot>
}