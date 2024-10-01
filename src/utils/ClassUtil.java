package utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;


public abstract class ClassUtil {
    
    /**
     * The function `getFieldsNames` takes a Class object as input and returns a list of field names of
     * that class.
     * 
     * @param clazz The `clazz` parameter is a `Class` object representing a class for which you want
     * to retrieve the names of its declared fields.
     * @return The method `getFieldsNames` returns a list of strings containing the names of all the
     * fields declared in the specified class `clazz`.
     */
    public static List<String> getFieldsNames(Class<?> clazz) {
        return Set.of(clazz.getDeclaredFields()).stream()
        .map(Field::getName).toList();
    }

    /**
     * The function `getFieldsAnnotations` returns a list of arrays of annotations for the fields of a
     * given class.
     * 
     * @param clazz The `clazz` parameter is a `Class` object representing a class for which you want
     * to retrieve the annotations of its fields.
     * @return A list of arrays of annotations for the fields declared in the given class.
     */
    public static List<Annotation[]> getFieldsAnnotations(Class<?> clazz) {
        return Set.of(clazz.getDeclaredFields()).stream()
        .map(Field::getDeclaredAnnotations).toList();
    }

    /**
     * The function `getClassName` returns the simple name of a given class.
     * 
     * @param clazz The `clazz` parameter is a `Class` object representing a class in Java.
     * @return The method `getClassName` returns the simple name of the class represented by the
     * `Class` object passed as a parameter.
     */
    public static String getClassName(Class<?> clazz) {
        return clazz.getSimpleName();
    }

    /**
     * The function `getFields` returns an array of `Field` objects representing the fields of a given
     * class.
     * 
     * @param clazz The `clazz` parameter is a `Class` object representing a class for which you want
     * to retrieve the declared fields.
     * @return An array of Field objects representing the fields declared in the specified class.
     */
    public static Field[] getFields(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }
}
