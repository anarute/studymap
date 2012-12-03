package br.com.jcomputacao.dao;

/**
 * 16/05/2010 19:19:49
 * @author Murilo
 */
public class DaoException extends Exception {
    private final String query;
    private Object object;

    public DaoException(String msg) {
        super(msg);
        this.query = null;
    }

    public DaoException(String msg, Throwable cause) {
        super(msg, cause);
        this.query = null;
    }
    
    public DaoException(String msg, String query) {
        super(msg);
        this.query = query;
    }

    public DaoException(String msg, String query, Throwable cause) {
        super(msg, cause);
        this.query = query;
    }

    public DaoException(String msg, String query, Throwable cause, Object obj) {
        super(msg, cause);
        this.query = query;
        this.object = obj;
    }

    /**
     * @return Comando SQL que provavelmente ocasionou o erro
     */
    public String getQuery() {
        return query;
    }
    
    /**
     * @return O objeto Model com os dados que foram passados ao dao
     */
    public Object getObject() {
        return object;
    }

}
