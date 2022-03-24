package parte2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionJDBC {
    public static void main(String[] args) {


       // String urlConnection ="jdbc:mysql://localhost/digital_innovation_one?serverTimezone=UTC";

       //conexao mais elegante e mais usado dia dia
        //2- Definir parâmetros para se conectar ao banco de dados MySQL.
        String driver = "mysql";
        String dataBaseAddress = "localhost";
        String dataBaseName = "digital_innovation_one";
        String user = "root";
        String password = "1234";
        String timeZone="?serverTimezone=UTC";

        // 3 - Construção da string de conexão.
        StringBuilder sb=new StringBuilder("jdbc:")
            .append(driver).append("://")       //apend aloca novas strings concatenadas para o mesmo objeto,
                .append(dataBaseAddress).append("/")
                .append(dataBaseName)
                .append(timeZone);

        String connectionUrl=sb.toString();

        try(Connection conn=DriverManager.getConnection(connectionUrl,user,password)){
            System.out.println("SUCESSO ao se conectar ao banco MySQL!");
        }catch (SQLException e){
            System.out.println("FALHA ao se conectar ao banco MySQL!");
            e.printStackTrace();
        }








        /*conexao com o bd atualmente
        try {
            Connection conn=DriverManager.getConnection(urlConnection,"root","963442");
            System.out.println("Sucesso");
        }catch (Exception e){
            System.out.println("Falha");
        }*/

        /*conexao com o banco de dados da forma antiga

          Connection conn=null;
        try {
             conn= DriverManager.getConnection(urlConnection,"root","963442");   ///url banco,usuario,senha
            System.out.println("Sucesso");
        }catch (Exception e){
            e.printStackTrace();//detalha o erro ;
            System.out.println(e);
            System.out.println("Falha"+e.getMessage());
        }
        finally {
            conn.close();
        }*/






    }
}
