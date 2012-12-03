
package br.com.jcomputacao.logic;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * 19/11/2012 15:16:51
 * @author Murilo
 */
public class ValidationExeption extends Exception {

    public ValidationExeption() {
    }

    public ValidationExeption(Exception ex) {
        super(ex);
    }

    public ValidationExeption(String msg) {
        super(msg);
    }

    public ValidationExeption(String msg, SQLException e) {
        super(msg, e);
    }

    public ValidationExeption(String msg, IOException ex) {
        super(msg, ex);
    }

    public ValidationExeption(String msg, RuntimeException e){
        super(msg, e);
    }

    public ValidationExeption(String msg, SQLException e, String query) {
        super(msg, e);
    }

    public ValidationExeption(String msg, ParseException e) {
        super(msg, e);
    }

    public ValidationExeption(String msg, Exception ex) {
        super(msg, ex);
    }

    public ValidationExeption(String msg, String comando, IOException e) {
        super(msg, e);
    }
    
    public ValidationExeption(String msg, Throwable cause) {
        super(msg, cause);
    }

}
