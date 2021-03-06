package xyz.fomichev.sandbox.config;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
public class HibernateListenerConfigurer {
     
    @PersistenceUnit
    private EntityManagerFactory emf;
     
    @Autowired
    private RootAwareInsertEventListener insertEventListener;
    @Autowired
    private RootAwareUpdateAndDeleteEventListener flushEntityListener;

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.PERSIST).appendListener(insertEventListener);
        registry.getEventListenerGroup(EventType.MERGE).appendListener(insertEventListener);
        registry.getEventListenerGroup(EventType.FLUSH_ENTITY).appendListener(flushEntityListener);
    }
}
