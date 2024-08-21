package SQLConnection;

import UserScreen.TelaLogin;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static java.sql.DriverManager.getConnection;

public class Cadastros {

    private int idUsuario;
    private String NameDB;

    public void setNameDB(String nameDB) {
        NameDB = nameDB;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    //
    public Connection getConnectionDataEase(){
    // método que conecta com o banco de dados cadastro
       try {
            return DriverManager.getConnection("jdbc:mysql://localhost/DataEase", "root", "******");
        }catch (SQLException e){
           throw new RuntimeException();
       }
    }

 public Cadastros(){
        this.getConnectionDataEase();
 } // constructor, ao criar um objeto já conecta com o DB

    public void userCadastro(String nome, String senha) {
        try (Connection connection = getConnectionDataEase()) {
            String sql = "INSERT INTO usuarios (nome, senha) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setString(2, senha);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Novo usuário cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao cadastrar novo usuário");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "SYSTEM ERROR DATABASE");
        }
    } // cadastra usuários


    public void dataBaseCadastro(String nome_db, int index ){

        String sql = "INSERT INTO banco_de_dados (nome_db, id_usuario) VALUES(?,?)";
        try(Connection connection = getConnectionDataEase()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,nome_db);
            stmt.setInt(2,index);

            int linhas_afetadas = stmt.executeUpdate();
            if (linhas_afetadas == 0){

                JOptionPane.showMessageDialog(null, "SYSTEM ERROR DATABASE");

            }

        }catch(SQLException exception){
            throw new RuntimeException();
        }


    } // cadastra banco de dados


    public void cadastroInstance(String instance, int index){
        String sql = "INSERT INTO instances (nome_instances, id_usuario) VALUES (?, ?)";
        try (Connection connection = getConnectionDataEase();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, instance);
            stmt.setInt(2, index);
            int linhas_afetadas = stmt.executeUpdate();
            if (linhas_afetadas > 0) {
                JOptionPane.showMessageDialog(null, "Instância cadastrada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar instância");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void cadastroUsers(String users, int index){
        String sql = "INSERT INTO users (name_users, id_usuario) VALUES (?, ?)";
        try (Connection connection = getConnectionDataEase();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, users);
            stmt.setInt(2, index);
            int linhas_afetadas = stmt.executeUpdate();
            if (linhas_afetadas > 0) {
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar usuário");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void cadastroPasswords(String password, int index){
        String sql = "INSERT INTO passwords (users_passwords, id_usuario) VALUES (?, ?)";
        try (Connection connection = getConnectionDataEase();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, password);
            stmt.setInt(2, index);
            int linhas_afetadas = stmt.executeUpdate();
            if (linhas_afetadas > 0) {
                JOptionPane.showMessageDialog(null, "Senha cadastrada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar senha");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }



    public int getIdUsuario(String nome, String senha) {
        String sql = "Select id_usuario from usuarios where nome = ? and senha = ?";
        try (Connection connection = getConnectionDataEase()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Verifica se há algum resultado
                idUsuario = rs.getInt("id_usuario");
            }
        }catch (SQLException error){

        }
        return idUsuario;
    }

    // RETORNA OS NOMES DOS BANCOS DE DADOS CADASTRADOS;
    public List<String> getNameDB(String nome, String senha){
        List<String> names = new ArrayList<>();
        String sql = "Select nome_db from banco_de_dados where id_usuario = ?";
        try (Connection connection = getConnectionDataEase()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, getIdUsuario(nome, senha));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("nome_db");
                names.add(name);
            }
        }catch (SQLException error){

        }
        return names;
    }


    public List<String> getInstance(String nome, String senha){
        List<String> instances = new ArrayList<>();
        String sql = "SELECT nome_instances FROM instances WHERE id_usuario = ?";
        try(Connection connection = getConnectionDataEase()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, getIdUsuario(nome, senha));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String name_instance = rs.getString("nome_instances");
                instances.add(name_instance);
            }
        } catch (SQLException error) {
            // Adicione tratamento de exceções, como logging ou relançamento da exceção
            error.printStackTrace(); // Isso pode ajudar a identificar problemas durante a execução
        }
        return instances;
    }


    public List<String> getUser(String nome, String senha){
        List<String> users = new ArrayList<>();
        String sql = "Select name_users from users where id_usuario = ?";

        try(Connection connection = getConnectionDataEase()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,getIdUsuario(nome,senha));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String name_instance = rs.getString("name_users");
                users.add(name_instance);
            }
        }catch (SQLException error) {
            // Adicione tratamento de exceções, como logging ou relançamento da exceção
            error.printStackTrace(); // Isso pode ajudar a identificar problemas durante a execução
        }
        return users ;
    }


    public List<String> getPasswords(String nome, String senha){
        List<String> senhas = new ArrayList<>();
        String sql = "Select users_passwords from passwords where id_usuario = ?";

        try(Connection connection = getConnectionDataEase()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,getIdUsuario(nome,senha));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String name_instance = rs.getString("users_passwords");
                senhas.add(name_instance);
            }
        }catch (SQLException error) {
            // Adicione tratamento de exceções, como logging ou relançamento da exceção
            error.printStackTrace(); // Isso pode ajudar a identificar problemas durante a execução
        }
        return senhas ;
    }





}
