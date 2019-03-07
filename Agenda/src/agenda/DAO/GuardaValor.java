package agenda.DAO;
import javax.swing.JOptionPane;

import agenda.connection.DatabaseConnect;

import java.sql.*;

public class GuardaValor
{
   PreparedStatement st;     
   Connection conn;
   DatabaseConnect db;
   
	public GuardaValor()
   {
      db = new DatabaseConnect();
      conn = db.getConnection();
   }   
   
   //Adiciona valores à agenda.
   public boolean adicionaValor(String des,String loc, String data, int Sta) 
   {
      boolean sucesso = false;
      
      try
      {
         String sql = "INSERT INTO dados (descricao,local,data,status) values (?,?,?,?)";
         st = conn.prepareStatement(sql);
         st.setString(1, des);
         st.setString(2, loc);
         st.setString(3, data);
         st.setInt(4, Sta);
         st.executeUpdate();
         st.close();
         sucesso =  true;
         
      } catch(Exception e)
      {
         JOptionPane.showMessageDialog(null,"Erro ao adicionar os arquivos ao banco de dados.","Mensagem - Erro",0);
         //e.printStackTrace();
      }
      return sucesso;
   }
   
   //Mostra os itens da agenda
   public String listaItens()
   {
      String texto = "";
      
      try
      {
         
         String sql = "SELECT * FROM dados ORDER BY codigo";
         st = conn.prepareStatement(sql);
         ResultSet resultSet = st.executeQuery();
         
         while(resultSet.next() == true)
         {
            texto = texto + "Código: " + resultSet.getInt("codigo") + "\n";
            texto = texto + "Descrição: " + resultSet.getString("descricao")+ "\n";
            texto = texto + "Local: " + resultSet.getString("local") + "\n";
            texto = texto + "Data: " + resultSet.getString("data") + "\n";
            
            String x = null;
            if(resultSet.getInt("status") == 1)
            {
               x = "confirmado\n\n";
            } else 
            { 
               x = "Não confirmado\n\n"; 
            }
            
            texto = texto + "Confimação: " + x + " \n\n";
         }
            st.close();
      }catch(Exception e)
      {
         JOptionPane.showMessageDialog(null,"Erro ao recuperar dados.","Erro",0);
         //e.printStackTrace();
      }
      return texto;
   }
   
   //Remove um item da agenda
   public boolean removerItem(int re)
   {
       boolean removeu = false;
       try
       {
            String sql = "DELETE FROM dados WHERE codigo = (?); ";
            st = conn.prepareStatement(sql);
            st.setInt(1, re);
            st.executeUpdate();
            st.close();
            removeu = true;
   
       }  
        catch(Exception e)
       {
         JOptionPane.showMessageDialog(null,"Falha ao remover o item","Erro",0);
         //e.printStackTrace();
       }
                
       return removeu;
   }
   
   
   //Mostra o item do código informado
    public String consultaPorCodigo(int c)
    {
      
      String texto = "Item não localizado.";   
      try
      {
         String sql = "SELECT * FROM dados WHERE codigo = (?)";
         st = conn.prepareStatement(sql);
         st.setInt(1,c);
         ResultSet resultSet = st.executeQuery();
         
         
         if(resultSet.next() == true)
         { 
            texto = "";
            texto = texto + "Código: " + resultSet.getInt("codigo") + "\n";
            texto = texto + "Descrição: " + resultSet.getString("descricao")+ "\n";
            texto = texto + "Local: " + resultSet.getString("local") + "\n";
            texto = texto + "Data: " + resultSet.getString("data") + "\n";
            
            String x = null;
            if(resultSet.getInt("status") == 1)
            {
               x = "confirmado\n\n";
            } else 
            { 
               x = "Não confirmado\n\n"; 
            }
            
            texto = texto + "Confimação: " + x;
         }
         st.close();
      }
      catch(Exception e)
      {
         //e.printStackTrace();
      }
         return texto; 
   }
   
   //Retorna cada item separadamente
    public String consultaDescricao(int codigo)
    {
      String texto = "";  
      try
      {
         String sql = "SELECT * FROM dados WHERE codigo = (?)";
         st = conn.prepareStatement(sql);
         st.setInt(1,codigo);
         ResultSet resultSet = st.executeQuery();
         
         
         if(resultSet.next() == true)
         { 
            texto = resultSet.getString("descricao");
         }
         st.close();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return texto;
   }
   
     public String consultaData(int codigo)
    {
      String texto = "";  
      try
      {
         String sql = "SELECT * FROM dados WHERE codigo = (?)";
         st = conn.prepareStatement(sql);
         st.setInt(1,codigo);
         ResultSet resultSet = st.executeQuery();
         
         
         if(resultSet.next() == true)
         { 
            texto = resultSet.getString("data");
         }
         st.close();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return texto;
   }
   
    public String consultaLocal(int codigo)
    {
      String texto = "";  
      try
      {
         String sql = "SELECT * FROM dados WHERE codigo = (?)";
         st = conn.prepareStatement(sql);
         st.setInt(1,codigo);
         ResultSet resultSet = st.executeQuery();
         
         
         if(resultSet.next() == true)
         { 
            texto = resultSet.getString("local");
         }
         st.close();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return texto;
   }
   
   public int consultaStatus(int codigo)
    {
      int texto = 0;  
      try
      {
         String sql = "SELECT * FROM dados WHERE codigo = (?)";
         st = conn.prepareStatement(sql);
         st.setInt(1,codigo);
         ResultSet resultSet = st.executeQuery();
         
         
         if(resultSet.next() == true)
         { 
            texto = resultSet.getInt("status"); 
         }
         st.close();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return texto;
   }   
      
}