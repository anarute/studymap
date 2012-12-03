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
        chavePrimariaDescritor.addCampo("cargoId", Integer.class, "cargo_id", java.sql.Types.INTEGER);
        chavePrimariaDescritor.setAutogerada(true);
    }


	@Override
	public String getSelect() {
		return "SELECT U.user_id,U.login,U.password,U.name,U.birthday,U.posts,U.gold FROM user U";
	}

	@Override
	public String getInsert() {
		return "INSERT INTO user (user_id,login,password,name,birthday,posts,gold) VALUES (?,?,?,?,?,?,?)";
	}

	@Override
	public String getUpdate() {
		return "UPDATE user SET user_id=?,login=?,password=?,name=?,birthday=?,posts=?,gold=?";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM user WHERE ID=?";
	}


	@Override
	protected void prepareUpdate(PreparedStatement stmt, User u) throws SQLException {
		int idx = 1;
		stmt.setInt(idx++, u.getUserId());
		stmt.setString(idx++, u.getLogin());
		stmt.setString(idx++, u.getPassword());
		stmt.setString(idx++, u.getName());
		setNullSafe(stmt, u.getBirthday(), idx++);
		setNullSafe(stmt, u.getPosts(), idx++);
		setNullSafe(stmt, u.getGold(), idx++);
	}


	@Override
	protected void setValues(User u, ResultSet rs) throws SQLException {
		u.setUserId(rs.getInt("user_id"));
		u.setLogin(rs.getString("login"));
		u.setPassword(rs.getString("password"));
		u.setName(rs.getString("name"));
		u.setBirthday(getDateOrNull(rs,"birthday"));
		u.setPosts(getIntOrNull(rs,"posts"));
		u.setGold(getDoubleOrNull(rs,"gold"));
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