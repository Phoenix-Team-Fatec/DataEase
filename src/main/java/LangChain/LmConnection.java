package LangChain;

import SQLConnection.ConnectionDB;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.localai.LocalAiChatModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class LmConnection  {

    private String content; // entrada do usuário

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private HttpURLConnection conn; // UrlPara conectar com o LmStudio

    public HttpURLConnection getConn() {
        return conn;
    }

    public void setConn(HttpURLConnection conn) {
        this.conn = conn;
    }

    public String getPrompt(ConnectionDB connectionDB)  {
         // cria um objeto

        ChatLanguageModel model = LocalAiChatModel.builder()
                .baseUrl("http://localhost:1234/v1")
                .modelName("nsql")
                .temperature(0.9)
                .build(); // defini o servidor, o nome do modelo, a qualidade da resposta

         String languageSql = model.generate(this.getContent() + "\n" + connectionDB.architectureDB()  ); //envia a entrada do usuário e o esquema do banco de dados do usuário para gerar o SQL
         return languageSql; // retorna o SQL
    }
//____________________________________________

    //Ligar o modelo
    public void ligarmodel (String nameModel){
        String command = "cmd.exe /c lms load " + nameModel;
        // Criação do ProcessBuilder
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Configura o comando a ser executado pelo ProcessBuilder
        processBuilder.command(command.split(" "));

        try {
            // Iniciar o processo
            Process process = processBuilder.start();

            // Capturar a saída do processo
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Lê e imprime cada linha da saída do processo
            while ((line = reader.readLine()) != null) {

            }

            // Esperar o término do processo e obter o código de saída
            int exitCode = process.waitFor();


        } catch (IOException | InterruptedException e) {
            // Captura e imprime exceções que possam ocorrer durante a execução do comando
            e.printStackTrace();

        }
    }

    public void desligarmodel (){
        String command = "cmd.exe /c lms unload --all " ;
        // Criação do ProcessBuilder
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Configura o comando a ser executado pelo ProcessBuilder
        processBuilder.command(command.split(" "));

        try {
            // Iniciar o processo
            Process process = processBuilder.start();

            // Capturar a saída do processo
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Lê e imprime cada linha da saída do processo
            while ((line = reader.readLine()) != null) {

            }

            // Esperar o término do processo e obter o código de saída
            int exitCode = process.waitFor();


        } catch (IOException | InterruptedException e) {
            // Captura e imprime exceções que possam ocorrer durante a execução do comando
            e.printStackTrace();

        }
    }





    }
