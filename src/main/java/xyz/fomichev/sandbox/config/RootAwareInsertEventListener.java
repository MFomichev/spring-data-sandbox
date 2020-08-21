package xyz.fomichev.sandbox.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.event.spi.*;
import org.springframework.stereotype.Component;
import xyz.fomichev.sandbox.model.RootAware;

import java.util.Map;

@Slf4j
@Component
public class RootAwareInsertEventListener implements PersistEventListener, MergeEventListener {

    @Override
    public void onPersist(PersistEvent event) throws HibernateException {
        onInsert(event.getObject(), event.getSession());
    }

    @Override
    public void onPersist(PersistEvent event, Map createdAlready) throws HibernateException {
        onPersist(event);
    }

    @Override
    public void onMerge(MergeEvent event) throws HibernateException {
        onInsert(event.getEntity(), event.getSession());
    }

    @Override
    public void onMerge(MergeEvent event, Map copiedAlready) throws HibernateException {
        onMerge(event);
    }

    private void onInsert(Object entity, Session session) {
        if (entity instanceof RootAware) {
            RootAware<?> rootAware = (RootAware<?>) entity;
            Object root = rootAware.root();
            session.lock(root, LockMode.OPTIMISTIC_FORCE_INCREMENT);
        }
    }
}
