package xyz.fomichev.sandbox.model

import java.util.UUID
import javax.persistence.DiscriminatorColumn
import javax.persistence.DiscriminatorType
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
abstract class Client(
    @Id var id: UUID,
    var code: String,
)

@Entity
@DiscriminatorValue("individual")
class IndividualClient(
    id: UUID,
    code: String,
    var lastName: String,
) : Client(id, code)

@Entity
@DiscriminatorValue("legal")
class LegalClient(
    id: UUID,
    code: String,
    var title: String,
) : Client(id, code)
