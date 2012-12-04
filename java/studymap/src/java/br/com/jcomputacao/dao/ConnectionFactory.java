package br.com.jcomputacao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 24/09/2010 12:17:26
 * @author Murilo
 */
public class ConnectionFactory {

    private static String sgbd = null;
    private static String database = null;
    private static String schema = null;
    private static String host = null;
    private static String port = null;
    private static String user = null;
    private static String password = null;
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = createConnection();
        }
        return conn;
    }

    private static Connection createConnection() throws SQLException {
        boolean desktop = Boolean.parseBoolean(System.getProperty("desktop", "false"));
        if (!desktop) {
            try {
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                DataSource ds = (DataSource) envCtx.lookup("jdbc/studymap");
                return ds.getConnection();
            } catch (NamingException ex) {
                ex.printStackTrace(System.err);
            }
        }
        boolean replication = Boolean.parseBoolean(System.getProperty("replication", "false"));
        host = System.getProperty("host", "localhost");
        user = System.getProperty("user");
        schema = System.getProperty("schema");
        password = System.getProperty("password");
        database = System.getProperty("database");
        if (replication && !host.contains(",")) {
            replication = false;
        }
        try {
            sgbd = System.getProperty("sgbd", "sqlserver");
            if ("sqlserver".equals(sgbd)) {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                port = "1433";
            } else if ("mysql".equals(sgbd)) {
                Class.forName("java.sql.Driver");
                port = "3306";
            } else if ("oracle".equals(sgbd)) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                port = "1521";
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        StringBuilder url = new StringBuilder("jdbc:");
        //jtds:sqlserver://

        if ("sqlserver".equals(sgbd)) {
            url.append("jtds:sqlserver://");
        } else if ("mysql".equals(sgbd)) {
            if(replication) {
                url.append("mysql:replication://");
            } else {
                url.append("mysql://");
            }
        } else if ("oracle".equals(sgbd)) {
            url.append("oracle:thin:@//");
        }

        url.append(host);
        if (port != null) {
            url.append(":");
            url.append(port);
        }
        url.append("/");
        if (database != null) {
            url.append(database);
        }
//        if(schema!=null) {
//            url.append(";");
//            url.append(schema);
//        }
        if("mysql".equals(sgbd)) {
            url.append("?");
            url.append("zeroDateTimeBehavior=convertToNull");
            
            if(Boolean.parseBoolean(System.getProperty("compress", "false"))) {
                url.append("&useCompression=true");
            }
            if(Boolean.parseBoolean(System.getProperty("auroreconnect", "false"))) {
                url.append("&autoReconnect=true");
            }
        }

        Properties prop = new Properties();
        prop.put("user",    user);
        prop.put("password",password);
        String aurl = url.toString();
        if (Boolean.parseBoolean(System.getProperty("debug", "false"))) {
            System.out.println(aurl);
        }
        if(replication && "mysql".equals(sgbd)) {
            com.mysql.jdbc.ReplicationDriver driver = new com.mysql.jdbc.ReplicationDriver();
            conn = driver.connect(aurl, prop);
//            Class clazz = Class.forName("com.mysql.jdbc.ReplicationDriver");
//            Object odriver = clazz.newInstance();
//            if(odriver instanceof java.sql.DriverManager) {
//                DriverManager 
//            }
        } else {
            conn = DriverManager.getConnection(aurl, prop);
//        if(schema!=null) {
//            conn.setCatalog(schema);
//        }
        }
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        
        return conn;
    }

    public static void devolver(java.sql.Connection conn) {
    }

    public static void close() throws SQLException {
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }
    
}
