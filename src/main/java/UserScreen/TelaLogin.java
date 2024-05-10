package UserScreen;

import SQLConnection.Cadastros;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class TelaLogin extends JFrame implements ActionListener {
    private JLabel labelUsuario, labelSenha, labelBd;
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JTextField campodBd;
    private JButton botaoCadastrar, botaoEntrar, botaoCadastrarBd;

    public TelaLogin(){
        setTitle("DataEase");
        setSize(850,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color corFundo = new Color(60,9,108);
        Color corBotao = new Color(51,153,255);

        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBackground(corFundo);
        GridBagConstraints gcb = new GridBagConstraints();
        gcb.insets = new Insets(10,10,10,10);
        gcb.fill = GridBagConstraints.HORIZONTAL;

        Font fonte = new Font("Arial", Font.PLAIN, 14);

        labelUsuario = new JLabel("Usuário:");
        labelUsuario.setFont(fonte);
        labelUsuario.setForeground(Color.WHITE);
        gcb.gridx = 0;
        gcb.gridy = 0;
        gcb.anchor = GridBagConstraints.LINE_END;
        painelPrincipal.add(labelUsuario, gcb);

        campoUsuario = new JTextField();
        campoUsuario.setPreferredSize(new Dimension(200,30));
        campoUsuario.setFont(fonte);
        gcb.gridx = 1;
        gcb.gridy = 0;
        gcb.anchor = GridBagConstraints.LINE_START;
        painelPrincipal.add(campoUsuario, gcb);

        labelSenha = new JLabel("Senha:");
        labelSenha.setFont(fonte);
        labelSenha.setForeground(Color.WHITE);
        gcb.gridx = 0;
        gcb.gridy = 1;
        gcb.anchor = GridBagConstraints.LINE_END;
        painelPrincipal.add(labelSenha, gcb);

        campoSenha = new JPasswordField();
        campoSenha.setPreferredSize(new Dimension(200, 30));
        campoSenha.setFont(fonte);
        gcb.gridx = 1;
        gcb.gridy = 1;
        gcb.anchor = GridBagConstraints.LINE_START;
        painelPrincipal.add(campoSenha, gcb);

        /*labelBd = new JLabel("Banco de dados:");
        labelBd.setForeground(Color.white);
        labelBd.setFont(fonte);
        gcb.gridx = 0;
        gcb.gridy = 2;
        gcb.anchor = GridBagConstraints.LINE_END;
        painelPrincipal.add(labelBd, gcb);

        campodBd = new JTextField();
        campodBd.setPreferredSize(new Dimension(200, 30));
        campodBd.setFont(fonte);
        gcb.gridx = 1;
        gcb.gridy = 2;
        gcb.anchor = GridBagConstraints.LINE_START;
        painelPrincipal.add(campodBd, gcb);*/

        //Linha vazia para separar os campos dos botões;
        gcb.gridwidth = 2;
        gcb.gridx = 0;
        gcb.gridy = 2;
        painelPrincipal.add(new JLabel(), gcb);

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(this);
        botaoCadastrar.setBackground(corBotao);
        botaoCadastrar.setForeground(Color.WHITE);
        botaoCadastrar.setFont(fonte);
        botaoCadastrar.setPreferredSize(new Dimension(100,25));
        gcb.gridx = 0;
        gcb.gridy = 3;
        gcb.gridwidth = 1;
        gcb.anchor = GridBagConstraints.CENTER;
        botaoCadastrar.setBackground(new Color(157,78,221));
        botaoCadastrar.setBorder(new LineBorder(new Color(224,170,255)));
        painelPrincipal.add(botaoCadastrar, gcb);

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.addActionListener(this);;
        botaoEntrar.setBackground(corBotao);
        botaoEntrar.setForeground(Color.WHITE);
        botaoEntrar.setFont(fonte);
        botaoEntrar.setPreferredSize(new Dimension(100,25));
        gcb.gridx = 1;
        gcb.gridy = 3;
        gcb.anchor = GridBagConstraints.CENTER;
        botaoEntrar.setBackground(new Color(157,78,221));
        botaoEntrar.setBorder(new LineBorder(new Color(224,170,255)));
        painelPrincipal.add(botaoEntrar, gcb);

        add(painelPrincipal);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoCadastrar) {
            abrirTelaCadastro();
        } else if (e.getSource() == botaoEntrar) {
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            // Aqui você adiciona a lógica para verificar se o usuário está cadastrado no banco de dados
            boolean usuarioCadastrado = verificarUsuario(usuario, senha); // Supondo que verificarUsuario é um método que verifica se o usuário existe

            if (usuarioCadastrado) {
                JOptionPane.showMessageDialog(this, "Usuário " + usuario + " logado com sucesso!");
                abrirTelaChat();
            } else {
                int opcao = JOptionPane.showConfirmDialog(this, "Usuário não encontrado. Deseja se cadastrar?", "Usuário não encontrado", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION) {
                    abrirTelaCadastro();
                }
            }
        }
    }

    public void abrirTelaChat(){
        TelaChat telaChat = new TelaChat(campoUsuario.getText());
        telaChat.setVisible(true);
        this.setVisible(false);
    }

    private boolean verificarUsuario(String usuario, String senha){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/DataEase", "root", "1234")){
            String sql = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";
            try(PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, usuario);
                stmt.setString(2, senha);
                Cadastros cadastros = new Cadastros();
                JOptionPane.showMessageDialog(null, "ID: "+cadastros.getIdUsuario(usuario, senha));
                JOptionPane.showMessageDialog(null, "NOME DB: "+cadastros.getNameDB(usuario, senha));

                try(ResultSet rs = stmt.executeQuery()){
                    return rs.next();
                }
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,"Erro ao conectar ao banco de dados!");
            return false;
        }
    }
    public void abrirTelaCadastro(){
        TelaCadastro telaCadastro = new TelaCadastro();
        telaCadastro.setVisible(true);
        this.setVisible(false); //oculta a tela de login ao abrir a tela cadastro;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }
}
