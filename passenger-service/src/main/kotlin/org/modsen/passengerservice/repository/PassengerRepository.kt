package org.modsen.passengerservice.repository

import org.modsen.passengerservice.model.Passenger
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PassengerRepository : JpaRepository<Passenger, Long> {
    fun existsByEmail(email: String): Boolean
}
