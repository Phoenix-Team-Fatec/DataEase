
package UserScreen;

import LangChain.LmConnection;
import SQLConnection.InsertDB;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.event.ActionListener;
public class  TelaChat extends javax.swing.JFrame implements ActionListener {

    private String nome;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private JLabel caixa_resposta;
    private JTextField caixa_entrada;
    private JPanel painel_chat = new JPanel();
    private JComboBox usersBD = new JComboBox<>();
    private JButton botao_add_bd = new JButton("+");
    private JLabel texto_Adiciona_bd = new JLabel("Novo Banco de dados");
    JButton botao_enviar;
    private JComboBox db_users = new JComboBox<>();
    private JComboBox db_instance = new JComboBox<>();
    private JLabel texto_db = new JLabel("Banco de Dados:");
    private JLabel texto_instance = new JLabel("Instancia:");
    private JLabel texto_users = new JLabel("Usuario:");

    private JButton side_bar  = new JButton("=");

    private JButton exit_side = new JButton("x");

    public TelaChat(String nome, String senha){
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
        painel_chat.setSize(250, 600);
        painel_chat.setVisible(false);
        painel_chat.setLayout(null);
        painel_chat.setBackground(new Color(44, 10, 200));
        add(painel_chat);

        

        //Caixa Resposta
        caixa_resposta = new JLabel();
        caixa_resposta.setForeground(new Color(255, 255,255));
        caixa_resposta.setBounds(37, 65, 776, 411); // Define a posição (x, y) e o tamanho (largura, altura)
        caixa_resposta.setBorder(new LineBorder(new Color(224, 170, 255)));
        caixa_resposta.setVisible(true);
        add(caixa_resposta);

        //Caixa Entrada
        caixa_entrada = new JTextField();
        caixa_entrada.setBounds(37, 493, 685, 36); // Define a posição (x, y) e o tamanho (largura, altura)
        caixa_entrada.setBackground(new Color(60, 9, 108));
        caixa_entrada.setForeground(Color.white);
        caixa_entrada.setBorder(new LineBorder(new Color(224, 170, 255)));
        caixa_entrada.setVisible(true);
        add(caixa_entrada);

        //Botao_enviar_prompt
        botao_enviar = new JButton("OK");
        botao_enviar.setBounds(737, 495, 76, 32); // Define a posição (x, y) e o tamanho (largura, altura)
        botao_enviar.setBorder(new LineBorder(new Color(224, 170, 255)));
        botao_enviar.setBackground(new Color(157,78,221));
        botao_enviar.addActionListener(this::sendText);
        add(botao_enviar);




        //Dropdown para seleção do banco de dados do usuário e Botão para adição do mesmo


        add(botao_add_bd);
        botao_add_bd.setBounds(340,39,50,15);
        botao_add_bd.addActionListener(this::changeToTelaDB);

        //painel chat
        painel_chat.add(db_instance);
        painel_chat.add(db_users);
        painel_chat.add(texto_db);
        painel_chat.add(texto_instance);
        painel_chat.add(texto_users);
        painel_chat.add(usersBD);
        usersBD.setBounds(40,80,180,20);

        painel_chat.add(texto_db);
        texto_db.setBounds(10,60,100,20);
        texto_db.setForeground(Color.white);

        painel_chat.add(texto_instance);
        texto_instance.setBounds(10,200,100,20);
        texto_instance.setForeground(Color.white);
        db_instance.setBounds(40, 220, 180, 20);

        painel_chat.add(texto_users);
        texto_users.setBounds(10,340,100,20);
        texto_users.setForeground(Color.white);
        db_users.setBounds(40, 360, 180, 20);

        add(side_bar);
        side_bar.setBounds(20,20,50,20);
        side_bar.addActionListener(this::aparecer_side_bar);


        painel_chat.add(exit_side);
        exit_side.setBounds(10,10,30,20);
        exit_side.addActionListener(this:: sair_side_bar);


    }

    private void sair_side_bar(ActionEvent actionEvent) {
        painel_chat.setVisible(false);
        side_bar.setVisible(true);
    }

    private void aparecer_side_bar(ActionEvent actionEvent) {
        painel_chat.setVisible(true);
        side_bar.setVisible(false);
    }

    // Abre a tela de cadastro de DB
    private void changeToTelaDB(ActionEvent actionEvent) {
        telaDB teladb = new telaDB();
        teladb.setNome(this.getNome());
        teladb.setSenha(this.getSenha());


    }

    // Envia o input do usuário para ConnectionDB
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
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new TelaChat("matheus", "");
    }
}

