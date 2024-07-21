package org.modsen.passengerservice.service.impl

import org.modsen.passengerservice.error.EmailAlreadyRegistered
import org.modsen.passengerservice.error.PassengerNotFoundException
import org.modsen.passengerservice.mapper.PassengerMapper
import org.modsen.passengerservice.model.Passenger
import org.modsen.passengerservice.payload.general.PageParams
import org.modsen.passengerservice.payload.request.PassengerRequest
import org.modsen.passengerservice.payload.response.PassengerPageResponse
import org.modsen.passengerservice.payload.response.PassengerResponse
import org.modsen.passengerservice.repository.PassengerRepository
import org.modsen.passengerservice.service.PassengerService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Service
class PassengerServiceImpl(
        private val passengerRepository: PassengerRepository,
        private val passengerMapper: PassengerMapper
) : PassengerService {

    private fun createPageable(pageParams: PageParams): Pageable =
        PageRequest.of(
            pageParams.page-1,
            pageParams.size,
            createDirection(pageParams)
        )

    private fun createDirection(pageParams: PageParams): Sort {
        var direction = Sort.unsorted()
        if (pageParams.direction == "asc") {
            direction = Sort.by(Sort.Direction.ASC, pageParams.sort)
        } else if (pageParams.direction == "desc") {
            direction = Sort.by(Sort.Direction.DESC, pageParams.sort)
        }
        return direction
    }

    private fun findById(id: Long): Passenger =
        passengerRepository.findById(id)
            .orElseThrow{ PassengerNotFoundException(id) }

    private fun checkEmail(email: String) {
        if (passengerRepository.existsByEmail(email)){
            throw EmailAlreadyRegistered(email)
        }
    }

    override fun getAll(pageParams: PageParams): PassengerPageResponse {
        val content: List<PassengerResponse> = passengerMapper.listObjToListResponse(
            passengerRepository
                .findAll(createPageable(pageParams))
                .content
        )
        return PassengerPageResponse(
                pageParams = pageParams,
                content    = content
            )
    }

    override fun savePassenger(passengerRequest: PassengerRequest): PassengerResponse {
        checkEmail(passengerRequest.email!!)
        val passengerToSave = passengerMapper.requestToObj(passengerRequest)
        val res = passengerRepository.save(passengerToSave)
        return passengerMapper.objToResponse(res)
    }

    override fun getById(id: Long) : PassengerResponse =
        passengerMapper.objToResponse(
            findById(id)
        )

    override fun updateById(id: Long, passengerRequest: PassengerRequest) : PassengerResponse {
        val passengerToUpdate = passengerMapper.requestToObj(passengerRequest)
        val passengerInDb = findById(id)
        if (passengerInDb.email != passengerToUpdate.email){
            checkEmail(passengerToUpdate.email)
        }
        passengerToUpdate.id = id
        val res = passengerRepository.save(passengerToUpdate)
        return passengerMapper.objToResponse(res)
    }

    override fun deleteById(id: Long) : PassengerResponse {
        val passenger = findById(id)
        passengerRepository.deleteById(id)
        return passengerMapper.objToResponse(passenger)
    }

}
