package br.com.fightclub.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fightclub.model.Administrador;
import br.com.fightclub.utils.ConnectionFactory;
import br.com.fightclub.utils.GenericDAO;

public class AdministradorDAO implements GenericDAO<Administrador> {

	@Override
	public List<Administrador> getAll() throws ClassNotFoundException,
			SQLException {
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Administrador admin = null;
		
		try {

			dbConnection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM fightclub.ADMINISTRADOR";

			preparedStatement = dbConnection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();

			List<Administrador> lista = new ArrayList<Administrador>();

			while (rs.next()) {
				admin = new Administrador();
				admin.setLogin(rs.getString("LOGIN"));
				admin.setSenha(rs.getString("SENHA"));
				
				lista.add(admin);
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
	public Administrador get(Integer id) throws ClassNotFoundException,
			SQLException {
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Administrador admin = null;
		
		try {

			dbConnection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM fightclub.ADMINISTRADOR"
					+ "WHERE ID = ?";

			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				admin = new Administrador();
				admin.setLogin(rs.getString("LOGIN"));
				admin.setSenha(rs.getString("SENHA"));
			}

			return admin;

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
	public Administrador save(Administrador admin)
			throws ClassNotFoundException, SQLException {
		
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		
		try{
			dbConnection = ConnectionFactory.getConnection();

			String sql = "CALL administradorInsert(?, ?)";
			callableStatement = dbConnection.prepareCall(sql);
			
			callableStatement.setString(1, admin.getLogin());
			callableStatement.setString(2, admin.getSenha());
			
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
		
		return admin;
		
	}

	@Override
	public boolean update(Administrador admin) throws ClassNotFoundException,
			SQLException {

		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		
		try{
			
			dbConnection = ConnectionFactory.getConnection();

			String sql = "CALL administradorUpdate(?, ?)";
			callableStatement = dbConnection.prepareCall(sql);
			
			callableStatement.setString(1, admin.getLogin());
			callableStatement.setString(2, admin.getSenha());
			
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
	public boolean delete(Administrador admin) throws ClassNotFoundException,
			SQLException {

		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		
		try{
			
			dbConnection = ConnectionFactory.getConnection();

			String sql = "CALL administradorDelete(?)";
			callableStatement = dbConnection.prepareCall(sql);
			
			callableStatement.setString(1, admin.getLogin());
			
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
		List<Administrador> lista = this.getAll();
		String sql;
		
		try {
			
			dbConnection = ConnectionFactory.getConnection();
			
			for(Administrador a : lista){
				sql = "CALL administradorDelete(?)";
				
				callableStatement = dbConnection.prepareCall(sql);
				callableStatement.setString(1, a.getLogin());
				
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
