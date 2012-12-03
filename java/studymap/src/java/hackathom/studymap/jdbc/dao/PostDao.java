package hackathom.studymap.jdbc.dao;
import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hackathon.studymap.jdbc.model.Post;

public class PostDao extends Dao<Post> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("cargoId", Integer.class, "cargo_id", java.sql.Types.INTEGER);
        chavePrimariaDescritor.setAutogerada(true);
    }


	@Override
	public String getSelect() {
		return "SELECT P.post_id,P.title,P.content,P.instant FROM post P";
	}

	@Override
	public String getInsert() {
		return "INSERT INTO post (post_id,title,content,instant) VALUES (?,?,?,?)";
	}

	@Override
	public String getUpdate() {
		return "UPDATE post SET post_id=?,title=?,content=?,instant=?";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM post WHERE ID=?";
	}


	@Override
	protected void prepareUpdate(PreparedStatement stmt, Post p) throws SQLException {
		int idx = 1;
		stmt.setInt(idx++, p.getPostId());
		stmt.setString(idx++, p.getTitle());
		setNullSafe(stmt, p.getContent(), idx++);
		stmt.setTimestamp(idx++, new java.sql.Timestamp(p.getInstant().getTime()));
	}


	@Override
	protected void setValues(Post p, ResultSet rs) throws SQLException {
		p.setPostId(rs.getInt("post_id"));
		p.setTitle(rs.getString("title"));
		p.setContent(getStringOrNull(rs,"content"));
		p.setInstant(rs.getTimestamp("instant"));
	}

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected Post newInstance() {
        return new Post();
    }
}