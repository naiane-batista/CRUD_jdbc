package ExercicioCrud;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private ConnectionFactory(){
        throw new UnsupportedOperationException();

    }

   //getConnection é um metodo estatico usamos com uma string p conectar
   public static Connection getConnection(){

        Connection connection=null;


        //get classloader é carregador de classes um objeto responsável por carregar classes
       try (InputStream input= parte3.ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties")){

           // 3 - Definir parâmetros para se conectar ao banco de dados MySQL
           Properties prop=new Properties();
           prop.load(input);

           String driver=prop.getProperty("jdbc.driver");
           String dataBaseAddress=prop.getProperty("db.address");
           String dataBaseName=prop.getProperty("db.name");
           String user=prop.getProperty("db.user.login");
           String password=prop.getProperty("db.user.password");
           String timeZone=prop.getProperty("db.timeZone");


           // String urlConnection ="jdbc:mysql://localhost/digital_innovation_one?serverTimezone=UTC";

           // 4 - Construção da string de conexão.
           StringBuilder sb=new StringBuilder("jdbc:")
                   .append(driver).append("://")
                   .append(dataBaseAddress).append("/")
                   .append(dataBaseName)
                   .append(timeZone);

           String connectionUrl=sb.toString();//sb.toString() == "jdbc:mysql://localhost/digital_innovation_one"

           // 5 - Criar conexão usando o DriverManager, passando como parâmetros a string de conexão, usuário e senha do usuário.

           try{
               connection= DriverManager.getConnection(connectionUrl,user,password);
           }catch (SQLException e){
               System.out.println("FALHA ao tentar criar conexão");
               throw new RuntimeException(e);
           }
       }catch (IOException e){
           System.out.println(" FALHA ao tentar carregar aquivos de propriedades");
           e.printStackTrace();
       }
       return connection;

   }

}
