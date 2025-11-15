# Planilha de Testes

Planilha: https://1drv.ms/x/c/c9374373c8f226c1/EZj4UkdKS_ZKjwGIaPfKun0BynOiAtCE9y4xC4-SIiKQgg?e=NR7fsP

![Screenshot_1](https://github.com/user-attachments/assets/976bd795-0463-4133-a265-2803a5779186)

# Notação de Grafo de Fluxo

```
package login;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User { //N1
    // conexão com o banco
    public Connection conectarDB(){ //N2
        Connection conn = null;
        try{ //tenta fazer a conexão N3
            Class.forName("com.mysql.cj.jdbc.Driver"); //N4
            //dados de conexão //N5
            String url = "jdbc:mysql://localhost:3306/usuarios";
            String usuario = "root";
            String senha = "";
            //faz a conexão do DB
            conn = DriverManager.getConnection(url, usuario, senha); //N6
        }catch (Exception e) { } //Falha na conexão N7
        return conn; // retorna a conexão bem sucedida, ou não N8
    }
    
    public String nome=""; //recebe o nome do usuario
    public boolean result = false; //resultado do login
    //verifica se o usuario existe
    public boolean verificarUsuario (String login, String senha) { //N9
        String sql = "";
        Connection conn = conectarDB(); //N10
        // INSTRUÇÃO SQL
        sql += "select nome from usuario ";
        sql += "where nome = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "';" ;
        try { //N11
            Statement st = conn.createStatement(); // cria o statement que conversa com o DB N12
            ResultSet rs = st.executeQuery(sql); // executa e retorna o resultado N13
            if (rs.next()) { // se existir usuario login = valido //N14
                result = true; //N15
                nome = rs.getString("nome");
            }
        } catch (Exception e) { } //N16
        return result; //N17
    }

}
```
Representação gráfica

![N1](https://github.com/user-attachments/assets/f3caa19c-3599-4b1b-8f2f-16fe6a61b39c)

# Complexidade ciclomática

```
M = E - N + 2P

Arestas = 18
Nós = 17
Componentes = 2

18 - 17  + 2 * 2 = 5
```
## Caminhos Básicos

### Método conectarDB():
```
N1 → N2 → N3 → N4 → N5 → N6 → N8 (conexão OK)

N1 → N2 → N3 → N7 → N8 (conexão falhou)
```
### Método verificarUsuario():
```
N9 → N10 → N11 → N12 → N13 → N14(sim) → N15 → N17

N9 → N10 → N11 → N12 → N13 → N14(não) → N17

N9 → N10 → N11 → N16 → N17
```
