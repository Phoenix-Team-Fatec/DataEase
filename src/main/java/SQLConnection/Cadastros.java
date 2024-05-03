package SQLConnection;

import UserScreen.TelaLogin;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import static java.sql.DriverManager.getConnection;

public class Cadastros {

    public void userCadastro(String nome, String senha) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dataease", "root", "1234")) {
            String sql = "INSERT INTO usuarios (nome, senha) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados!");
        }
    }
}