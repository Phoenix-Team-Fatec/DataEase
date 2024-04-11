package UserScreen;

import LangChain.LmConnection;
import SQLConnection.InsertDB;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Screen extends javax.swing.JFrame{


     JLabel caixa_resposta;
     JTextField caixa_entrada;


    JButton botao;

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

        //Caixa Resposta
        caixa_resposta = new JLabel();
        caixa_resposta.setForeground(new Color(255, 255,255));
        caixa_resposta.setBounds(37, 65, 776, 411); // Define a posição (x, y) e o tamanho (largura, altura)
        caixa_resposta.setBorder(new LineBorder(new Color(224, 170, 255)));
        add(caixa_resposta);



        //Caixa Entrada
        caixa_entrada = new JTextField();
        caixa_entrada.setBounds(37, 493, 685, 36); // Define a posição (x, y) e o tamanho (largura, altura)
        caixa_entrada.setBackground(new Color(60, 9, 108));
        caixa_entrada.setForeground(Color.white);
        caixa_entrada.setBorder(new LineBorder(new Color(224, 170, 255)));
        add(caixa_entrada);


        //Botao
        botao = new JButton("OK");
        botao.setBounds(737, 495, 76, 32); // Define a posição (x, y) e o tamanho (largura, altura)
        botao.setBorder(new LineBorder(new Color(224, 170, 255)));
        botao.setBackground(new Color(157,78,221));
        add(botao);

        botao.addActionListener(this::sendText);


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
        StringBuilder sb = new StringBuilder();
        for (String resultado : resultados) {
            sb.append(resultado).append("\n"+" "); // Adicionando cada resultado, separando por nova linha
        }
        String resultadoConcatenado = sb.toString();

        // Definindo a String concatenada como texto do Label
        caixa_resposta.setText(resultadoConcatenado);
    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(Screen::new);
    }

}
