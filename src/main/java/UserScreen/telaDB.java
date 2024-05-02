package UserScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class telaDB extends JFrame {

    JLabel TextDB = new JLabel();
    JTextField caixa_DB = new JTextField();

    JButton botao_cadastro_db = new JButton();

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

    }

    private void sendDB(ActionEvent actionEvent) {

    }


    public static void main(String[] args) {
        new telaDB();
    }
}
