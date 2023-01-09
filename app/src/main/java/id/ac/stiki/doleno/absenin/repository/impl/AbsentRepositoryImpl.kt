package id.ac.stiki.doleno.absenin.repository.impl

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.data.source.local.AbsentDao
import id.ac.stiki.doleno.absenin.data.source.network.AbsentStore
import id.ac.stiki.doleno.absenin.repository.AbsentRepository
import javax.inject.Inject

class AbsentRepositoryImpl @Inject constructor(
    private val absentDao: AbsentDao,
    private val absentStore: AbsentStore
) : AbsentRepository {
    override fun create(absent: Absent) {
        absentDao.insert(absent)
    }

    override fun create(absent: List<Absent>) {
        absentDao.insert(absent)
    }

    override fun read(): List<Absent> {
        return absentDao.all
    }

    override fun readByStatus(status: List<String>): List<Absent> {
        return absentDao.getByStatus(status)
    }

    override fun read(id: Int): Absent {
        return absentDao.getById(id)
    }

    override fun delete() {
        absentDao.delete()
    }

    override fun post(absent: Absent, email: String): Task<Void> {
        return absentStore.createAbsent(absent, email)
    }

    override fun get(email: String): Task<QuerySnapshot> {
        return absentStore.get(email)
    }
}