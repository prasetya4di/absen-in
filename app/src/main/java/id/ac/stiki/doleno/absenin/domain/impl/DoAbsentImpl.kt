package id.ac.stiki.doleno.absenin.domain.impl

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant
import id.ac.stiki.doleno.absenin.domain.DoAbsent
import id.ac.stiki.doleno.absenin.repository.AbsentRepository
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository
import id.ac.stiki.doleno.absenin.repository.UserRepository
import id.ac.stiki.doleno.absenin.util.enums.AbsentStatus
import id.ac.stiki.doleno.absenin.util.qrcode.QRCodeResultStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DoAbsentImpl @Inject constructor(
    private val absentRepository: AbsentRepository,
    private val userRepository: UserRepository,
    private val eventParticipantRepository: EventParticipantRepository
) : DoAbsent {
    override fun execute(qrResult: String, currentLoc: LatLng): Flow<QRCodeResultStatus> {
        return flow {
            val user = userRepository.read()
            val absent = absentRepository.getById(qrResult.toLong(), user.email)
            val eventParticipant = eventParticipantRepository.get(qrResult.toLong(), user.email)
                .await().documents.map { EventParticipant(it.data) }.first()
            val distance = SphericalUtil.computeDistanceBetween(currentLoc, absent.location) / 1000
            if (distance < 0.5) {
                if (absent.status == AbsentStatus.REGISTERED) {
                    absent.status = AbsentStatus.ATTENDED
                    eventParticipant.absentStatus = AbsentStatus.ATTENDED
                    absentRepository.put(absent, user.email).await()
                    eventParticipantRepository.put(eventParticipant).await()
                    emit(QRCodeResultStatus.VALID)
                } else {
                    emit(QRCodeResultStatus.REGISTERED)
                }
                absentRepository.create(absent)
            } else {
                emit(QRCodeResultStatus.INVALID_LOCATION)
            }
        }
    }
}