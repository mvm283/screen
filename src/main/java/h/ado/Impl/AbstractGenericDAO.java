package h.ado.Impl;

import h.ado.IAbstractGenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractGenericDAO<T extends Serializable, PK extends Serializable>
        implements IAbstractGenericDAO<T, PK> {


    protected Class<T> clazz;

    @Autowired
    protected SessionFactory sessionFactory;

    protected static Logger log = LoggerFactory
            .getLogger(AbstractGenericDAO.class);

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    @SuppressWarnings("rawtypes")
    public DetachedCriteria getDetachedCriteria(Class clazz) {
        return DetachedCriteria.forClass(clazz);
    }

    // Generic CRUD Methods
    public T getById(final PK id) {
        return (T) this.getCurrentSession().get(this.clazz, id);
    }

    public List<T> getAll() {
        return this.getCurrentSession()
                .createQuery("from " + this.clazz.getName()).list();
    }

    public T create(final T entity) {
        this.getCurrentSession().save(entity);
        return entity;
    }

    public T update(final T entity) {
        return (T) this.getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        this.getCurrentSession().delete(entity);
    }

    public void deleteById(final PK entityId) {
        this.delete(this.getById(entityId));
    }


}
