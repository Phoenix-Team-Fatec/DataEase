package UserScreen;

import LangChain.LmConnection;
import SQLConnection.Cadastros;
import SQLConnection.ConnectionDB;
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
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private JLabel caixa_resposta;
    private JTextField caixa_entrada;
    private JPanel painel_chat = new JPanel();
    private JComboBox usersBD = new JComboBox<String>();
    private JButton botao_add_bd = new JButton("+");
    private JLabel texto_Adiciona_bd = new JLabel("Novo Banco de dados");
    JButton botao_enviar;
    private JComboBox db_users = new JComboBox<String>();
    private JComboBox db_instance = new JComboBox<String>();
    private JComboBox db_passwords = new JComboBox<String>();
    private JLabel texto_db = new JLabel("Banco de Dados:");
    private JLabel texto_instance = new JLabel("Instancia:");
    private JLabel texto_users = new JLabel("Usuario:");
    private JLabel texto_passwords = new JLabel("Senha:");

    //Icone Jbutton
    ImageIcon close_icon = new ImageIcon("C:\\Users\\xgust\\DataEase\\src\\main\\java\\UserScreen\\botsair.png");

    ImageIcon open_sidebar = new ImageIcon("C:\\Users\\xgust\\DataEase\\src\\main\\java\\UserScreen\\menu1.png");

    private JButton side_bar  = new JButton(open_sidebar);

    private JButton exit_side = new JButton(close_icon);


    public TelaChat(String nome, String senha){
        ScreenComponents();
        this.setNome(nome);
        this.setSenha(senha);
        this.preencherJComboBox(this.getNome(),this.getSenha());

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
        painel_chat.setBackground(new Color(86, 28, 122));
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
        botao_enviar.setForeground(Color.white);
        add(botao_enviar);


        //Ir tele cadastro
        texto_Adiciona_bd.setBounds(625,30,200,20);
        texto_Adiciona_bd.setForeground(Color.white);
        add(texto_Adiciona_bd);




        //Dropdown para seleção do banco de dados do usuário e Botão para adição do mesmo

        add(botao_add_bd);
        Font fonte = botao_add_bd.getFont();
        float tamanhoFonte = fonte.getSize() + 12; // Aumenta 2 pontos
        botao_add_bd.setFont(fonte.deriveFont(tamanhoFonte));
        botao_add_bd.setBounds(760,25,50,25);
        botao_add_bd.addActionListener(this::changeToTelaDB);
        botao_add_bd.setBorder(new LineBorder(new Color(224, 170, 255)));
        botao_add_bd.setBackground(new Color(157,78,221));
        botao_add_bd.setForeground(Color.white);

        //painel chat
        painel_chat.add(db_instance);
        painel_chat.add(db_users);
        painel_chat.add(texto_db);
        painel_chat.add(texto_instance);
        painel_chat.add(texto_users);
        painel_chat.add(usersBD);
        painel_chat.add(texto_passwords);
        painel_chat.add(db_passwords);
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

        painel_chat.add(texto_passwords);
        texto_passwords.setBounds(10,450,100,20);
        texto_passwords.setForeground(Color.white);
        db_passwords.setBounds(40, 470, 180, 20);

        add(side_bar);
        side_bar.setBounds(20,20,32,32);
        side_bar.addActionListener(this::aparecer_side_bar);


        painel_chat.add(exit_side);
        exit_side.setBounds(10,10,30,30);
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

    public void preencherJComboBox(String nome, String senha){
        Cadastros preenche = new Cadastros();

        List<String> dbNames = preenche.getNameDB(nome, senha);
        List<String> instances = preenche.getInstance(nome, senha);
        List<String> users = preenche.getUser(nome, senha);
        List<String> passwords = preenche.getPasswords(nome, senha);

        //Limpar os JComboBox
        usersBD.removeAllItems();
        db_instance.removeAllItems();
        db_users.removeAllItems();
        db_passwords.removeAllItems();

        //Adiciona os itens recuperados aos JComboBox
        for (String dbName : dbNames){
            usersBD.addItem(dbName);
        }
        for (String instace : instances){
            db_instance.addItem(instace);
        }
        for(String user : users){
            db_users.addItem(user);
        }
        for(String password: passwords){
            db_passwords.addItem(password);
        }
    }

    // Abre a tela de cadastro de DB
    private void changeToTelaDB(ActionEvent actionEvent) {
        telaDB teladb = new telaDB();
        teladb.setNome(this.getNome());
        teladb.setSenha(this.getSenha());

        this.dispose();

    }

    // Envia o input do usuário para ConnectionDB
    private void sendText(ActionEvent actionEvent) {
        String entrada = caixa_entrada.getText();
        LmConnection prompt = new LmConnection();
        prompt.setContent(entrada);

        //Selecionar o seu banco de dados
        Cadastros cadastros = new Cadastros();
        ConnectionDB connectionDB = new ConnectionDB(db_instance.getSelectedItem().toString(),usersBD.getSelectedItem().toString(),db_users.getSelectedItem().toString(),db_passwords.getSelectedItem().toString());

        // caixa_resposta.setText(prompt.getPrompt());

        InsertDB consulta = new InsertDB(db_instance.getSelectedItem().toString(),usersBD.getSelectedItem().toString(),db_users.getSelectedItem().toString(),db_passwords.getSelectedItem().toString());
        consulta.setSql_prompt(prompt.getPrompt(connectionDB));

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
        new TelaChat("","");
    }


}

