package ar.edu.unlam.talleweb.timelineme.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator; //Importo la interfaz Iterator para iterar el arrayList
import java.util.ArrayList; //Importo la clase ArrayList para poder usar la lista

import ar.edu.unlam.talleweb.timelineme.model.Publicacion;
import ar.edu.unlam.talleweb.timelineme.model.Seguir;

public class PublicacionDaoJdbcImpl implements PublicacionDao{
	
	private static PublicacionDao instance = new PublicacionDaoJdbcImpl();

	public static PublicacionDao getInstance() {
		return instance;
	}

	@Override
	public void insert(Publicacion publicacion) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into publicacion (iId, cComentario, fkEmpresa, fkAgente, dFecha) values (0, ?, ?, ?, ?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			
			statement.setString(1, publicacion.getComentario());
			statement.setInt(2, publicacion.getIdempresa());
			statement.setInt(3, publicacion.getIdagente());
			statement.setString(4, publicacion.getFecha());
			

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
	public void delete(Publicacion publicacion) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from publicacion where iId = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, publicacion.getId());
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
	public Publicacion findById(Integer idPublicacion) throws PersistenceException {
		if (idPublicacion == null) {
			throw new IllegalArgumentException(
					"El id de la publicacion no debe ser nulo");
		}
		Publicacion publicacion = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from publicacion where iId = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setInt(1, idPublicacion);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				publicacion = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return publicacion;
	}

	private Publicacion convertOne(ResultSet resultSet) throws SQLException {
		Publicacion retorno = new Publicacion();

		retorno.setId(resultSet.getInt("iId"));
		retorno.setComentario(resultSet.getString("cComentario"));
		retorno.setIdempresa(resultSet.getInt("fkEmpresa"));
		retorno.setIdagente(resultSet.getInt("fkAgente"));
		retorno.setFecha(resultSet.getString("dFecha"));

		return retorno;
	}
	
	public List<Publicacion> findAllByEmpresa(Integer idEmpresa) throws PersistenceException {
		List<Publicacion> lista = new LinkedList<Publicacion>();
		try {
			String query = "select * from publicacion where fkEmpresa = ?";
			Connection cn = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement statement = cn.prepareStatement(query);
			statement.setInt(1, idEmpresa);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}
	
	
	public List<Publicacion> findAllComments(Integer idAgente) throws PersistenceException {
		List<Publicacion> lista = new LinkedList<Publicacion>();
		try {
			
			//Traigo todos las empresas que sigo.
			SeguirDaoJdbcImpl sigue = new SeguirDaoJdbcImpl();
			List<Seguir> resultados = sigue.findFollow(idAgente);
			
			Iterator<Seguir> results = resultados.iterator();
			
			
			String query = "select * from publicacion where";
			Boolean primeravez = true;
			
			while ( results.hasNext() ) {
				
				Seguir row = results.next();
				
				Seguir objSeguir = sigue.findById(row.getId());
				EmpresaDaoJdbcImpl empresasQueSigo = new EmpresaDaoJdbcImpl();
				
				if(primeravez){
					query =  query + " fkEmpresa = "+objSeguir.idempresaseguida+" ";
					primeravez = false;
				}else{
					query =  query + " AND fkEmpresa = "+objSeguir.idempresaseguida+" ";
					
				}
				//Armo objeto empresa.
				//Empresa objEmpresa  = empresasQueSigo.findById(objSeguir.idempresaseguida);
				//html = html+objEmpresa.nombre;
			}
			
			
			
			
			Connection cn = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement statement = cn.prepareStatement(query);
			
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}

	public List<Publicacion> findAllCommentsGeneral() throws PersistenceException {
		List<Publicacion> lista = new LinkedList<Publicacion>();
		try {
			
			
			
			String query = "select * from publicacion";
				
			
			Connection cn = ConnectionProvider.getInstance().getConnection();
			
			PreparedStatement statement = cn.prepareStatement(query);
			
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}
}