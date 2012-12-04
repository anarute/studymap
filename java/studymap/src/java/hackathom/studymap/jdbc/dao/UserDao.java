package hackathom.studymap.jdbc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import hackathon.studymap.jdbc.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao<User> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("userId", Integer.class, "user_id", java.sql.Types.INTEGER);
        chavePrimariaDescritor.setAutogerada(true);
    }

    @Override
    public String getSelect() {
        return "SELECT U.user_id,U.login,U.email,U.fb_user_id,U.fb_username,U.creation FROM users U";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO users (login,email,fb_user_id,fb_username,creation) VALUES (?,?,?,?,now())";
    }

    @Override
    public String getUpdate() {
        return "UPDATE users SET login=?,email=?,fb_user_id=?,fb_username=? WHERE user_id=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM users WHERE user_id=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, User u) throws SQLException {
        int idx = 1;
//        stmt.setInt(idx++, u.getUserId());
        stmt.setString(idx++, u.getLogin());
        stmt.setString(idx++, u.getEmail());
        stmt.setString(idx++, u.getFbUsername());
    }

    @Override
    protected void setValues(User u, ResultSet rs) throws SQLException {
        u.setUserId(rs.getInt("user_id"));
        u.setLogin(rs.getString("login"));
        u.setEmail(rs.getString("email"));
        u.setFbUsername(rs.getString("fb_username"));
        u.setCreation(getTimestampOrNull(rs, "creation"));
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