package org.modsen.passengerservice.mapper

import org.mapstruct.Mapper
import org.modsen.passengerservice.model.Passenger
import org.modsen.passengerservice.payload.request.PassengerRequest
import org.modsen.passengerservice.payload.response.PassengerResponse

@Mapper
interface PassengerMapper {

    fun objToResponse(passenger: Passenger): PassengerResponse

    fun requestToObj(passengerRequest: PassengerRequest): Passenger

}
