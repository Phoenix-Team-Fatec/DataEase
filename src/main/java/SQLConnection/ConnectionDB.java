package SQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public Connection getConnection() {

        //testando
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/Estoque","root","1234");
        }
        catch(SQLException excecao) {
            throw new RuntimeException(excecao);
        }
    }
}
