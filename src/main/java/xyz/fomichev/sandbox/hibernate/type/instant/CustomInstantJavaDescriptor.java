package xyz.fomichev.sandbox.hibernate.type.instant;

import java.time.Instant;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.InstantJavaDescriptor;

import static java.time.temporal.ChronoUnit.MICROS;

public class CustomInstantJavaDescriptor extends InstantJavaDescriptor {

    public static final CustomInstantJavaDescriptor INSTANCE = new CustomInstantJavaDescriptor();

    @Override
    public <X> X unwrap(Instant instant, Class<X> type, WrapperOptions options) {
        if (instant != null) {
            instant = instant.truncatedTo(MICROS);
        }
        return super.unwrap(instant, type, options);
    }

    @Override
    public String toString(Instant value) {
        return super.toString(value.truncatedTo(MICROS));
    }

    @Override
    public Instant fromString(String string) {
        return super.fromString(string);
    }

    @Override
    public <X> Instant wrap(X value, WrapperOptions options) {
        if ( value == null ) {
            return null;
        }
        if (value instanceof Instant) {
            super.wrap(((Instant) value).truncatedTo(MICROS), options);
        }
        return super.wrap(value, options);
    }
}
