package id.ac.stiki.doleno.absenin.domain.impl

import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.domain.GetAllAbsentHistory
import id.ac.stiki.doleno.absenin.repository.AbsentRepository
import javax.inject.Inject

class GetAllAbsentHistoryImpl @Inject constructor(private val absentRepository: AbsentRepository) :
    GetAllAbsentHistory {
    override fun execute(): List<Absent> {
        return absentRepository.read()
    }
}