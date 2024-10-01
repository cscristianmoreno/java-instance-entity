package database.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;


import database.connection.DBConnection;

public abstract class ExecuteQuery {
    
    /**
     * The `query` function prepares and executes a SQL query with optional parameters and returns the
     * result set.
     * 
     * @param query The `query` parameter is a SQL query string that you want to execute. It can
     * contain placeholders for parameters that will be replaced by the values in the `params` array.
     * @return The method is returning a `ResultSet` object, which represents the result set generated
     * by the query execution.
     */
    public static ResultSet query(String query, Object... params) throws SQLException {
        String sqlQuery = String.format(query, params);

        if (params != null) {
            sqlQuery = String.format(sqlQuery, params);
        }

        PreparedStatement preparedStatement = prepareQuery(sqlQuery);

        System.out.println("Executing query...");
        return preparedStatement.executeQuery();
    }

    /**
     * The function `execute` prepares and executes a SQL query with optional parameters.
     * 
     * @param query The `query` parameter is a SQL query string that represents the SQL statement to be
     * executed. It can be any valid SQL statement such as SELECT, INSERT, UPDATE, DELETE, etc.
     * @return The method is returning an integer value, which is the result of executing the SQL query
     * without any parameter values.
     */
    public static int execute(String query, Object... params) throws SQLException {

        String sqlQuery = query;

        if (params != null) {
            sqlQuery = String.format(sqlQuery, params);
        }

        PreparedStatement preparedStatement = prepareQuery(sqlQuery);
        
        System.out.println("Executing query without values...");
        return preparedStatement.executeUpdate();
    }

    private static PreparedStatement prepareQuery(final String sqlQuery) throws SQLException {
        Connection connection = new DBConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        return preparedStatement;
    }
}
