@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.modsen.passengerservice.model

import jakarta.persistence.*

@Entity
data class Passenger(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0,
    @Column(name = "first_name")
    var firstName: String = "",
    @Column(name = "last_name")
    var lastName: String = "",
    var email: String = "",
) {
    override fun toString(): String = "Passenger(id=$id, firstName='$firstName', lastName='$lastName', email='$email')"
}
