package repository;

public abstract class CrudRepository<T> {
    
    /**
     * The function `entity` returns a new `Repository` instance with the specified class type.
     * 
     * @param clazz The `clazz` parameter is a `Class` object representing the class of type `T` for
     * which a `Repository` instance is being created.
     * @return A `Repository` object of type `T` is being returned.
     */
    public static <T> Repository<T> entity(Class<T> clazz) {
        return new Repository<T>(clazz);
    }
}
