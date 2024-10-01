package utils;

import java.sql.ResultSet;

import java.lang.reflect.Field;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import annotations.relations.OneToOne;
import annotations.types.PrimaryKey;
import database.queries.ExecuteQuery;

public abstract class QueryUtil {

    /**
     * This Java function generates a SQL query string for inserting data into a database table based
     * on the fields of a given object, handling special cases for primary keys and one-to-one
     * relationships.
     * 
     * @param clazz The method `getFieldToInsert` takes an object of type `T` as a parameter and
     * retrieves the fields of the class of that object. It then processes these fields to generate a
     * SQL query for inserting data into a database table.
     * @return The `getFieldToInsert` method is returning a formatted string that represents the fields
     * and values to be inserted into a database table. The method constructs the field names and
     * corresponding values based on the fields of the input object `clazz`. It skips fields annotated
     * with `@PrimaryKey` and handles fields annotated with `@OneToOne` by querying the maximum ID from
     * a related table and using it as the value
     */
    public static <T> String getFieldToInsert(T clazz) throws Exception {

        Field[] fields = ClassUtil.getFields(clazz.getClass());

        List<String> rows = new ArrayList<String>();
        List<Object> values = new ArrayList<Object>();

        for (Field field: fields) {
            field.setAccessible(true);

            if (field.get(clazz) == null) {
                continue;
            }

            if (field.isAnnotationPresent(PrimaryKey.class)) {
                continue;
            }

            Object value = field.get(clazz);
            
            String fieldName = field.getName(); 
            
            if (field.isAnnotationPresent(OneToOne.class)) {
                String relationName = field.getType().getSimpleName();
                fieldName = relationName + "_id";

                String sqlQuery = getFieldToInsert(value);
                ResultSet result = ExecuteQuery.query("INSERT INTO %s %s RETURNING id", 
                value.getClass().getSimpleName(), sqlQuery);

                if (result.next()) {
                    value = result.getString("id");
                }
            }

            rows.add(fieldName);

            values.add(value);
        }

        return String.format("(%s) VALUES (%s)",
            String.join(",", rows),
            values.stream().map((object) -> {
                if (object instanceof String) {
                    return String.format("'%s'", object);
                }

                return object.toString();
            }).collect(Collectors.joining(","))
        );
    }

    /**
     * The function `getResult` reads data from a `ResultSet` and populates an instance of a specified
     * class with the retrieved values.
     * 
     * @param result The `result` parameter is a `ResultSet` object, which is typically used in Java to
     * retrieve data from a database query result. It represents a set of rows containing data
     * retrieved from a database after executing a query.
     * @param clazz The `clazz` parameter in the `getResult` method is a Class object that represents
     * the class type of the object you want to create and populate with data from the ResultSet. It is
     * used to instantiate an object of that class type and set its fields based on the data retrieved
     * from the ResultSet.
     * @return The `getResult` method returns an instance of type T, which is created and populated
     * based on the data retrieved from the ResultSet.
     */
    public static <T> T getResult(final ResultSet result, final Class<T> clazz) throws Exception {
        T instance = clazz.getConstructor().newInstance();

        while (result.next()) {
            Field[] fields = ClassUtil.getFields(clazz);
            for (Field field: fields) {
                field.setAccessible(true);
                
                if (field.get(instance) == null) {
                    continue;
                }

                String fieldName = field.getName();
                Object value = result.getObject(fieldName);
                
                field.set(instance, value);
            }
        }

        return instance;
    }

    /**
     * The function getFieldToUpdate iterates through the fields of a given class object and returns a
     * string of fields and their values that are not null and do not have specific annotations.
     * 
     * @param clazz The `getFieldToUpdate` method takes an object of type `T` as a parameter, which is
     * represented by the variable `clazz` in the method signature. This method retrieves the fields of
     * the class of the provided object, checks for certain annotations on the fields, and constructs a
     * string representing the fields
     * @return The `getFieldToUpdate` method returns a String that contains a list of fields and their
     * corresponding values that need to be updated in an object of type T. The method skips fields
     * that are annotated with `PrimaryKey` or `OneToOne`, as well as fields that have a null value.
     */
    public static <T> String getFieldToUpdate(T clazz) throws Exception {
        Field[] fields = ClassUtil.getFields(clazz.getClass());

        List<String> rows = new ArrayList<String>();

        for (Field field: fields) {
            field.setAccessible(true);

            if (field.get(clazz) == null) {
                continue;
            }

            if (field.isAnnotationPresent(PrimaryKey.class) ||
            field.isAnnotationPresent(OneToOne.class)) {
                continue;
            }

            String fieldName = field.getName(); 
            Object value = field.get(clazz);

            rows.add(String.format("%s='%s'", fieldName, value));
        }

        return String.join(",", rows);
    }
}
