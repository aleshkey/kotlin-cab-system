package org.modsen.passengerservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PassengerServiceApplication

fun main(args: Array<String>) {
    runApplication<PassengerServiceApplication>(*args)
}
