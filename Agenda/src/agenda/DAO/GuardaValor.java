package agenda.DAO;

import javax.swing.JOptionPane;

import agenda.connection.DatabaseConnect;

import java.sql.*;

/**
 * @author flavio.rocha
 */
public class GuardaValor {

	/**
	 * Atributo
	 */
	private PreparedStatement st;
	/**
	 * Atributo
	 */
	private Connection conn;
	/**
	 * Atributo
	 */
	private DatabaseConnect db;

	/**
	 * Construtor
	 */
	public GuardaValor() {
		this.db = new DatabaseConnect();
		this.conn = this.db.getConnection();
	}

	/**
	 * Adiciona valores à agenda.
	 * 
	 * @param des
	 * @param loc
	 * @param data
	 * @param Sta
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean adicionaValor(String des, String loc, String data, int Sta) throws SQLException {
		boolean sucesso = false;
		try {
			String sql = "INSERT INTO dados (descricao,local,data,status) values (?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, des);
			st.setString(2, loc);
			st.setString(3, data);
			st.setInt(4, Sta);
			st.executeUpdate();
			sucesso = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao adicionar os arquivos ao banco de dados.", "Mensagem - Erro",
					0);
			System.err.println("Detalhe do erro: " + e.getCause());
		} finally {
			st.close();
		}
		return sucesso;
	}

	/**
	 * Mostra os itens da agenda
	 * 
	 * @return String
	 * @throws SQLException
	 */
	public String listaItens() throws SQLException {
		String texto = "";
		try {
			String sql = "SELECT * FROM dados ORDER BY codigo";
			st = conn.prepareStatement(sql);
			ResultSet resultSet = st.executeQuery();

			while (resultSet.next() == true) {
				texto = texto + "Código: " + resultSet.getInt("codigo") + "\n";
				texto = texto + "Descrição: " + resultSet.getString("descricao") + "\n";
				texto = texto + "Local: " + resultSet.getString("local") + "\n";
				texto = texto + "Data: " + resultSet.getString("data") + "\n";

				String x = null;
				if (resultSet.getInt("status") == 1) {
					x = "confirmado\n\n";
				} else {
					x = "Não confirmado\n\n";
				}

				texto = texto + "Confimação: " + x + " \n\n";
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao recuperar dados.", "Erro", 0);
			System.err.println("Detalhe do erro: " + e.getCause());
		} finally {
			st.close();
		}
		return texto;
	}

	/**
	 * Remove um item da agenda
	 * 
	 * @param re
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean removerItem(int re) throws SQLException {
		boolean removeu = false;
		try {
			String sql = "DELETE FROM dados WHERE codigo = (?); ";
			st = conn.prepareStatement(sql);
			st.setInt(1, re);
			st.executeUpdate();
			removeu = true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao remover o item", "Erro", 0);
			System.err.println("Detalhe do erro: " + e.getCause());
		} finally {
			st.close();
		}

		return removeu;
	}

	/**
	 * Mostra o item do código informado
	 * 
	 * @param c
	 * @return String
	 * @throws SQLException
	 */
	public String consultaPorCodigo(int c) throws SQLException {

		String texto = "Item não localizado.";
		try {
			String sql = "SELECT * FROM dados WHERE codigo = (?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, c);
			ResultSet resultSet = st.executeQuery();

			if (resultSet.next() == true) {
				texto = "";
				texto = texto + "Código: " + resultSet.getInt("codigo") + "\n";
				texto = texto + "Descrição: " + resultSet.getString("descricao") + "\n";
				texto = texto + "Local: " + resultSet.getString("local") + "\n";
				texto = texto + "Data: " + resultSet.getString("data") + "\n";

				String x = null;
				if (resultSet.getInt("status") == 1) {
					x = "confirmado\n\n";
				} else {
					x = "Não confirmado\n\n";
				}

				texto = texto + "Confimação: " + x;
			}

		} catch (Exception e) {
			System.err.println("Detalhe do erro: " + e.getCause());
		} finally {
			st.close();
		}
		return texto;
	}

	/**
	 * Retorna cada item separadamente
	 * 
	 * @param codigo
	 * @return String
	 * @throws SQLException
	 */
	public String consultaDescricao(int codigo) throws SQLException {
		String texto = "";
		try {
			String sql = "SELECT * FROM dados WHERE codigo = (?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, codigo);
			ResultSet resultSet = st.executeQuery();

			if (resultSet.next() == true) {
				texto = resultSet.getString("descricao");
			}
			st.close();
		} catch (Exception e) {
			System.err.println("Detalhe do erro: " + e.getCause());
		} finally {
			st.close();
		}
		return texto;
	}

	/**
	 * @param codigo
	 * @return String
	 * @throws SQLException
	 */
	public String consultaData(int codigo) throws SQLException {
		String texto = "";
		try {
			String sql = "SELECT * FROM dados WHERE codigo = (?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, codigo);
			ResultSet resultSet = st.executeQuery();

			if (resultSet.next() == true) {
				texto = resultSet.getString("data");
			}
		} catch (Exception e) {
			System.err.println("Detalhe do erro: " + e.getCause());
		} finally {
			st.close();
		}
		return texto;
	}

	/**
	 * @param codigo
	 * @return String
	 * @throws SQLException
	 */
	public String consultaLocal(int codigo) throws SQLException {
		String texto = "";
		try {
			String sql = "SELECT * FROM dados WHERE codigo = (?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, codigo);
			ResultSet resultSet = st.executeQuery();

			if (resultSet.next() == true) {
				texto = resultSet.getString("local");
			}
		} catch (Exception e) {
			System.err.println("Detalhe do erro: " + e.getCause());
		} finally {
			st.close();
		}
		return texto;
	}

	/**
	 * @param codigo
	 * @return
	 * @throws SQLException
	 */
	public int consultaStatus(int codigo) throws SQLException {
		int texto = 0;
		try {
			String sql = "SELECT * FROM dados WHERE codigo = (?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, codigo);
			ResultSet resultSet = st.executeQuery();

			if (resultSet.next() == true) {
				texto = resultSet.getInt("status");
			}
		} catch (Exception e) {
			System.err.println("Detalhe do erro: " + e.getCause());
		} finally {
			st.close();
		}
		return texto;
	}

}