package xyz.fomichev.sandbox.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.boot.spi.SessionFactoryBuilderFactory;
import org.hibernate.boot.spi.SessionFactoryBuilderImplementor;
import xyz.fomichev.sandbox.hibernate.type.instant.CustomInstantType;

@Slf4j
public class CustomDataTypesRegistration implements SessionFactoryBuilderFactory {

    @Override
    public SessionFactoryBuilder getSessionFactoryBuilder(MetadataImplementor metadata,
                                                          SessionFactoryBuilderImplementor defaultBuilder) {
        log.info("Registering custom Hibernate data types");
        metadata.getTypeResolver().registerTypeOverride(CustomInstantType.INSTANCE);
        return defaultBuilder;
    }
}
