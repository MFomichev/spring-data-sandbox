package xyz.fomichev.sandbox.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.Status;
import org.hibernate.event.spi.FlushEntityEvent;
import org.hibernate.event.spi.FlushEntityEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;
import xyz.fomichev.sandbox.model.RootAware;

@Slf4j
@Component
public class RootAwareUpdateAndDeleteEventListener implements FlushEntityEventListener {

    @Override
    public void onFlushEntity(FlushEntityEvent event) throws HibernateException {
        EntityEntry entry = event.getEntityEntry();
        Object entity = event.getEntity();
        boolean mightBeDirty = entry.requiresDirtyCheck(entity);

        if (mightBeDirty && entity instanceof RootAware) {
            RootAware<?> rootAware = (RootAware<?>) entity;
            if (updated(event)) {
                Object root = rootAware.root();
                incrementRootVersion(event, root);
            } else if (deleted(event)) {
                Object root = rootAware.root();
                if (event.getSession().getPersistenceContext().getEntry(root).getStatus() != Status.DELETED) {
                    incrementRootVersion(event, root);
                }
            }
        }
    }

    private void incrementRootVersion(FlushEntityEvent event, Object root) {
        event.getSession().lock(root, LockMode.OPTIMISTIC_FORCE_INCREMENT);
    }

    private boolean deleted(FlushEntityEvent event) {
        return event.getEntityEntry().getStatus() == Status.DELETED;
    }

    private boolean updated(FlushEntityEvent event) {
        EntityEntry entry = event.getEntityEntry();
        Object entity = event.getEntity();

        int[] dirtyProperties;
        EntityPersister persister = entry.getPersister();
        Object[] values = event.getPropertyValues();
        SessionImplementor session = event.getSession();

        if (event.hasDatabaseSnapshot()) {
            dirtyProperties = persister.findModified(
                    event.getDatabaseSnapshot(), values, entity, session
            );
        } else {
            dirtyProperties = persister.findDirty(
                    values, entry.getLoadedState(), entity, session
            );
        }

        return dirtyProperties != null;
    }
}
