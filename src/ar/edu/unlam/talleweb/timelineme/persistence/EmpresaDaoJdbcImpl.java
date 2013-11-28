package ar.edu.unlam.talleweb.timelineme.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.unlam.talleweb.timelineme.model.Empresa;

public class EmpresaDaoJdbcImpl implements EmpresaDao{
	
	private static EmpresaDao instance = new EmpresaDaoJdbcImpl();

	public static EmpresaDao getInstance() {
		return instance;
	}

	

	@Override
	public Empresa findById(Integer idEmpresa) throws PersistenceException {
		if (idEmpresa == null) {
			throw new IllegalArgumentException(
					"El id de la empresa no debe ser nulo");
		}
		Empresa empresa = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from empresa where iId = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idEmpresa);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				empresa = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return empresa;
	}

	private Empresa convertOne(ResultSet resultSet) throws SQLException {
		Empresa retorno = new Empresa();

		retorno.setId(resultSet.getInt("iId"));
		retorno.setNombre(resultSet.getString("cNombre"));
		retorno.setRazon(resultSet.getString("cRazonsocial"));
		return retorno;
	}
	
	

	
}