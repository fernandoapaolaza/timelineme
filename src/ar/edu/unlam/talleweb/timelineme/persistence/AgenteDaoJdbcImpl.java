package ar.edu.unlam.talleweb.timelineme.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.unlam.talleweb.timelineme.model.Agente;

public class AgenteDaoJdbcImpl implements AgenteDao{
	
	private static AgenteDao instance = new AgenteDaoJdbcImpl();

	public static AgenteDao getInstance() {
		return instance;
	}

	@Override
	public void insert(Agente agente) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into agente (iId, cNombre, cEmail, cPassword, fkEmpresa, iActivo, iAdmin) values (0, ?, ?, ?, ?, 1, 0)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			//statement.setInt(1, agente.getId());
			statement.setString(1, agente.getNombre());
			statement.setString(2, agente.getUsername());
			statement.setString(3, agente.getPassword());
			statement.setInt(4, agente.getIdempresa());
			

			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

	@Override
	public void delete(Agente agente) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from agente where iId = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, agente.getId());
			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

	@Override
	public void update(Agente agente) throws PersistenceException {
		try {
			String query = "update agente set cNombre = ?  where iId = ?";

			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, agente.getNombre());
			
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
	}

	public List<Agente> findAll() throws PersistenceException {
		List<Agente> lista = new LinkedList<Agente>();
		try {
			String query = "select * from agente";
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}

	@Override
	public Agente findById(Integer idAgente) throws PersistenceException {
		if (idAgente == null) {
			throw new IllegalArgumentException(
					"El id del agente no debe ser nulo");
		}
		Agente agente = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from agente where iId = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idAgente);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				agente = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return agente;
	}

	private Agente convertOne(ResultSet resultSet) throws SQLException {
		Agente retorno = new Agente();

		retorno.setId(resultSet.getInt("iId"));
		retorno.setNombre(resultSet.getString("cNombre"));
		retorno.setUsername(resultSet.getString("cEmail"));
		retorno.setPassword(resultSet.getString("cPassword"));
		retorno.setIdempresa(resultSet.getInt("fkEmpresa"));

		return retorno;
	}

	public Agente findByName(String username) throws PersistenceException {
		if (username == null) {
			throw new IllegalArgumentException(
					"El nombre de usuario no debe ser nulo");
		}		
		Agente agente = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from agente where cEmail = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				agente = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return agente;
	}

}
