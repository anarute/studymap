
package br.com.jcomputacao.logic;

/**
 * 19/11/2012 17:35:44
 * @author Murilo
 */
public class TransactionException extends Exception {

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(Exception ex) {
        super(ex);
    }

}
