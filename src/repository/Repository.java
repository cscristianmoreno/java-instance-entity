package repository;

import java.sql.SQLException;
import java.sql.ResultSet;

import database.queries.ExecuteQuery;
import models.IRepository;
import utils.QueryUtil;

public class Repository<T> implements IRepository<T> {

    private final Class<T> entity;
    private final String table;

    public Repository(final Class<T> entity) {
        this.entity = entity;
        this.table = entity.getSimpleName();
    }
    
    /**
     * This Java function saves an entity to a database table and returns the saved entity.
     * 
     * @param entity The `entity` parameter in the `save` method represents the object that you want to
     * save or insert into the database. It is a generic type `T`, which means it can be any type of
     * object. The method generates an SQL query based on the fields of the entity and then executes
     * the
     * @return The `save` method is returning an object of type `T`.
     */
    @Override
    public T save(T entity) throws Exception {
        String query = QueryUtil.getFieldToInsert(entity);

        ResultSet result = ExecuteQuery.query("INSERT INTO %s %s RETURNING *", table, query);

        return QueryUtil.getResult(result, this.entity);
    }

    /**
     * This Java function updates a database entity and returns the updated entity.
     * 
     * @param entity The `entity` parameter represents the object that you want to update in the
     * database. It contains the data that needs to be updated in the database table.
     * @return The `update` method is returning an updated entity of type `T`.
     */
    @Override
    public T update(T entity) throws Exception {
        String query = QueryUtil.getFieldToUpdate(entity);

        ResultSet result =  ExecuteQuery.query("UPDATE %s SET %s RETURNING *", 
        table, query);

        return QueryUtil.getResult(result, this.entity);
    }

    /**
     * This function retrieves a record from a database table based on the provided ID.
     * 
     * @param id The `id` parameter is an integer value representing the unique identifier of the
     * entity you are trying to find in the database.
     * @return The method is returning an object of type T, which is the result of querying the
     * database for a specific entity with the given id.
     */
    @Override
    public T findById(int id) throws Exception {
        ResultSet result = ExecuteQuery.query("SELECT * FROM '%s' WHERE id = '%d'", table, id);

        return QueryUtil.getResult(result, entity);
    }

    /**
     * The `deleteById` function deletes a record from a database table based on the provided ID.
     * 
     * @param id The `id` parameter is an integer value that represents the unique identifier of the
     * record that needs to be deleted from the database table.
     */
    @Override
    public void deleteById(int id) throws SQLException {
        ExecuteQuery.execute("DELETE FROM %s WHERE id = '%d'", table, id);
    }
    
}
