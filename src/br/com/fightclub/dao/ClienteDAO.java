package br.com.fightclub.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fightclub.model.Cliente;
import br.com.fightclub.utils.ConnectionFactory;
import br.com.fightclub.utils.GenericDAO;

public class ClienteDAO implements GenericDAO<Cliente> {

	@Override
	public List<Cliente> getAll() throws ClassNotFoundException, SQLException {
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Cliente cliente = null;
		
		try {

			dbConnection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM fightclub.CLIENTE";

			preparedStatement = dbConnection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();

			List<Cliente> lista = new ArrayList<Cliente>();

			while (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("ID"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setTelefone(rs.getString("TELEFONE"));
				cliente.setEmpresa(rs.getString("EMPRESA"));
				
				lista.add(cliente);
			}

			return lista;

		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}

	@Override
	public Cliente get(Cliente cliente) throws ClassNotFoundException, SQLException {
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {

			dbConnection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM fightclub.CLIENTE "
					+ "WHERE ID = ?";

			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, cliente.getId());

			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("ID"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setTelefone(rs.getString("TELEFONE"));
				cliente.setEmpresa(rs.getString("EMPRESA"));
			}
			
			return cliente;

		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		
	}

	@Override
	public Cliente save(Cliente cliente) throws ClassNotFoundException,
			SQLException {
		
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		
		try{
			dbConnection = ConnectionFactory.getConnection();

			String sql = "CALL clienteInsert(?, ?, ?, ?)";
			callableStatement = dbConnection.prepareCall(sql);
			
			callableStatement.setString(1, cliente.getNome());
			callableStatement.setString(2, cliente.getEmail());
			callableStatement.setString(3, cliente.getTelefone());
			callableStatement.setString(4, cliente.getEmpresa());
			
			callableStatement.execute();
			
		}catch(SQLException e){
			String erro = e.getMessage();
			System.out.println(erro);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if (callableStatement != null) {
				callableStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		
		return cliente;
	}

	@Override
	public boolean update(Cliente cliente) throws ClassNotFoundException,
			SQLException {
		
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		boolean success = false;
		
		try{
			
			dbConnection = ConnectionFactory.getConnection();

			String sql = "CALL clienteUpdate(?, ?, ?, ?, ?)";
			callableStatement = dbConnection.prepareCall(sql);
			
			callableStatement.setInt(1, cliente.getId());
			callableStatement.setString(2, cliente.getNome());
			callableStatement.setString(3, cliente.getEmail());
			callableStatement.setString(4, cliente.getTelefone());
			callableStatement.setString(5, cliente.getEmpresa());
			
			callableStatement.execute();
			
			success = true;
			
		}catch(SQLException e){
			String erro = e.getMessage();
			System.out.println(erro);
			success = false;
			
		}catch(Exception e){
			e.printStackTrace();
			success = false;
			
		}finally{
			if (callableStatement != null) {
				callableStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		
		return success;
		
	}

	@Override
	public boolean delete(Cliente cliente) throws ClassNotFoundException,
			SQLException {
		
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		
		try{
			
			dbConnection = ConnectionFactory.getConnection();

			String sql = "CALL clienteDelete(?)";
			callableStatement = dbConnection.prepareCall(sql);
			
			callableStatement.setInt(1, cliente.getId());
			
			callableStatement.execute();
			
			return true;
			
		}catch(SQLException e){
			String erro = e.getMessage();
			System.out.println(erro);
			return false;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
			
		}finally{
			if (callableStatement != null) {
				callableStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		
	}

	@Override
	public boolean deleteAll() throws ClassNotFoundException, SQLException {
		
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		List<Cliente> lista = this.getAll();
		String sql;
		
		try {

			dbConnection = ConnectionFactory.getConnection();
			
			for(Cliente c : lista){
				sql = "CALL clienteDelete(?)";
				
				callableStatement = dbConnection.prepareCall(sql);
				callableStatement.setInt(1, c.getId());
				
				callableStatement.execute();
			}
			
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
			
		} finally {
			if (callableStatement != null) {
				callableStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		
	}

}
