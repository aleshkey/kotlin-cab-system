package org.modsen.passengerservice.error

class PassengerNotFoundException(
    id: Long?,
) : RuntimeException("Cant find passenger with id: $id")
