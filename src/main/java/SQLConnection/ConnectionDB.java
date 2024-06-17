package SQLConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDB {

    private String instance;
    private String nome_db;
    private String user;
    private String senha;

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getNome_db() {
        return nome_db;
    }

    public void setNome_db(String nome_db) {
        this.nome_db = nome_db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // conexão com o banco de dados do usuário
    public Connection getConnection(String instance, String nome_db, String user, String senha) {
        instance = this.getInstance();
        nome_db = this.getNome_db();
        user = this.getUser();
        senha = this.getSenha();

        try {
            return DriverManager.getConnection("jdbc:mysql://"+instance+"/" + nome_db,user,senha);
        }
        catch(SQLException excecao) {
            JOptionPane.showMessageDialog(null,"Erro");
            throw new RuntimeException(excecao);

        }

    }

    // Constructor para criar objeto já com o DB conectado
    public ConnectionDB(String instance, String nome_db, String user, String senha) {
        this.setInstance(instance);
        this.setNome_db(nome_db);
        this.setUser(user);
        this.setSenha(senha);
        getConnection(this.getInstance(),this.getNome_db(),this.getUser(),this.getSenha());
    }

    // Retorna como String o esquema do DB
    public String architectureDB() {
        StringBuilder result = new StringBuilder();
        try (Connection connection = getConnection(this.getInstance(),this.getNome_db(),this.getUser(),this.getSenha())) {
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


    public List<String> columNames() {
        List<String> result = new ArrayList<>();
        try (Connection connection = getConnection(this.getInstance(),this.getNome_db(),this.getUser(),this.getSenha())) {
            DatabaseMetaData metaData = connection.getMetaData();
            String databaseName = connection.getCatalog();
            ResultSet tables = metaData.getTables(databaseName, null, null, new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");

                ResultSet columns = metaData.getColumns(databaseName, null, tableName, null);

                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    result.add(columnName);
                }
                columns.close();

            }
            tables.close();
        } catch (SQLException excecao) {
            throw new RuntimeException(excecao);
        }
        return result;







    }













}


