package SQLConnection;

import UserScreen.TelaLogin;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import static java.sql.DriverManager.getConnection;

public class Cadastros {

    private Connection connection;
    ConnectionDB conexao;
    public void userCadastro(String nome, String senha) {


        String sql = "INSERT INTO usuarios (nome, senha) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conexao.getConnection().prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);


            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Novo usuário cadastrado com sucesso!");
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar novo usuário");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados!");
        }
    }
}