package SQLConnection;

import UserScreen.TelaLogin;

import javax.swing.*;
import java.sql.*;


import static java.sql.DriverManager.getConnection;

public class Cadastros {

    private int idUsuario;

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
            return DriverManager.getConnection("jdbc:mysql://localhost/DataEase", "root", "fatec");
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


    public void dataBaseCadastro(String nome_db, String usuario, String instance_name, String password_bd, int index){

        String sql = "INSERT INTO banco_de_dados (nome_db, usuario, instance_name, password_bd, id_usuario) VALUES(?,?,?,?,?)";
        try(Connection connection = getConnectionDataEase()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,nome_db);
            stmt.setString(2,usuario);
            stmt.setString(3,instance_name);
            stmt.setString(4,password_bd);
            stmt.setInt(5,index);

            int linhas_afetadas = stmt.executeUpdate();
            if (linhas_afetadas > 0){
                JOptionPane.showMessageDialog(null, "Banco de dados cadastrado com sucesso");
            }else{
                JOptionPane.showMessageDialog(null, "SYSTEM ERROR DATABASE");

            }

        }catch(SQLException exception){
            throw new RuntimeException();
        }


    } // cadastra banco de dados


    public int getIdUsuario(String nome, String senha) {
        String sql = "Select idusuarios from usuarios where nome = ? and senha = ?";
        try (Connection connection = getConnectionDataEase()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Verifica se há algum resultado
                idUsuario = rs.getInt("idusuarios");
            }
        }catch (SQLException error){

        }
        return idUsuario;
    }
}