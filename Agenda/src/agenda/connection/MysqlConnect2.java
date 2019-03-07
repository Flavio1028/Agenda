package agenda.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

abstract class MysqlConnect2 implements Connectio2
{
   public Connection c; // ATRIBUTO
   
   // COSNTRUTOR, MEXE COM O BANCO DE DADOS 
   public MysqlConnect2()
   {
        c = null;
        String url      = "jdbc:mysql://localhost/"; //  LOCALIZAÇÃO DO SERVIDOR
        String dbName   = "agenda";                 //  NOME DO BANCO DE DADOS
        String driver   = "com.mysql.jdbc.Driver"; //  NOME DO DRIVER
        String userName = "root";                 //  NOME DE USUÁRIO DO BANCO DE DADOS
        String password = "123456";              //  SENHA DO BANCO DE DADOS 
        
         try
         {
            Class.forName(driver);
            c = DriverManager.getConnection(url + dbName, userName, password);
         } catch( Exception e)
         {
            JOptionPane.showMessageDialog(null,"Alerta - A agenda não está conectada","Mensagem - Erro",0);
            e.printStackTrace();
         }
   
   } 
   
   public Connection getConnection()
   {
      return c;
   }
   
   public void closeConnection()
   {
      try
      {
         c.close();
      }catch(Exception e)
      {
         JOptionPane.showMessageDialog(null,"Erro ao desconectar.","ERRO",0);
         //e.printStackTrace();
      }
   }   
   
}