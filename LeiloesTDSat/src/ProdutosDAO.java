/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ProdutosDAO {
    
    Connection conn;
    conectaDAO cd;
    PreparedStatement prep;
    ResultSet resultset;
    
    
    public boolean conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/UC11Atividade02","root", "W@gn1989");
            System.out.println("Conexão efetuada com sucesso");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }
    
    public void desconectar(){
        try {
            conn.close();
            System.out.println("Desconectado");
        } catch (SQLException ex) {
            
        }
    }
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        try{
            
            prep = conn.prepareStatement("INSERT INTO PRODUTOS(nome, valor, status) VALUES(?, ?, ?)");
            
            prep.setString(1, produto.getNome());
            prep.setFloat(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso");
        }
        catch(SQLException ex){
            System.out.println("Erro ao Salvar: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao salvar o produto");
        }
        
    }
    
    public List<ProdutosDTO> listarProdutos(){
        try{
            prep = conn.prepareStatement("SELECT * FROM PRODUTOS");
            resultset = prep.executeQuery();
            List<ProdutosDTO> listagem = new ArrayList<>();
            
            while(resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getFloat("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            return listagem;
        }
        catch(SQLException ex){
            return null;
        }
        
    }
    
    
    
        
}
