package models.repository;

import java.sql.SQLException;

public interface IRepositoryByIndex<T> {
    T findById(int id) throws Exception;

    void deleteById(int id) throws SQLException;
}
