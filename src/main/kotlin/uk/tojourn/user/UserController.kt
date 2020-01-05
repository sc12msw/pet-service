package uk.tojourn.user

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import uk.tojourn.generic.PetServiceController
import uk.tojourn.generic.PetServiceResponse
import java.util.*
import javax.validation.Valid


@RestController
class UserController : PetServiceController() {

    @PostMapping("/user")
    fun createUser(@Valid @RequestBody user: User): ResponseEntity<PetServiceResponse> {
        logger.info("Creating user: $user")
        val uuid = UUID.randomUUID()
        val randomUUIDString = uuid.toString()
        logger.info("User $randomUUIDString has been created")
        val responseHeaders = HttpHeaders()
        responseHeaders["Location"] = "/user/$randomUUIDString"
        return ResponseEntity(
                PetServiceResponse("User $randomUUIDString created successfully", 2001),
                responseHeaders,
                HttpStatus.CREATED
        )
    }


}