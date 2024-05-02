package UserScreen;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaCadastro extends JFrame implements ActionListener {
    private JLabel labelNovoUsuario, labelNovaSenha;
    private JTextField campoNovoUsuario;
    private JPasswordField campoNovaSenha;
    private JButton botaoCadastrarNovo, botaoVoltar;

    public TelaCadastro() {
        setTitle("Cadastro - DataEase");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color corFundo = new Color(60, 9, 108);
        Color corBotao = new Color(51, 153, 255);

        JPanel painelCadastro = new JPanel(new GridBagLayout());
        painelCadastro.setBackground(corFundo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fonte = new Font("Arial", Font.PLAIN, 14);

        labelNovoUsuario = new JLabel("Novo Usuário:");
        labelNovoUsuario.setFont(fonte);
        labelNovoUsuario.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        painelCadastro.add(labelNovoUsuario, gbc);

        campoNovoUsuario = new JTextField();
        campoNovoUsuario.setPreferredSize(new Dimension(200, 30));
        campoNovoUsuario.setFont(fonte);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        painelCadastro.add(campoNovoUsuario, gbc);

        labelNovaSenha = new JLabel("Nova Senha:");
        labelNovaSenha.setFont(fonte);
        labelNovaSenha.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        painelCadastro.add(labelNovaSenha, gbc);

        campoNovaSenha = new JPasswordField();
        campoNovaSenha.setPreferredSize(new Dimension(200, 30));
        campoNovaSenha.setFont(fonte);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        painelCadastro.add(campoNovaSenha, gbc);

        //Linha vazia para separar os campos dos botões;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelCadastro.add(new JLabel(), gbc);

        botaoCadastrarNovo = new JButton("Cadastrar");
        botaoCadastrarNovo.addActionListener(this);
        botaoCadastrarNovo.setBackground(corBotao);
        botaoCadastrarNovo.setForeground(Color.WHITE);
        botaoCadastrarNovo.setFont(fonte);
        botaoCadastrarNovo.setPreferredSize(new Dimension(100,25));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        botaoCadastrarNovo.setBackground(new Color(157,78,221));
        botaoCadastrarNovo.setBorder(new LineBorder(new Color(224,170,255)));
        painelCadastro.add(botaoCadastrarNovo, gbc);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(this);
        botaoVoltar.setBackground(corBotao);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setFont(fonte);
        botaoVoltar.setPreferredSize(new Dimension(100,25));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        botaoVoltar.setBackground(new Color(157,78,221));
        botaoVoltar.setBorder(new LineBorder(new Color(224,170,255)));
        painelCadastro.add(botaoVoltar, gbc);

        add(painelCadastro);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoCadastrarNovo) {
            String novoUsuario = campoNovoUsuario.getText();
            String novaSenha = new String(campoNovaSenha.getPassword());

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dataease", "root", "1234")) {
                String sql = "INSERT INTO usuarios (nome, senha) VALUES (?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, novoUsuario);
                    stmt.setString(2, novaSenha);

                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Novo usuário cadastrado com sucesso!");
                        campoNovoUsuario.setText("");
                        campoNovaSenha.setText(""); // Limpa o campo corretamente
                        TelaLogin telaLogin = new TelaLogin();
                        telaLogin.setVisible(true);
                        this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "Falha ao cadastrar novo usuário");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados!");
            }
        } else if (e.getSource() == botaoVoltar) {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
            this.setVisible(false);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaCadastro().setVisible(true);
            }
        });
    }
}
