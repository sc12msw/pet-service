package uk.tojourn.user

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.*
import uk.tojourn.generic.PetServiceController
import uk.tojourn.generic.PetServiceResponse
import java.util.*
import javax.validation.Valid


@RestController
class UserController : PetServiceController() {

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@Valid @RequestBody user: User): PetServiceResponse {
        logger.info("Creating user: $user")
        val uuid = UUID.randomUUID()
        val randomUUIDString = uuid.toString()
        logger.info("User $randomUUIDString has been created")
        return PetServiceResponse("User $randomUUIDString added successfully", 2001)
    }



}