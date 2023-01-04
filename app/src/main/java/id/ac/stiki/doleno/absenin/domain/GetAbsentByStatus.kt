package id.ac.stiki.doleno.absenin.domain

import androidx.lifecycle.LiveData
import id.ac.stiki.doleno.absenin.data.entity.Absent

interface GetAbsentByStatus {
    fun execute(status: List<String>): LiveData<List<Absent>>
}