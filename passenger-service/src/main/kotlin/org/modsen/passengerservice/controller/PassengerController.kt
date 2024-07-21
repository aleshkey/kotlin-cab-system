@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.modsen.passengerservice.controller

import jakarta.validation.Valid
import lombok.extern.slf4j.Slf4j
import org.modsen.passengerservice.payload.general.PageParams
import org.modsen.passengerservice.payload.request.PassengerRequest
import org.modsen.passengerservice.payload.response.PassengerPageResponse
import org.modsen.passengerservice.payload.response.PassengerResponse
import org.modsen.passengerservice.service.PassengerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/passengers")
class PassengerController(
    private val passengerService: PassengerService,
) {
    @GetMapping
    fun getAll(pageParams: PageParams): ResponseEntity<PassengerPageResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(passengerService.getAll(pageParams))

    @PostMapping
    fun savePassenger(
        @RequestBody @Valid passengerRequest: PassengerRequest,
    ): ResponseEntity<PassengerResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(passengerService.savePassenger(passengerRequest))

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Long,
    ): ResponseEntity<PassengerResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(passengerService.getById(id))

    @PutMapping("/{id}")
    fun updateById(
        @PathVariable id: Long,
        @RequestBody @Valid passengerRequest: PassengerRequest,
    ): ResponseEntity<PassengerResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(passengerService.updateById(id, passengerRequest))

    @DeleteMapping("/{id}")
    fun deleteById(
        @PathVariable id: Long,
    ): ResponseEntity<PassengerResponse> =
        ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(passengerService.deleteById(id))
}
