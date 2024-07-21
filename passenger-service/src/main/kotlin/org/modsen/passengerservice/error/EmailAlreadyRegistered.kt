package org.modsen.passengerservice.error

class EmailAlreadyRegistered(
    email: String,
) : RuntimeException("Email $email already registered!")
