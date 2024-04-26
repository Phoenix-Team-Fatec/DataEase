package UserScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastro extends JFrame implements ActionListener {
    private JLabel labelUsuario, labelSenha, labelBd;
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JTextField campodBd;
    private JButton botaoCadastrar, botaoEntrar, botaoCadastrarBd;

    public TelaCadastro(){
        setTitle("Login");
        setSize(400,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color corFundo = new Color(240,240,240);
        Color corBotao = new Color(51,153,255);

        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBackground(corFundo);
        GridBagConstraints gcb = new GridBagConstraints();
        gcb.insets = new Insets(10,10,10,10);
        gcb.fill = GridBagConstraints.HORIZONTAL;

        Font fonte = new Font("Arial", Font.PLAIN, 14);

        labelUsuario = new JLabel("Usuário:");
        labelUsuario.setFont(fonte);
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

        labelBd = new JLabel("Banco de dados:");
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
        painelPrincipal.add(campodBd, gcb);

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
        gcb.gridx = 0;
        gcb.gridy = 3;
        gcb.gridwidth = 1;
        gcb.anchor = GridBagConstraints.CENTER;
        painelPrincipal.add(botaoCadastrar, gcb);

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.addActionListener(this);;
        botaoEntrar.setBackground(corBotao);
        botaoEntrar.setForeground(Color.WHITE);
        botaoEntrar.setFont(fonte);
        gcb.gridx = 1;
        gcb.gridy = 3;
        gcb.anchor = GridBagConstraints.CENTER;
        painelPrincipal.add(botaoEntrar, gcb);

        add(painelPrincipal);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == botaoCadastrar){
            JOptionPane.showMessageDialog(this, "Cadastro com sucesso!");
        }else if (e.getSource() == botaoEntrar){
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());
            JOptionPane.showMessageDialog(this, "Usuário " + usuario + "\nSenha: " + senha);
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
