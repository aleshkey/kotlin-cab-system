package org.modsen.passengerservice.error

import org.modsen.passengerservice.constants.MessageConstants

class PassengerNotFoundException(
    id: Long,
) : RuntimeException(MessageConstants.PASSENGER_NOT_FOUND(id))
