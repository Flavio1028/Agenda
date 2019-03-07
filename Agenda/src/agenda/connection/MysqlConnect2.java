package agenda.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * @author flavio.rocha
 */
abstract class MysqlConnect2 implements Connectio2 {

	/**
	 * Atributo
	 */
	public Connection connection = null;
	/**
	 * Atributo (LOCALIZAÇÃO DO SERVIDOR)
	 */
	private String url = "jdbc:mysql://db4free.net:3306/";
	/**
	 * Atributo (NOME DO BANCO DE DADOS)
	 */
	private String dbName = "base_dados";
	/**
	 * Atributo (NOME DO DRIVER)
	 */
	private String driver = "com.mysql.cj.jdbc.Driver";
	/**
	 * Atributo (NOME DE USUÁRIO DO BANCO DE DADOS)
	 */
	private String userName = "flavio_rocha";
	/**
	 * Atributo (SENHA DO BANCO DE DADO)
	 */
	private String password = "159Asd753";

	/**
	 * COSNTRUTOR, MEXE COM O BANCO DE DADOS
	 */
	public MysqlConnect2() {
		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(this.url + this.dbName, this.userName, this.password);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro - A agenda não está conectada", "Mensagem - Erro", 0);
			System.err.println("Detalhe do erro: " + e.getCause());
		}

	}

	/**
	 * return Connection
	 */
	public Connection getConnection() {
		return this.connection;
	}

	/**
	 * Fecha a conexao
	 */
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao desconectar.", "ERRO", 0);
			System.err.println("Detalhe do erro: " + e.getCause());
		}
	}
}