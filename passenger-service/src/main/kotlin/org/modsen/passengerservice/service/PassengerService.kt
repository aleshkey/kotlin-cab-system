package org.modsen.passengerservice.service

import org.modsen.passengerservice.model.Passenger
import org.modsen.passengerservice.payload.general.PageParams
import org.modsen.passengerservice.payload.request.PassengerRequest
import org.modsen.passengerservice.payload.response.PassengerPageResponse
import org.modsen.passengerservice.payload.response.PassengerResponse
import org.springframework.data.domain.Pageable

interface PassengerService {

    fun getAll(pageParams: PageParams) : PassengerPageResponse

    fun savePassenger(passengerRequest: PassengerRequest) : PassengerResponse

    fun getById(id: Long) : PassengerResponse

    fun updateById (id: Long, passengerRequest: PassengerRequest) : PassengerResponse

    fun deleteById(id: Long) : PassengerResponse
}
