package xyz.fomichev.sandbox.repository

import org.springframework.data.repository.CrudRepository
import xyz.fomichev.sandbox.model.Client
import java.util.UUID

interface ClientRepository : CrudRepository<Client, UUID> {
    fun findByCode(code: String): Client?
}
