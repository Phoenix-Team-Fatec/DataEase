package LangChain;

import SQLConnection.ConnectionDB;
import dev.ai4j.openai4j.embedding.EmbeddingModel;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.data.segment.TextSegmentTransformer;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.localai.LocalAiChatModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
                .modelName("nsql")
                .temperature(0.9)
                .build();

        





         String languageSql = model.generate(this.getContent() + "\n" + connectionDB.architectureDB()  );
         return languageSql;
    }





    }
