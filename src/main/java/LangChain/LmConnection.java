package LangChain;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.localai.LocalAiChatModel;
public class LmConnection  {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrompt(){
        ChatLanguageModel model = LocalAiChatModel.builder()
                .baseUrl("http://localhost:1234/v1")
                .modelName("nsql")
                .temperature(0.9)
                .build();


         String languageSql = model.generate(this.getContent());
         return languageSql;
    }

}
