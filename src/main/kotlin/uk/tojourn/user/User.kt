package uk.tojourn.user

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias

@TypeAlias("user")
data class User (val firstName : String,val lastName:String, val email: String,  @Id var id: String?)