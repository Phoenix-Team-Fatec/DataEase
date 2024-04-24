package SQLConnection;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ConnectionDB {
    public Connection getConnection() {

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/Estoque","root","1234");
        }
        catch(SQLException excecao) {
            throw new RuntimeException(excecao);
        }
    }

    public Map<String, String[]> architectureDB() {
        Map<String, String[]> tablesColumnsMap = new HashMap<>();
        try {
            Connection connection = getConnection();

            DatabaseMetaData metaData = connection.getMetaData();

            String databaseName = connection.getCatalog();

            ResultSet tables = metaData.getTables(databaseName,null,null,new String[]{"TABLE"});

            while (tables.next()){
                String tableName = tables.getString("TABLE_NAME");

                String[] columnsArray;

                ResultSet columns = metaData.getColumns(databaseName,null,tableName,null);

                int columCount = 0;
                while (columns.next()){
                    columCount++;
                }

                columns.beforeFirst();

                columnsArray = new String[columCount];

                int index = 0;
                while (columns.next()){
                    String columnName = columns.getString("COLUMN_NAME");
                    columnsArray[index] = columnName;
                    index++;
                }
                columns.close();

                tablesColumnsMap.put(tableName,columnsArray);
            }
            tables.close();
            connection.close();
        }catch (SQLException excecao){
            throw new RuntimeException();
        }
        return tablesColumnsMap;
    }


}
