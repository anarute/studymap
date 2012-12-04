package hackathom.studymap.jdbc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hackathon.studymap.jdbc.model.User;

public class UserDao extends Dao<User> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("userId", Integer.class, "user_id", java.sql.Types.INTEGER);
        chavePrimariaDescritor.setAutogerada(true);
    }

    @Override
    public String getSelect() {
        return "SELECT U.user_id,U.login,U.email FROM users U";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO users (user_id,login,email) VALUES (?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE users SET user_id=?,login=?,email=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM users WHERE ID=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, User u) throws SQLException {
        int idx = 1;
        stmt.setInt(idx++, u.getUserId());
        stmt.setString(idx++, u.getLogin());
        stmt.setString(idx++, u.getEmail());
    }

    @Override
    protected void setValues(User u, ResultSet rs) throws SQLException {
        u.setUserId(rs.getInt("user_id"));
        u.setLogin(rs.getString("login"));
        u.setEmail(rs.getString("email"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected User newInstance() {
        return new User();
    }
}