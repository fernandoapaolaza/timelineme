package ar.edu.unlam.talleweb.timelineme.persistence;

import java.sql.Connection;

public interface Transaction {
    
    public Connection getConnection() throws PersistenceException;
    
    public void begin() throws PersistenceException;

    public void commit() throws PersistenceException;

    public void rollback() throws PersistenceException;
    
}
