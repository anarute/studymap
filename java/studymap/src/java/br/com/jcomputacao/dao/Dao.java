package br.com.jcomputacao.dao;

import br.com.jcomputacao.util.NumberUtil;
import br.com.jcomputacao.util.StringUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;

/**
 * 23/10/2010 10:15:06
 * @author Murilo
 */
public abstract class Dao <T> {

    protected static final boolean migracao = Boolean.parseBoolean(System.getProperty("migracao", "false"));
    protected static final boolean debug = Boolean.parseBoolean(System.getProperty("debug", "false"));
    public static final String ERRO_GRAVE = "Erro grave ao tentar voltar o banco de dados para a situação anterior!"
                        + "\nChame o suporte e não altere mais informações no sistema"
                        + "\nSe precisar fazer alguma operação abra outra tela do sistema";
    protected abstract ChavePrimariaDescritor getChavePrimariaDescritor();
    protected abstract T newInstance();
    protected abstract String getSelect();
    protected abstract String getInsert();
    protected abstract String getUpdate();
    protected abstract String getDelete();
    protected abstract void prepareUpdate(PreparedStatement stmt, T t) throws SQLException, DaoException;
    protected abstract void setValues(T t, ResultSet rs) throws SQLException, DaoException;
    protected void gerarChavePrimaria(T t) throws SQLException {};
    
    /**
     * Metodo feito para ser sobre-escrito
     * quando a tabela precisar de prefixo
     * nao eh abstrato por serem raras excessoes
     * @return
     */
    protected String getTablePrefix() {
        return "";
    }

    public T buscar(Object ... chavePrimaria) throws DaoException {
        ChavePrimariaDescritor descritor = getChavePrimariaDescritor();
        if(chavePrimaria==null || chavePrimaria.length!=descritor.getCampos().size()) {
            throw new IllegalArgumentException("Chaves nulas ou numero de argumentos inválidos");
        }

        String comando = getSelect();
        comando += comandoComChavePrimaria();

        Connection conn = null;
        T t = null;
        try {
            conn = ConnectionFactory.getConnection();
            if(debug) {
                System.out.println(comando);
            }
            try (PreparedStatement stmt = conn.prepareStatement(comando)) {
                preecheChavePrimaria(stmt,chavePrimaria);
                try (ResultSet rs = stmt.executeQuery()) {
                    if(rs.next()) {
                        t = newInstance();
                        setValues(t, rs);
                    }
                }
            }
        } catch (SQLException ex) {
            throw new DaoException("Erro ao tentar recuperar o cadastro", ex);
        } finally {
            ConnectionFactory.devolver(conn);
        }
        return t;
    }
    
    public T buscar(String where, String [] params) throws DaoException {
        List<T> listar = listar(where, params);
        if (!listar.isEmpty()) {
            if (listar.size() > 1) {
                throw new DaoException("Mais de um registro encontrado para a busca por valores");
            } else {
                return listar.get(0);
            }
        }
        return null;
    }
        
    public int excluir(T t) throws DaoException {
        Connection conn = null;
        int n = 0;
        try {
            conn = ConnectionFactory.getConnection();
            n = excluir(t, conn);
        } catch (SQLException ex) {
            throw new DaoException("Erro ao tentar obter a conexão", ex);
        } finally {
            ConnectionFactory.devolver(conn);
        }
        return n;
    }
    
    public int excluir(T t, Connection conn) throws DaoException {
        Object [] chavePrimaria = extrairValoresChavePrimaria(t);

        String comando = getDelete();
        int afetados = 0;
        if (debug) {
            System.out.println(comando);
        }
        boolean wasAutocommit = true;
        try {
            wasAutocommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(comando);
            preecheChavePrimaria(stmt,chavePrimaria);
            afetados = stmt.executeUpdate();
//            DatabaseAudit.removido(t, this, conn);
            //NO CASO DE O DAO ESTAR ENVOLVIDO EM UMA TRANSACAO
            //EXTERNA ENTAO ELE NAO SERA RESPONSAVEL
            //POR FAZER COMMIT OU ROLL-BACK
            if(wasAutocommit) {
                conn.commit();
            }
        } catch (SQLException ex) {
            //NO CASO DE O DAO ESTAR ENVOLVIDO EM UMA TRANSACAO
            //EXTERNA ENTAO ELE NAO SERA RESPONSAVEL
            //POR FAZER COMMIT OU ROLL-BACK
            if (wasAutocommit) {
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    throw new DaoException(ERRO_GRAVE, ex1);
                }
            }
            throw new DaoException("Erro ao tentar alterar o cadastro", ex);
        } finally {
            if(wasAutocommit) {
                try {
                    conn.setAutoCommit(wasAutocommit);
                } catch (SQLException ex) {
                    throw new DaoException(ERRO_GRAVE, ex);
                }
            }
        }
        return afetados;
    }

    public T salvar(T t) throws DaoException {
        Connection conn = null;
        T ot = null;
        try {
            conn = ConnectionFactory.getConnection();
            ot = salvar(t, conn);
        } catch (SQLException ex) {
            throw new DaoException("Erro ao tentar obter a conexão", ex);
        } finally {
            ConnectionFactory.devolver(conn);
        }
        return ot;
    }
        
    public T salvar(T t, Connection conn) throws DaoException {
        String comando = getInsert();
        boolean wasAutocommit = true;
        try {
            wasAutocommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            PreparedStatement stmt = null;
            boolean genKeys = getChavePrimariaDescritor().isAutogerada();
            if(genKeys) {
                stmt = conn.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS);                
            } else {
                gerarChavePrimaria(t);
                stmt = conn.prepareStatement(comando);
            }
            if (debug) {
                System.out.println(comando);
            }
            //TODO fazer teste para verificar se nao esta
            //tentando salvar um registro ja salvo
            prepareUpdate(stmt, t);
            int afetados = stmt.executeUpdate();
            if (getChavePrimariaDescritor().isAutogerada()) {
                ResultSet chavesGeradas = stmt.getGeneratedKeys();
                ResultSetMetaData rsmd = chavesGeradas.getMetaData();
                int coluns = rsmd.getColumnCount();
                for (int column = 1; column <= coluns; column++) {
                    List<ChavePrimariaDescritorCampo> campos = getChavePrimariaDescritor().getCampos();
                    for(ChavePrimariaDescritorCampo campo : campos) {
                        String atributo = "set"+StringUtil.primeiraLetraMaiuscula(campo.getAtributo());
                        Object parametro = null;
                        if (chavesGeradas.next()) {
                            Class parameterType = campo.getTipo();
                            if (parameterType.equals(Integer.class)) {
                                parametro = chavesGeradas.getInt(1);
                            } else if (parameterType.equals(Long.class)) {
                                parametro = chavesGeradas.getLong(1);
                            } else if (parameterType.equals(String.class)) {
                                parametro = chavesGeradas.getString(1);
                            } else {
                                parametro = chavesGeradas.getObject(1);
                            }
                            try {
                                Method method = t.getClass().getMethod(atributo, parameterType);
                                method.invoke(t, parametro);
                            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            throw new DaoException("Nao conseguiu obter os valores das chaves geradas");
                        }
                    }
                }
                chavesGeradas.close();
            }
//            if(!migracao) DatabaseAudit.salvo(t, this, conn);
            //NO CASO DE O DAO ESTAR ENVOLVIDO EM UMA TRANSACAO
            //EXTERNA ENTAO ELE NAO SERA RESPONSAVEL
            //POR FAZER COMMIT OU ROLL-BACK
            if(wasAutocommit) {
                conn.commit();
            }
            
            if (debug) {
                System.out.println("Insertiu " + afetados + " registro(s)");
            }
//            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)
//                    .log(Level.FINER, "Insertiu {0} registro(s)", afetados);
        } catch (SQLException ex) {
            //NO CASO DE O DAO ESTAR ENVOLVIDO EM UMA TRANSACAO
            //EXTERNA ENTAO ELE NAO SERA RESPONSAVEL
            //POR FAZER COMMIT OU ROLL-BACK
            if (wasAutocommit) {
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    throw new DaoException(ERRO_GRAVE, ex1);
                }
            }
            throw new DaoException("Erro ao tentar inserir o cadastro", ex);
        } finally {
            if(wasAutocommit) {
                try {
                    conn.setAutoCommit(wasAutocommit);
                } catch (SQLException ex) {
                    throw new DaoException(ERRO_GRAVE, ex);
                }
            }    
        }
        return t;
    }
    
    public int salvar(List<T> ts) throws DaoException {
        Connection conn = null;
        int alterados = 0;
        try {
            conn = ConnectionFactory.getConnection();
            alterados = salvar(ts, conn);
        } catch (SQLException ex) {
            throw new DaoException("Erro ao tentar obter a conexão", ex);
        } finally {
            ConnectionFactory.devolver(conn);
        }
        return alterados;
    }
    
    
    public int salvar(List<T> ts, Connection conn) throws DaoException {
        String comando = getInsert();
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.FINE, comando);
        boolean wasAutocommit = true;
        boolean genKeys = getChavePrimariaDescritor().isAutogerada();
        
        int[] records = null;
        int[] audits = null;

        try {
            wasAutocommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(comando);
//            PreparedStatement auditStmt = DatabaseAudit.prepareForBatch(conn);
            for (T t : ts) {
                if (!genKeys) {
                    gerarChavePrimaria(t);
                }
                Object[] chavePrimaria = extrairValoresChavePrimaria(t);
                prepareUpdate(stmt, t);
                if (!genKeys) {
                    preecheChavePrimaria(stmt, chavePrimaria);
                }
                stmt.addBatch();
//                if (!migracao) {
//                    DatabaseAudit.salvo(t, this, auditStmt);
//                }
            }
            records = stmt.executeBatch();
//            audits  = auditStmt.executeBatch();
            stmt.close();
//            auditStmt.close();
            if (debug) {
                System.out.println("Insercoes : " + NumberUtil.soma(records) + " registros de auditoria : " + NumberUtil.soma(audits));
            }
            //NO CASO DE O DAO ESTAR ENVOLVIDO EM UMA TRANSACAO
            //EXTERNA ENTAO ELE NAO SERA RESPONSAVEL
            //POR FAZER COMMIT OU ROLL-BACK
            if(wasAutocommit) {
                conn.commit();
            }
        } catch (SQLException ex) {
            //NO CASO DE O DAO ESTAR ENVOLVIDO EM UMA TRANSACAO
            //EXTERNA ENTAO ELE NAO SERA RESPONSAVEL
            //POR FAZER COMMIT OU ROLL-BACK
            if (wasAutocommit) {
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    throw new DaoException(ERRO_GRAVE, ex1);
                }
            }
            throw new DaoException("Erro ao tentar alterar o cadastro", ex);
        } finally {
            if(wasAutocommit) {
                try {
                    conn.setAutoCommit(wasAutocommit);
                } catch (SQLException ex) {
                    throw new DaoException(ERRO_GRAVE, ex);
                }
            }
        }
        if(records==null) {
            return 0;
        } else {
            return NumberUtil.soma(records);
        }
    }

    public T alterar(T t) throws DaoException {
        Connection conn = null;
        T ot = null;
        try {
            conn = ConnectionFactory.getConnection();
            ot = alterar(t, conn);
        } catch (SQLException ex) {
            throw new DaoException("Erro ao tentar obter a conexão", ex);
        } finally {
            ConnectionFactory.devolver(conn);
        }
        return ot;
    }
    
    public T alterar(T t, Connection conn) throws DaoException {
        Object [] chavePrimaria = extrairValoresChavePrimaria(t);

        String comando = getUpdate();
        boolean wasAutocommit = true;
        int versao = 0;
//        if(t instanceof CadastroVersionado) {
//            versao = ((CadastroVersionado) t).getVersao();
//            ((CadastroVersionado) t).setVersao(versao + 1);
//        }
        int alterados = 0;
        try {
            wasAutocommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(comando);
            if (debug) {
                System.out.println(comando);
            }
            prepareUpdate(stmt, t);
            preecheChavePrimaria(stmt,chavePrimaria);
            alterados = stmt.executeUpdate();
//            if(!migracao) DatabaseAudit.atualizado(t, this, conn);
            //NO CASO DE O DAO ESTAR ENVOLVIDO EM UMA TRANSACAO
            //EXTERNA ENTAO ELE NAO SERA RESPONSAVEL
            //POR FAZER COMMIT OU ROLL-BACK
            if(wasAutocommit) {
                conn.commit();
            }
        } catch (SQLException ex) {
//            if (t instanceof CadastroVersionado) {
//                ((CadastroVersionado) t).setVersao(versao);
//            }
            //NO CASO DE O DAO ESTAR ENVOLVIDO EM UMA TRANSACAO
            //EXTERNA ENTAO ELE NAO SERA RESPONSAVEL
            //POR FAZER COMMIT OU ROLL-BACK
            if (wasAutocommit) {
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    throw new DaoException(ERRO_GRAVE, ex1);
                }
            }
            throw new DaoException("Erro ao tentar alterar o cadastro", ex);
        } finally {
            if(wasAutocommit) {
                try {
                    conn.setAutoCommit(wasAutocommit);
                } catch (SQLException ex) {
                    throw new DaoException(ERRO_GRAVE, ex);
                }
            }
        }
        if (1 != alterados) {
            throw new DaoException("Registro nao alterado!");
        }
        return t;
    }
    
    public int alterar(List<T> ts, Connection conn) throws DaoException {
        String comando = getUpdate();
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.FINE, comando);
        boolean wasAutocommit = true;
        
        int[] records = null;
        int[] audits  = null;

        try {
            wasAutocommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(comando);
//            PreparedStatement auditStmt = DatabaseAudit.prepareForBatch(conn);
            for (T t : ts) {
//                if (t instanceof CadastroVersionado) {
//                    int versao = ((CadastroVersionado) t).getVersao();
//                    ((CadastroVersionado) t).setVersao(versao + 1);
//                }
                Object [] chavePrimaria = extrairValoresChavePrimaria(t);
                
                prepareUpdate(stmt, t);
                preecheChavePrimaria(stmt, chavePrimaria);
                stmt.addBatch();
//                if (!migracao) {
//                    DatabaseAudit.atualizado(t, this, auditStmt);
//                }
            }
            records = stmt.executeBatch();
//            audits  = auditStmt.executeBatch();
            stmt.close();
//            auditStmt.close();
            if (debug) {
                System.out.println("Alteracoes : " + NumberUtil.soma(records) + " registros de auditoria : " + NumberUtil.soma(audits));
            }
            //NO CASO DE O DAO ESTAR ENVOLVIDO EM UMA TRANSACAO
            //EXTERNA ENTAO ELE NAO SERA RESPONSAVEL
            //POR FAZER COMMIT OU ROLL-BACK
            if(wasAutocommit) {
                conn.commit();
            }
        } catch (SQLException ex) {
            //NO CASO DE O DAO ESTAR ENVOLVIDO EM UMA TRANSACAO
            //EXTERNA ENTAO ELE NAO SERA RESPONSAVEL
            //POR FAZER COMMIT OU ROLL-BACK
            if (wasAutocommit) {
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    throw new DaoException(ERRO_GRAVE, ex1);
                }
            }
            throw new DaoException("Erro ao tentar alterar o cadastro", ex);
        } finally {
            if(wasAutocommit) {
                try {
                    conn.setAutoCommit(wasAutocommit);
                } catch (SQLException ex) {
                    throw new DaoException(ERRO_GRAVE, ex);
                }
            }
        }
        if(records==null) {
            return 0;
        } else {
            return NumberUtil.soma(records);
        }
    }

    public List<T> listar() throws DaoException {
        return listar(null);
    }
    
    public List<T> listar(String where, String ... params) throws DaoException {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            return listar(where, params, conn);
        } catch (SQLException ex) {
            throw new DaoException("Erro ao tentar obter a conexão", ex);
        } finally {
            if (conn != null) {
                ConnectionFactory.devolver(conn);
            }
        }
    }

    public List<T> listar(String where, String [] params, Connection conn) throws DaoException {
        List<T> lista = new ArrayList<>();
        String comando = getSelect();

        if(where!=null && !"".equals(where.trim())) {
            if(!comando.toUpperCase().contains("WHERE") &&
                !where.toUpperCase().contains("WHERE") && !where.toUpperCase().contains("LIMIT")) {
                comando += " WHERE ";
            } else if(!comando.endsWith(" ") && !comando.endsWith("\n") && !where.startsWith("\n")) {
                comando += "\n";
            }
            comando += where;
        }

        try {
            Statement stmt = null;
            ResultSet rs = null;
            if(debug) {
                System.out.println(comando);
            }
            if (params == null || params.length==0) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery(comando);
            } else {
                stmt = conn.prepareStatement(comando);
                for (int i = 1; i <= params.length; i++) {
                    ((PreparedStatement)stmt).setString(i, params[i-1]);
                }
                rs = ((PreparedStatement)stmt).executeQuery();
            }
            
            while (rs.next()) {
                T entidade = newInstance();
                setValues(entidade, rs);
                lista.add(entidade);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            throw new DaoException("Erro ao tentar ler o(s) registro(s)", ex);
        }
        return lista;
    }

    private void preecheChavePrimaria(PreparedStatement stmt, Object[] chavePrimaria) throws SQLException {
        ChavePrimariaDescritor descritor = getChavePrimariaDescritor();
        int parametros = stmt.getParameterMetaData().getParameterCount();
        parametros -= (descritor.getCampos().size()-1);
        int idx = 0;

        for (ChavePrimariaDescritorCampo campo : descritor.getCampos()) {
            switch (campo.getTipoSql()) {
                case java.sql.Types.INTEGER:
                    stmt.setInt((idx + parametros), new Integer(chavePrimaria[idx].toString()));
                    break;
                case java.sql.Types.BIGINT:
                    stmt.setLong((idx + parametros), new Long(chavePrimaria[idx].toString()));
                    break;
            }
            idx++;
        }
    }


    private String comandoComChavePrimaria() {
        String where = " WHERE ";
        boolean first = true;
        for (ChavePrimariaDescritorCampo campo : getChavePrimariaDescritor().getCampos()) {
            if (first) {
                first = false;
            } else {
                where += " AND ";
            }
            if(null!=getChavePrimariaDescritor().getPrefixo() &&
                    !"".equals(getChavePrimariaDescritor().getPrefixo())) {
                where += getChavePrimariaDescritor().getPrefixo()+".";
            }
            where += campo.getColunaSql() + "=?";
        }
        return where;
    }

    Object[] extrairValoresChavePrimaria(T t) throws DaoException {
        ChavePrimariaDescritor descritor = getChavePrimariaDescritor();
        Object[] valores = new Object[descritor.getCampos().size()];

        int i = 0;

        for(ChavePrimariaDescritorCampo campo:descritor.getCampos()) {
            String nomeMetodo = "get" +
                    campo.getAtributo().substring(0, 1).toUpperCase() +
                    campo.getAtributo().substring(1);

            try {
                Method method = t.getClass().getMethod(nomeMetodo);
                valores[i] = method.invoke(t);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                throw new DaoException("Não encontrou o metodo "+
                        nomeMetodo+" em "+t.getClass().getName());
            } catch (SecurityException ex) {
                throw new DaoException("Erro de segurando ao tentar executar o metodo "+
                        nomeMetodo+" em "+t.getClass().getName());
            }
            i++;
        }

        return valores;
    }

    protected void setNullSafe(PreparedStatement stmt, Integer value, int index) throws SQLException {
        if (value == null || value.intValue() == 0) {
            stmt.setNull(index, java.sql.Types.INTEGER);
        } else {
            stmt.setInt(index, value);
        }
    }

    protected void setNullSafe(PreparedStatement stmt, Long value, int index) throws SQLException {
        if (value == null || value.longValue() == 0) {
            stmt.setNull(index, java.sql.Types.BIGINT);
        } else {
            stmt.setLong(index, value);
        }
    }

    protected void setNullSafe(PreparedStatement stmt, String value, int index) throws SQLException {
        if (StringUtil.isNull(value)) {
            stmt.setNull(index, java.sql.Types.VARCHAR);
        } else {
            stmt.setString(index, value);
        }
    }

    protected void setNullSafe(PreparedStatement stmt, Double value, int index) throws SQLException {
        if (value==null || value==0) {
            stmt.setNull(index, java.sql.Types.DOUBLE);
        } else {
            stmt.setDouble(index, value);
        }
    }

    protected void setNullSafe(PreparedStatement stmt, Float value, int index) throws SQLException {
        if (value==null || value==0) {
            stmt.setNull(index, java.sql.Types.DOUBLE);
        } else {
            stmt.setDouble(index, value);
        }
    }


    protected void setNullSafe(PreparedStatement stmt, java.util.Date date, int idx) throws SQLException {
        if (date == null) {
            stmt.setNull(idx++, java.sql.Types.TIMESTAMP);
        } else {
            stmt.setTimestamp(idx++, new java.sql.Timestamp(date.getTime()));
        }
    }

    protected void setNullSafe(PreparedStatement stmt, Boolean value, int index) throws SQLException {
        if (value != null) {
            stmt.setBoolean(index, value.booleanValue());
        } else {
            stmt.setNull(index, java.sql.Types.BOOLEAN);
        }
    }
    
    protected void setNullSafe(PreparedStatement stmt, byte [] value, int index) throws SQLException {
        if (value != null) {
            stmt.setBlob(index, new SerialBlob(value));
        } else {
            stmt.setNull(index, java.sql.Types.BLOB);
        }
    }
    
    protected Integer getIntOrNull(ResultSet rs, String string) throws SQLException {
        int value = rs.getInt(string);
        if(rs.wasNull()) {
            return null;
        }
        return value;
    }
    
    protected Long getLongOrNull(ResultSet rs, String string) throws SQLException {
        long value = rs.getLong(string);
        if(rs.wasNull()) {
            return null;
        }
        return value;
    }
    
    protected Float getFloatOrNull(ResultSet rs, String string) throws SQLException {
        Float value = rs.getFloat(string);
        if(rs.wasNull()) {
            return null;
        }
        return value;
    }
    
    protected Double getDoubleOrNull(ResultSet rs, String string) throws SQLException {
        Double value = rs.getDouble(string);
        if(rs.wasNull()) {
            return null;
        }
        return value;
    }
    
    protected String getStringOrNull(ResultSet rs, String string) throws SQLException {
        String value = rs.getString(string);
        if(rs.wasNull()) {
            return null;
        }
        return value;
    }

    protected Date getDateOrNull(ResultSet rs, String string) throws SQLException {
        Date value = rs.getDate(string);
        if(rs.wasNull()) {
            return null;
        }
        return value;
    }

    protected Date getTimeOrNull(ResultSet rs, String string) throws SQLException {
        Date value = rs.getTime(string);
        if(rs.wasNull()) {
            return null;
        }
        return value;
    }
    
    protected Date getTimestampOrNull(ResultSet rs, String string) throws SQLException {
        Date value = rs.getTimestamp(string);
        if (rs.wasNull()) {
            return null;
        }
        return value;
    }
    
    protected Boolean getBooleanOrNull(ResultSet rs, String string) throws SQLException {
        Boolean value = rs.getBoolean(string);
        if (rs.wasNull()) {
            value = null;
        }
        return value;
    }
    
    protected byte[] getBlobOrNull(ResultSet rs, String string) throws SQLException {
        Blob blob = rs.getBlob(string);
        byte[] value = null;
        if (!rs.wasNull()) {
            long length = blob.length();
            value = (blob.getBytes(1, (int) length));
        }
        return value;
    }
}
