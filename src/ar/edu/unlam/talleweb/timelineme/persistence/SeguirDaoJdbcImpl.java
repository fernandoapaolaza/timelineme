package ar.edu.unlam.talleweb.timelineme.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.unlam.talleweb.timelineme.model.Empresa;
import ar.edu.unlam.talleweb.timelineme.model.Publicacion;
import ar.edu.unlam.talleweb.timelineme.model.Seguir;

public class SeguirDaoJdbcImpl implements SeguirDao{
	
	private static SeguirDao instance = new SeguirDaoJdbcImpl();

	public static SeguirDao getInstance() {
		return instance;
	}
	
	
	@Override
	public void insert(Seguir seguir) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into seguir (iId, fkSeguidor, fkSeguido, dFecha) values (0, ?, ?, ?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			
			statement.setInt(1, seguir.getIdseguidor());
			statement.setInt(2, seguir.getIdempresaseguida());
			statement.setString(3, seguir.getFecha());
			
			

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
	public void delete(Seguir seguir) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "delete from seguir where fkSeguidor=? and fkSeguido=?";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			
			statement.setInt(1, seguir.getIdseguidor());
			statement.setInt(2, seguir.getIdempresaseguida());
			

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
	public Seguir findById(Integer idseguir) throws PersistenceException {
		if (idseguir == null) {
			throw new IllegalArgumentException(
					"El id de seguimiento no debe ser nulo");
		}
		Seguir seguir = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from seguir where iId = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idseguir);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				seguir = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return seguir;
	}
	
	private Seguir convertOne(ResultSet resultSet) throws SQLException {
		Seguir retorno = new Seguir();

		retorno.setId(resultSet.getInt("iId"));
		retorno.setIdseguidor(resultSet.getInt("fkSeguidor"));
		retorno.setIdempresaseguida(resultSet.getInt("fkSeguido"));
		return retorno;
	}
	
	public List<Seguir> findFollow(Integer idAgente) throws PersistenceException {
		List<Seguir> lista = new LinkedList<Seguir>();
		try {
			String query = "select * from seguir where fkSeguidor = ?";
			Connection cn = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement statement = cn.prepareStatement(query);
			statement.setInt(1, idAgente);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}
	
	public Boolean sigue(Integer idAgente, Integer idEmpresa) throws PersistenceException {
		Boolean encuentra = false;
		try {
			String query = "select * from seguir where fkSeguidor = ? and fkSeguido = ?";
			Connection cn = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement statement = cn.prepareStatement(query);
			statement.setInt(1, idAgente);
			statement.setInt(2, idEmpresa);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				encuentra=true;
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return encuentra;
	}

	
}