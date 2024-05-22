package UserScreen;

import SQLConnection.Cadastros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class telaDB extends JFrame{

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

    JLabel TextDB = new JLabel();
    JTextField caixa_DB = new JTextField();
    JButton botao_cadastro_db = new JButton();
    private JButton botao_voltar = new JButton("Voltar");
    private JLabel texto_instance = new JLabel("Nome da instancia :");
    private JTextField insert_instance = new JTextField();
    private JLabel texto_user = new JLabel("Nome do Usuário :");
    private JTextField insert_user = new JTextField();
    private JLabel texto_dbPassword = new JLabel("Senha do Banco de dados :");
    private JPasswordField insert_db_password = new JPasswordField();

    public telaDB(){

        ScreenComponents();
    }
    private void ScreenComponents(){

        //Configurações básicas da tela
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("DataEase");
        setVisible(true);
        setSize(850,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(44, 10, 75));

        // JLabel "Insira seu banco de dados:"
        TextDB.setBounds(100, 100, 200, 20);
        TextDB.setText("Insira seu banco de dados :");
        TextDB.setForeground(Color.WHITE);
        add(TextDB);

        // JTextField nome_db
        caixa_DB.setBounds(275, 100, 200, 20);
        add(caixa_DB);


        //Instance
        texto_instance.setBounds(100,150,200,20);
        texto_instance.setForeground(Color.white);
        add(texto_instance);
        insert_instance.setBounds(275,150,200,20);
        add(insert_instance);

        //User
        texto_user.setBounds(100,200,200,20);
        texto_user.setForeground(Color.white);
        add(texto_user);
        insert_user.setBounds(275,200,200,20);
        add(insert_user);

        //Password
        texto_dbPassword.setBounds(100,250,200,20);
        texto_dbPassword.setForeground(Color.white);
        add(texto_dbPassword);
        insert_db_password.setBounds(275,250,200,20);
        add(insert_db_password);









        // JButton "Cadastrar"
        botao_cadastro_db.setBounds(300, 350, 200, 30);
        botao_cadastro_db.setText("CADASTRAR");
        botao_cadastro_db.addActionListener(this::sendDB);
        add(botao_cadastro_db);

        //JButton "Voltar"
        botao_voltar.setBounds(100,350,100,30);
        add(botao_voltar);
        botao_voltar.addActionListener(this::voltar);





    }

    // Volta tela do chat
    private void voltar(ActionEvent actionEvent) {
        TelaChat telaChat = new TelaChat(this.getNome(),this.getSenha());
        telaChat.preencherJComboBox(this.getNome(),this.getSenha());
        this.dispose();


    }



    // Cadastra o DB
    private void sendDB(ActionEvent actionEvent) {
        String db_name = caixa_DB.getText();
        String instance = insert_instance.getText();
        String password = insert_db_password.getText();
        String user = insert_user.getText();
        Cadastros database = new Cadastros();

        if(db_name.length() > 1){
            database.dataBaseCadastro(db_name,  database.getIdUsuario(this.getNome(),this.getSenha()));
        }
        if (instance.length() > 1){
            database.cadastroInstance(instance,database.getIdUsuario(this.getNome(),this.getSenha()));
        }
        if (user.length() > 1){
            database.cadastroUsers(user,database.getIdUsuario(this.getNome(),this.getSenha()));
        }
        if(password.length() > 1){
            database.cadastroPasswords(password,database.getIdUsuario(this.getNome(),this.getSenha()));
        }

    }




}
