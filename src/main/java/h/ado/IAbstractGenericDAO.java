package h.ado;

import java.io.Serializable;
import java.util.List;


import javax.transaction.Transactional;

public interface IAbstractGenericDAO<T extends Serializable, PK extends Serializable> {

	void setClazz(final Class<T> clazzToSet);

	@Transactional()
	T getById(final PK id);

	@Transactional()
	List<T> getAll();

	@Transactional()
	T create(final T entity);

	@Transactional()
	T update(final T entity);

	@Transactional()
	void delete(final T entity);

	@Transactional()
	void deleteById(final PK entityId);

}
