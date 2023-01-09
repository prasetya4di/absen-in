package id.ac.stiki.doleno.absenin.data.source.network.impl

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import id.ac.stiki.doleno.absenin.data.database.table.Table
import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.data.source.network.AbsentStore

class AbsentStoreImpl(private val firestore: FirebaseFirestore) : AbsentStore {
    override fun createAbsent(absent: Absent, email: String): Task<Void> {
        return getCollection(email).document(absent.documentPath).set(absent.toMap())
    }

    override fun updateAbsent(absent: Absent, email: String): Task<Void> {
        return getCollection(email).document(absent.documentPath).set(absent.toMap())
    }

    override fun get(email: String): Task<QuerySnapshot> {
        return getCollection(email).get()
    }

    override fun get(id: Long, email: String): Absent {
        return Absent(getCollection(email).document("${Table.ABSENT.text}-$id").get().result.data)
    }

    private fun getCollection(email: String): CollectionReference {
        return firestore.collection(Table.ABSENT.text + "_" + email)
    }
}