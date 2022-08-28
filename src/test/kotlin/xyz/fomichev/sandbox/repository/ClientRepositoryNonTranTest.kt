package xyz.fomichev.sandbox.repository

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import xyz.fomichev.sandbox.NonTransactionalBaseTest
import xyz.fomichev.sandbox.model.IndividualClient
import xyz.fomichev.sandbox.model.LegalClient
import java.util.UUID

val CLIENT_ID_1: UUID = UUID.randomUUID()
val CLIENT_ID_2: UUID = UUID.randomUUID()

class ClientRepositoryNonTranTest : NonTransactionalBaseTest() {

    @BeforeEach
    fun init() {
        transactionTemplate.execute { status ->
            entityManager.persist(IndividualClient(CLIENT_ID_1, "test_code1", "Fomichev"))
            entityManager.persist(LegalClient(CLIENT_ID_2, "test_code2", "LLC Fomichev"))
        }
    }

    @Test
    fun `find by code`() {
        transactionTemplate.execute { status ->
            val client1 = clientRepository.findByCode("test_code1")
            Assertions.assertTrue(client1 is IndividualClient)

            val client2 = clientRepository.findByCode("test_code2")
            Assertions.assertTrue(client2 is LegalClient)

            val client3 = clientRepository.findByCode("test_code3")
            Assertions.assertTrue(client3 == null)
        }
    }
}
