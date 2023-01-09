package id.ac.stiki.doleno.absenin.domain.impl

import id.ac.stiki.doleno.absenin.data.entity.Absent
import id.ac.stiki.doleno.absenin.domain.GetAbsentByStatus
import id.ac.stiki.doleno.absenin.repository.AbsentRepository
import javax.inject.Inject

class GetAbsentByStatusImpl @Inject constructor(
    val absentRepository: AbsentRepository
) : GetAbsentByStatus {
    override fun execute(status: List<String>): List<Absent> =
        absentRepository.readByStatus(status)
}