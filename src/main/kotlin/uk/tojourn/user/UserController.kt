package uk.tojourn.user

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import uk.tojourn.generic.PetServiceController
import uk.tojourn.generic.PetServiceResponse
import java.util.*
import javax.validation.Valid


@RestController("/user/")
class UserController : PetServiceController() {

    @Autowired
    lateinit var userRepository: UserRepository

    @PostMapping
    fun createUser(@Valid @RequestBody user: User): ResponseEntity<PetServiceResponse> {
        val userWithId = createAndAddUUIDToUser(user)
        val userId = userWithId.id
        logger.info("Creating user $userId in database")
        userRepository.save(user)
        val successfullyCreatedUserMessage = "User $userId has been created"
        logger.info(successfullyCreatedUserMessage)
        val responseHeaders = HttpHeaders()
        responseHeaders["Location"] = "/user/$userId"
        return ResponseEntity(
                PetServiceResponse(successfullyCreatedUserMessage, 2001),
                responseHeaders,
                HttpStatus.CREATED
        )
    }

    // TODO implement GET all

    // TODO GET TO WORK
    @GetMapping("{id}")
    fun getUserById(@PathVariable(value = "id") id: String): ResponseEntity<PetServiceResponse> {
        val user = userRepository.findById(id)
        val gson = Gson()
        val jsonString = gson.toJson(user)
        return ResponseEntity(
                PetServiceResponse(jsonString, 2000),
                HttpStatus.OK
        )
    }

    private fun createAndAddUUIDToUser(user: User): User {
        val uuid = UUID.randomUUID()
        val randomUUIDString = uuid.toString()
        user.id = randomUUIDString
        return user
    }


}