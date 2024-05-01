package LangChain;

import SQLConnection.ConnectionDB;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.localai.LocalAiChatModel;
import java.net.HttpURLConnection;


public class LmConnection  {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private HttpURLConnection conn;

    public HttpURLConnection getConn() {
        return conn;
    }

    public void setConn(HttpURLConnection conn) {
        this.conn = conn;
    }

    public String getPrompt()  {
        ConnectionDB connectionDB = new ConnectionDB();





        ChatLanguageModel model = LocalAiChatModel.builder()
                .baseUrl("http://localhost:1234/v1")
                .modelName("nsql llama 2")
                .temperature(0.9)
                .build();

        





         String languageSql = model.generate(this.getContent() + "\n" + connectionDB.architectureDB()  );
         return languageSql;
    }





    }
