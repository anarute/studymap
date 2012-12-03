package br.com.jcomputacao.logic;

import br.com.jcomputacao.dao.DaoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * 13/08/2012 19:38:52
 * @author Murilo
 */
public class TransactionLogicHelper {
    
    protected boolean rolledback = false;
    
    protected final void rollback(Connection conn, java.lang.Exception ex, boolean wasAutocomit) throws TransactionException, DaoException {
        if (conn != null) {
            try {
                String lastRollBack = conn.getClientInfo("last.rollback");
                if (lastRollBack != null) {
                    long lrb = Long.parseLong(lastRollBack);
                    long diff = Calendar.getInstance().getTimeInMillis() - lrb;
                    System.out.println("last rollback=" + lastRollBack + " diff : " + diff);
                    System.out.flush();
                    if (diff < 5) {
                        return;
                    }
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.err);
                System.err.flush();
            }
        }
        if (conn != null && wasAutocomit) {
            try {
                conn.rollback();
                rolledback = true;
                String lastRollBack = Long.toString(Calendar.getInstance().getTimeInMillis());
                System.out.println("last rollback=" + lastRollBack);
                System.out.flush();
                conn.setClientInfo("last.rollback", lastRollBack);
                conn.setAutoCommit(wasAutocomit);
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.err);
            }
            if (ex == null) {
                throw new TransactionException("Algum erro interno ao sistema ocorreu");
            }
            if (ex instanceof DaoException) {
                DaoException dex = (DaoException) ex;
                dex.printStackTrace(System.err);
                throw dex;
            } else {
                throw new DaoException("Erro de transação SAIA do programa!!", ex);
            }
        } else {
            if (ex != null) {
                throw new TransactionException(ex);
            } else {
                throw new TransactionException("Erro de transação SAIA do programa!!");
            }
        }
    }
}
