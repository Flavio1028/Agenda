package agenda.connection;
import java.sql.Connection;

public interface Connectio2
{
   public Connection getConnection();
   public void closeConnection();
}