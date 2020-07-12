package xyz.fomichev.sandbox.hibernate.type.instant;

import java.time.Instant;
import java.util.Comparator;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.InstantType;
import org.hibernate.type.LiteralType;
import org.hibernate.type.VersionType;
import org.hibernate.type.descriptor.sql.TimestampTypeDescriptor;

import static java.time.temporal.ChronoUnit.MICROS;

public class CustomInstantType extends AbstractSingleColumnStandardBasicType<Instant>
        implements VersionType<Instant>, LiteralType<Instant> {

    public static final CustomInstantType INSTANCE = new CustomInstantType();

    private static final InstantType delegateInstance = InstantType.INSTANCE;

    public CustomInstantType() {
        super(TimestampTypeDescriptor.INSTANCE, CustomInstantJavaDescriptor.INSTANCE);
    }

    @Override
    public String objectToSQLString(Instant value, Dialect dialect) throws Exception {
        return delegateInstance.objectToSQLString(value.truncatedTo(MICROS), dialect);
    }

    @Override
    public Instant seed(SharedSessionContractImplementor session) {
        return delegateInstance.seed(session);
    }

    @Override
    public Instant next(Instant current, SharedSessionContractImplementor session) {
        return delegateInstance.next(current.truncatedTo(MICROS), session);
    }

    @Override
    public Comparator<Instant> getComparator() {
        return (o1, o2) ->
                delegateInstance.getComparator().compare(o1.truncatedTo(MICROS), o2.truncatedTo(MICROS));
    }

    @Override
    public String getName() {
        return delegateInstance.getName();
    }

    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }
}
