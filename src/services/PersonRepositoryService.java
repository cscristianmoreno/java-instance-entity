package services;

import java.sql.SQLException;

import entity.Persons;
import models.IRepository;
import repository.CrudRepository;
import repository.Repository;

public class PersonRepositoryService implements IRepository<Persons> {

    private final Repository<Persons> repository = CrudRepository.entity(Persons.class);

    /**
     * The function saves a person entity using a repository in Java.
     * 
     * @param entity The `entity` parameter in the `save` method represents an object of type `Persons`
     * that you want to save in the repository.
     * @return The method is returning a saved instance of the `Persons` entity after it has been saved
     * in the repository.
     */
    @Override
    public Persons save(Persons entity) throws Exception {
        return repository.save(entity);
    }

    /**
     * This function updates a person entity in a repository and returns the updated entity.
     * 
     * @param entity The `entity` parameter represents the object of type `Persons` that you want to
     * update in the repository.
     * @return The `update` method is returning a `Persons` object after updating the entity in the
     * repository.
     */
    @Override
    public Persons update(Persons entity) throws Exception {
        return repository.update(entity);
    }

    /**
     * This function retrieves a Person object by its ID from a repository.
     * 
     * @param id The `id` parameter is an integer value that represents the unique identifier of a
     * person in the database.
     * @return An instance of the `Persons` class is being returned.
     */
    @Override
    public Persons findById(int id) throws Exception {
        return repository.findById(id);
    }

   /**
    * This function overrides the deleteById method to delete a record by its ID from a repository and
    * throws a SQLException if an error occurs.
    * 
    * @param id The `id` parameter is an integer value that represents the unique identifier of the
    * entity that needs to be deleted from the repository.
    */
    @Override
    public void deleteById(int id) throws SQLException {
        repository.deleteById(id);
    }
}
