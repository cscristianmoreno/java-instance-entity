package services;

import java.sql.SQLException;

import entity.Users;
import models.IRepository;
import repository.CrudRepository;
import repository.Repository;

public class UserRepositoryService implements IRepository<Users> {

    private final Repository<Users> repository = CrudRepository.entity(Users.class);

    /**
     * The `save` function overrides the method to save a `Users` entity and returns the saved entity.
     * 
     * @param entity The `entity` parameter in the `save` method represents an object of the `Users`
     * class that you want to save in the repository.
     * @return The `save` method is returning the `Users` entity that was saved in the repository.
     */
    @Override
    public Users save(Users entity) throws Exception {
        return repository.save(entity);
    }

    /**
     * The update function in Java updates a Users entity in a repository and returns the updated
     * entity.
     * 
     * @param entity The `entity` parameter in the `update` method represents the `Users` object that
     * you want to update in the repository. This method is responsible for saving the updated `Users`
     * entity in the repository and returning the updated entity.
     * @return The `update` method is returning the updated `Users` entity after saving it in the
     * repository.
     */
    @Override
    public Users update(Users entity) throws Exception {
        return repository.save(entity);
    }

    /**
     * This function overrides a method to find a user by their ID in a repository and returns the user
     * object.
     * 
     * @param id The `id` parameter is an integer value that represents the unique identifier of a user
     * in the system. The `findById` method is used to retrieve a user entity from the repository based
     * on this identifier.
     * @return An instance of the `Users` class is being returned.
     */
    @Override
    public Users findById(int id) throws Exception {
        return repository.findById(id);
    }

    /**
     * This function deletes a record from a repository based on the provided ID.
     * 
     * @param id The `id` parameter is an integer value that represents the unique identifier of the
     * entity that needs to be deleted from the repository.
     */
    @Override
    public void deleteById(int id) throws SQLException {
        repository.deleteById(id);
    }
}
