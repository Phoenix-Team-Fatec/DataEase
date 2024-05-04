package SQLConnection;

import UserScreen.TelaLogin;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import static java.sql.DriverManager.getConnection;

public class Cadastros {

    public Connection getConnectionDataEase(){

       try {
            return DriverManager.getConnection("jdbc:mysql://localhost/DataEase", "root", "fatec");
        }catch (SQLException e){
           throw new RuntimeException();
       }
    }

 public Cadastros(){
        this.getConnectionDataEase();
 }

    public void userCadastro(String nome, String senha, int id_banco_dados ) {
        try (Connection connection = getConnectionDataEase()) {
            String sql = "INSERT INTO usuarios (nome, senha, id_banco_dados) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setString(2, senha);
                stmt.setInt(3, id_banco_dados);

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
    }


    public void dataBaseCadastro(String nome_db, int index){

        String sql = "INSERT INTO banco_de_dados (nome_db, id_usuario) VALUES(?,?)";
        try(Connection connection = getConnectionDataEase()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,nome_db);
            stmt.setInt(2,index);

            int linhas_afetadas = stmt.executeUpdate();
            if (linhas_afetadas > 0){
                JOptionPane.showMessageDialog(null, "Banco de dados cadastrado com sucesso");
            }else{
                JOptionPane.showMessageDialog(null, "SYSTEM ERROR DATABASE");

            }

        }catch(SQLException exception){
            throw new RuntimeException();
        }


    }

}