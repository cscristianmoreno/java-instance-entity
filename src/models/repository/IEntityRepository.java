package models.repository;

public interface IEntityRepository<T> extends IRepositoryByIndex<T> {
    T save(T entity) throws Exception;

    T update(T entity) throws Exception;
}
