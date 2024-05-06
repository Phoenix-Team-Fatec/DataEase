package UserScreen;

import SQLConnection.Cadastros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class telaDB extends JFrame {

    JLabel TextDB = new JLabel();
    JTextField caixa_DB = new JTextField();

    JButton botao_cadastro_db = new JButton();
    private JButton botao_voltar = new JButton("Voltar");

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
        TextDB.setBounds(200, 250, 200, 25);
        TextDB.setText("Insira seu banco de dados");
        TextDB.setForeground(Color.WHITE);
        add(TextDB);

        // JTextField
        caixa_DB.setBounds(200, 300, 400, 25);
        add(caixa_DB);

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
        this.setVisible(false);
    }

    // Cadastra o DB
    private void sendDB(ActionEvent actionEvent) {
        String db_name = caixa_DB.getText();
        Cadastros database = new Cadastros();
        database.dataBaseCadastro(db_name,1);

    }

    public static void main(String[] args) {
        new telaDB();
    }
}
