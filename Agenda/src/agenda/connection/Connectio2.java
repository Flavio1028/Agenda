package agenda.connection;

import java.sql.Connection;

/**
 * @author flavio.rocha
 */
public interface Connectio2 {

	/**
	 * @return Connection
	 */
	public Connection getConnection();

	/**
	 * Metodo
	 */
	public void closeConnection();
}