package uk.tojourn.user

import org.springframework.data.repository.CrudRepository


interface UserRepository : CrudRepository<User?, String?> {
    fun findByFirstName(firstName: String?): List<User>?
    fun findByLastName(lastName: String?): List<User>?
}