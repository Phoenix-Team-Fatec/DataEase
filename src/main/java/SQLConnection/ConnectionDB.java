package SQLConnection;

import java.sql.*;
public class ConnectionDB {



    public Connection getConnection() {

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/estoque","root","1234");
        }
        catch(SQLException excecao) {
            throw new RuntimeException(excecao);
        }
    }

    public ConnectionDB() {
        this.getConnection();
    }

    /*public String architectureDB() {
            StringBuilder result = new StringBuilder();
            try {
                Connection connection = getConnection();
                DatabaseMetaData metaData = connection.getMetaData();
                String databaseName = connection.getCatalog();
                ResultSet tables = metaData.getTables(databaseName, null, null, new String[]{"TABLE"});

                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");

                    ResultSet columns = metaData.getColumns(databaseName, null, tableName, null);
                    result.append("Tables: ").append(tableName).append("\nColumns: ");
                    while (columns.next()) {
                        String columnName = columns.getString("COLUMN_NAME");
                        result.append("\n   ").append(columnName);
                    }
                    columns.close();
                    result.append("\n\n");
                }
                tables.close();
                connection.close();
            } catch (SQLException excecao) {
                throw new RuntimeException(excecao);
            }
            return result.toString();
        }*/
    public String architectureDB() {
        StringBuilder result = new StringBuilder();
        try (Connection connection = getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            String databaseName = connection.getCatalog();
            ResultSet tables = metaData.getTables(databaseName, null, null, new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");

                ResultSet columns = metaData.getColumns(databaseName, null, tableName, null);
                result.append("Tables: ").append(tableName).append("\nColumns: ");
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    result.append("\n   ").append(columnName);
                }
                columns.close();
                result.append("\n");
            }
            tables.close();
        } catch (SQLException excecao) {
            throw new RuntimeException(excecao);
        }
        return result.toString();
    }

}
