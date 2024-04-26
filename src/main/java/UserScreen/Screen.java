package UserScreen;

import LangChain.LmConnection;
import SQLConnection.InsertDB;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.event.ActionListener;
public class Screen extends javax.swing.JFrame implements ActionListener {


     JLabel caixa_resposta;
     JTextField caixa_entrada;
     private JPanel painel_chat = new JPanel();

     private JPanel painel_cadastro = new JPanel();

     JLabel label_usuario, label_senha, label_bd = new JLabel();
     JTextField campo_usuario = new JTextField();
     JTextField campo_bd = new JTextField();
     JPasswordField campo_senha = new JPasswordField();

     JButton botao_cadastrar, botao_entrar, botao_cadastrar_bd = new JButton();

    JButton botao;

    private JComboBox db_users = new JComboBox<>();

    public Screen(){
        ScreenComponents();
    }

    private void ScreenComponents(){
        //Configurações Básicas
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("DataEase");
        setVisible(true);
        setSize(850,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(44, 10, 75));

        //Configurações dos painéis;
        //Painel Chat
        painel_chat.setSize(850, 600);
        painel_chat.setVisible(false);
        painel_chat.setLayout(null);
        painel_chat.setBackground(new Color(44, 10, 75));
        add(painel_chat);

        //Painel Cadastro
        painel_cadastro.setSize(850, 600);
        painel_cadastro.setVisible(true);
        painel_cadastro.setLayout(null);
        painel_cadastro.setBackground(new Color(44, 10, 75));
        add(painel_cadastro);

        //Caixa Resposta
        caixa_resposta = new JLabel();
        caixa_resposta.setForeground(new Color(255, 255,255));
        caixa_resposta.setBounds(37, 65, 776, 411); // Define a posição (x, y) e o tamanho (largura, altura)
        caixa_resposta.setBorder(new LineBorder(new Color(224, 170, 255)));
        caixa_resposta.setVisible(true);

        //Caixa Entrada
        caixa_entrada = new JTextField();
        caixa_entrada.setBounds(37, 493, 685, 36); // Define a posição (x, y) e o tamanho (largura, altura)
        caixa_entrada.setBackground(new Color(60, 9, 108));
        caixa_entrada.setForeground(Color.white);
        caixa_entrada.setBorder(new LineBorder(new Color(224, 170, 255)));
        caixa_entrada.setVisible(true);
        //add(caixa_entrada);

        //Botao_enviar_prompt
        botao = new JButton("OK");
        botao.setBounds(737, 495, 76, 32); // Define a posição (x, y) e o tamanho (largura, altura)
        botao.setBorder(new LineBorder(new Color(224, 170, 255)));
        botao.setBackground(new Color(157,78,221));

        botao.addActionListener(this::sendText);

        painel_chat.add(caixa_resposta);
        painel_chat.add(caixa_entrada);
        painel_chat.add(botao);


        //Configurações tela cadastro;
        GridBagConstraints gcb = new GridBagConstraints();
        gcb.insets = new Insets(10,10,10,10);
        gcb.fill = GridBagConstraints.HORIZONTAL;

        //Usuário
        label_usuario = new JLabel("Usuário:");
        gcb.gridx = 0;
        gcb.gridy = 0;
        gcb.anchor = GridBagConstraints.LINE_END;
        painel_cadastro.add(label_usuario, gcb);

        campo_usuario = new JTextField();
        campo_usuario.setPreferredSize(new Dimension(200, 30));
        gcb.gridx = 1;
        gcb.gridy = 0;
        gcb.anchor = GridBagConstraints.LINE_START;
        painel_cadastro.add(campo_usuario, gcb);

//Senha
        label_senha = new JLabel("Senha:");
        gcb.gridx = 0;
        gcb.gridy = 1;
        gcb.anchor = GridBagConstraints.LINE_END;
        painel_cadastro.add(label_senha, gcb);

        campo_senha = new JPasswordField(); // Use JPasswordField para senhas
        campo_senha.setPreferredSize(new Dimension(200, 30));
        gcb.gridx = 1;
        gcb.gridy = 1;
        gcb.anchor = GridBagConstraints.LINE_START;
        painel_cadastro.add(campo_senha, gcb);

//Banco de dados
        label_bd = new JLabel("Banco de dados:");
        gcb.gridx = 0;
        gcb.gridy = 2;
        gcb.anchor = GridBagConstraints.LINE_END;
        painel_cadastro.add(label_bd, gcb);

        campo_bd = new JTextField();
        campo_bd.setPreferredSize(new Dimension(200, 30));
        gcb.gridx = 1;
        gcb.gridy = 2;
        gcb.anchor = GridBagConstraints.LINE_START;
        painel_cadastro.add(campo_bd, gcb);

//Botão cadastrar
        botao_cadastrar = new JButton("Cadastrar");
        botao_cadastrar.addActionListener(this);
        gcb.gridx = 0;
        gcb.gridy = 3;
        gcb.gridwidth = 1;
        gcb.anchor = GridBagConstraints.CENTER;
        painel_cadastro.add(botao_cadastrar, gcb);

//Botão entrar
        botao_entrar = new JButton("Entrar");
        botao_entrar.addActionListener(this);
        gcb.gridx = 1;
        gcb.gridy = 3;
        gcb.anchor = GridBagConstraints.CENTER;
        painel_cadastro.add(botao_entrar, gcb);


        //Usuário
        /*label_usuario.setText("Usuário");
        gcb.gridx = 0;
        gcb.gridy = 0;
        gcb.anchor = GridBagConstraints.LINE_END;
        painel_cadastro.add(label_usuario, gcb);

        campo_usuario.setPreferredSize(new Dimension(200, 30));
        gcb.gridx = 1;
        gcb.gridy = 0;
        gcb.anchor = GridBagConstraints.LINE_START;
        painel_cadastro.add(campo_usuario, gcb);

        //Senha
        label_senha.setText("Senha:");
        gcb.gridx = 0;
        gcb.gridy = 1;
        gcb.anchor = GridBagConstraints.LINE_END;
        painel_cadastro.add(label_usuario, gcb);

        campo_senha.setPreferredSize(new Dimension(200,30));
        gcb.gridx = 1;
        gcb.gridy = 1;
        gcb.anchor = GridBagConstraints.LINE_START;
        painel_cadastro.add(campo_senha, gcb);

        //Banco de dados
        label_bd.setText("Banco de dados:");
        gcb.gridx = 0;
        gcb.gridy = 2;
        gcb.anchor = GridBagConstraints.LINE_END;
        painel_cadastro.add(label_bd, gcb);

        campo_bd.setPreferredSize(new Dimension(200,30));
        gcb.gridx = 1;
        gcb.gridy = 2;
        gcb.anchor = GridBagConstraints.LINE_START;
        painel_cadastro.add(campo_bd, gcb);

        //Linha pra separar os campos dos botões
        gcb.gridwidth = 2;
        gcb.gridx = 0;
        gcb.gridy = 2;
        painel_cadastro.add(new JLabel(), gcb);

        //Botão cadastrar
        botao_cadastrar.setText("Cadastrar");
        botao_cadastrar.addActionListener(this);
        gcb.gridx = 0;
        gcb.gridy = 3;
        gcb.gridwidth = 1;
        gcb.anchor = GridBagConstraints.CENTER;
        painel_cadastro.add(botao_cadastrar, gcb);

        botao_entrar.setText("Entrar");
        botao_entrar.addActionListener(this);
        gcb.gridx = 1;
        gcb.gridy = 3;
        gcb.anchor = GridBagConstraints.CENTER;
        painel_cadastro.add(botao_entrar, gcb);

        painel_cadastro.add(label_usuario);
        painel_cadastro.add(campo_usuario);
        painel_cadastro.add(label_senha);
        painel_cadastro.add(campo_senha);
        painel_cadastro.add(label_bd);
        painel_cadastro.add(campo_bd);
        painel_cadastro.add(botao_entrar);
        painel_cadastro.add(botao_cadastrar); */

        //db_users
    }



    private void sendText(ActionEvent actionEvent) {
        String entrada = caixa_entrada.getText();
        LmConnection prompt = new LmConnection();
        prompt.setContent(entrada);

        // caixa_resposta.setText(prompt.getPrompt());

        InsertDB consulta = new InsertDB();
        consulta.setSql_prompt(prompt.getPrompt());

        List<String> resultados = consulta.select();

        // Concatenando os resultados em uma única String
        String resultadoFormatado = consulta.resultadoConcatenado(resultados);

        // Definindo a String concatenada como texto do Label
        caixa_resposta.setText(resultadoFormatado);
    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(Screen::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
