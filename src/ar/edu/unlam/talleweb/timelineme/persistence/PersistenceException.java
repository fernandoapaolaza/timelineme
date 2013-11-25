package ar.edu.unlam.talleweb.timelineme.persistence;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = -3203899399754071316L;

	public PersistenceException() {
		super();
	}

	public PersistenceException(String msg) {
        super(msg);
    }

	public PersistenceException(Exception exception) {
		super(exception);
	}

}
