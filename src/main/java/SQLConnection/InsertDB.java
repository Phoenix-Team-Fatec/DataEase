package SQLConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsertDB {

    List<String> results = new ArrayList<>(); // Cria a lista
    private Connection connection; // Conexão com o DB

    private String sql_prompt; // Comando SQL

    public String getSql_prompt() {
        return sql_prompt;
    }

    public void setSql_prompt(String sql_prompt) {
        this.sql_prompt = sql_prompt;
    }

    // Constructor que conecta o objeto ao DB
    public InsertDB(String instance, String nome_db, String users, String senha){
        this.connection = new ConnectionDB(instance,nome_db,users,senha).getConnection(instance,nome_db,users,senha);
    }

    //recebendo os dados e imprimindo na tela
    public List<String> select() {

        String sql = this.getSql_prompt();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                {
                    StringBuilder row = new StringBuilder();
                    for (int i = 1; i <= columnCount; i++) {
                        if (i > 1) {
                            row.append(" | " + " ");
                        }
                        String columnValue = rs.getString(i);
                        row.append(columnValue);
                    }
                    results.add(row.toString());

                }
            }


            rs.close();
            stmt.close();
        }

        catch (SQLException u) {
            new RuntimeException(u);
            JOptionPane.showMessageDialog(null,"Falha na consulta, verifique o requerimento e tente novamente");
        }
        return results;
    }

    // Retorna resultado formatado para a tela
    public String resultadoConcatenado(List<String> resultados){
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><style>");
        sb.append("table { border-collapse: collapse; width: 100%; }");
        sb.append("th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }");
        sb.append("th { background-color: #f2f2f2; }");
        sb.append("</style></head><body><table>");

        for (String resultado : resultados) {
            sb.append("<tr><td>").append(resultado).append("</td></tr>");
        }

        sb.append("</table></body></html>");
        return sb.toString();
    }

}