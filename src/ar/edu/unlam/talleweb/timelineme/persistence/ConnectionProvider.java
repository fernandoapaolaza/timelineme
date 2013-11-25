package ar.edu.unlam.talleweb.timelineme.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	private static ConnectionProvider instance;
	private static Connection connection = null;

	private String URL = "jdbc:mysql://localhost:3306/timelineme";
	private String USER="root";
	private String PASSWORD="";
	private String DRIVER_CLASS="com.mysql.jdbc.Driver";
	private ConnectionProvider() throws PersistenceException {
		
		
		try {
			Class.forName(this.DRIVER_CLASS);
		} catch (Exception classNotFoundException) {
			throw new PersistenceException(classNotFoundException);
		}
	}

	public static ConnectionProvider getInstance() throws PersistenceException {
		if (instance == null) {
			instance = new ConnectionProvider();
		}
		return instance;
	}

	public Connection getConnection() throws PersistenceException {

		try {
			//this.closeConnection();
			connection = DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
			connection.setAutoCommit(false);
		} catch (Exception exception) {
			throw new PersistenceException(exception);
		}
		return connection;
	}

	public void closeConnection() throws PersistenceException {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

	public void rollback() throws PersistenceException {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

}