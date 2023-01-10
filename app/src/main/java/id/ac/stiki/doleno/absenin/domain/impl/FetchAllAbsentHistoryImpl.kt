package id.ac.stiki.doleno.absenin.domain.impl

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.domain.FetchAllAbsentHistory
import id.ac.stiki.doleno.absenin.repository.AbsentRepository
import java.util.concurrent.Executors
import javax.inject.Inject

class FetchAllAbsentHistoryImpl @Inject constructor(private val absentRepository: AbsentRepository) :
    FetchAllAbsentHistory {
    override fun execute(email: String): Task<QuerySnapshot> {
        return absentRepository.get(email)
            .addOnSuccessListener { result: QuerySnapshot ->
                Executors.newSingleThreadExecutor().execute {
                    val absents: List<Absent> = result.documents.map { Absent(it.data) }
                    absentRepository.delete()
                    absentRepository.create(absents)
                }
            }
    }
}