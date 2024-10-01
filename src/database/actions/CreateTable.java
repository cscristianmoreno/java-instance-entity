package database.actions;

import java.util.ArrayList;
import java.util.List;

import annotations.table.Entity;
import database.queries.ExecuteQuery;
import utils.ClassUtil;
import utils.EntityUtil;

public abstract class CreateTable {

    /**
     * Creates a database table for a class annotated with {@link Entity}. 
     * If the table exists, it drops it and recreates it, adding columns 
     * based on the class fields.
     *
     * @param clazz The class for which to create the table. Must be annotated 
     *              with {@link Entity}.
     * @throws Exception If an error occurs during SQL execution.
    */
    public static void create(Class<?> clazz) throws Exception {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            return;
        }

        String table = ClassUtil.getClassName(clazz);

        ExecuteQuery.execute("DROP TABLE IF EXISTS %s CASCADE", table);
        ExecuteQuery.execute("CREATE TABLE %s ()", table);

        List<String> fields = ClassUtil.getFieldsNames(clazz);
        List<String> columns = new ArrayList<String>();

        for (String field: fields) {
            String query = EntityUtil.createStatementAddColumn(clazz, field);
            
            if (query == null) {
                continue;
            }

            columns.add("ADD COLUMN IF NOT EXISTS " + query);
        }

        if (columns.size() > 0) {
            String rows = String.join(", ", columns);
            ExecuteQuery.execute("ALTER TABLE %s %s", table, rows);
        }
    }
}
