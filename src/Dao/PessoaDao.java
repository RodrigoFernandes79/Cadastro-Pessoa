package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBCUtil.ConexaoJDBC;
import Model.Pessoa;

public class PessoaDao {
	
	public void salvar(Pessoa pessoa) throws ClassNotFoundException {
		
		String sql = "insert into PESSOA (nome, data_nasci, sexo, cpf) values (?,?,?,?)";
		Connection conexao;
		
		try {
			conexao = ConexaoJDBC.getConexao();
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getDataNasci());
			ps.setString(3, pessoa.getSexo());
			ps.setString(4, pessoa.getCPF());
			
			ps.execute();
			//conexao.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		
		public void atualizar (Pessoa pessoa) throws ClassNotFoundException {
			
			String sql = "UPDATE PESSOA SET NOME=?, CPF=?, DATA_NASCI=?, sexo=? WHERE ID_PESSOA=?";
			Connection conexao;
			
			try {
				conexao = ConexaoJDBC.getConexao();
				PreparedStatement ps = conexao.prepareStatement(sql);
				
				ps.setString(1, pessoa.getNome());
				ps.setString(2, pessoa.getCPF());
				ps.setString(3, pessoa.getDataNasci());
				ps.setString(4, pessoa.getSexo());
				ps.setLong(5, pessoa.getId());
				
				ps.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
		}
		
		public void deletar(Long id) throws SQLException, ClassNotFoundException {
			
			String sql = "DELETE FROM PESSOA WHERE ID_PESSOA=?";
			Connection conexao;
			
			try {
				conexao = ConexaoJDBC.getConexao();
				PreparedStatement ps = conexao.prepareStatement(sql);
				
				ps.setLong(1, id);
				
				ps.execute();
				
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
		
		}
		
		public List<Pessoa> listarTodos() throws ClassNotFoundException {
			String sql = "SELECT * FROM PESSOA";
			Connection conexao;
			
			List<Pessoa> listarPessoas = new ArrayList<Pessoa>();
			
			try {
				conexao = ConexaoJDBC.getConexao();
				PreparedStatement ps = conexao.prepareStatement(sql);
				ResultSet resultado = ps.executeQuery();
				
				while(resultado.next()) {
					
					Pessoa pessoa = new Pessoa();
					pessoa.setId(resultado.getLong("ID_PESSOA"));
					pessoa.setNome(resultado.getString("NOME"));
					pessoa.setCPF(resultado.getString("CPF"));
					pessoa.setSexo(resultado.getString("SEXO"));
					pessoa.setDataNasci(resultado.getString("DATA_NASCI"));
					
					listarPessoas.add(pessoa);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return listarPessoas;
			
		}
		
		
			
		
					
}
