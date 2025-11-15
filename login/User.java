package login;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    // conexão com o banco
    public Connection conectarDB() {
        Connection conn = null;
        try { // tenta fazer a conexão
            Class.forName("com.mysql.cj.jdbc.Driver");
            // dados de conexão
            String url = "jdbc:mysql://localhost:3306/usuarios";
            String usuario = "root";
            String senha = "";
            // faz a conexão do DB
            conn = DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
        } // Falha na conexão
        return conn; // retorna a conexão bem sucedida, ou não
    }

    public String nome = ""; // recebe o nome do usuario
    public boolean result = false; // resultado do login
    // verifica se o usuario existe

    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        Connection conn = conectarDB();
        // INSTRUÇÃO SQL
        sql += "select nome from usuario ";
        sql += "where nome = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "';";
        try {
            Statement st = conn.createStatement(); // cria o statement que conversa com o DB
            ResultSet rs = st.executeQuery(sql); // executa e retorna o resultado
            if (rs.next()) { // se existir usuario login = valido
                result = true;
                nome = rs.getString("nome");
            }
        } catch (Exception e) {
        }
        return result;
    }

}