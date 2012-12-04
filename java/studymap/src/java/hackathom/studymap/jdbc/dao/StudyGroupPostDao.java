package hackathom.studymap.jdbc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import hackathon.studymap.jdbc.model.StudyGroupPost;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyGroupPostDao extends Dao<StudyGroupPost> {
    
    private static final ChavePrimariaDescritor chavePrimariaDescritor;
    
    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("studyGroupPostId", Integer.class, "study_group_post_id", java.sql.Types.INTEGER);
        chavePrimariaDescritor.setAutogerada(true);
    }
    
    @Override
    public String getSelect() {
        return "SELECT S.study_group_post_id,S.study_group_id,S.user_id,S.title,S.content,S.posted,\n"
                + "U.user_id,U.login,U.fb_user_id,U.fb_username,U.creation\n"
                + "FROM study_group_post S inner join users U on S.user_id=U.user_id";
    }
    
    @Override
    public String getInsert() {
        return "INSERT INTO study_group_post (study_group_id,user_id,title,content,posted) VALUES (?,?,?,?,now())";
    }
    
    @Override
    public String getUpdate() {
        return "UPDATE study_group_post SET study_group_id=?,user_id=?,title=?,content=? WHERE study_group_post_id=?";
    }
    
    @Override
    public String getDelete() {
        return "DELETE FROM study_group_post WHERE study_group_post_id=?";
    }
    
    @Override
    protected void prepareUpdate(PreparedStatement stmt, StudyGroupPost s) throws SQLException {
        int idx = 1;
//        stmt.setInt(idx++, s.getStudyGroupPostId());
        stmt.setInt(idx++, s.getStudyGroupId());
        stmt.setInt(idx++, s.getUserId());
        stmt.setString(idx++, s.getTitle());
        stmt.setString(idx++, s.getContent());
    }
    
    @Override
    protected void setValues(StudyGroupPost s, ResultSet rs) throws SQLException {
        s.setStudyGroupPostId(rs.getInt("study_group_post_id"));
        s.setStudyGroupId(rs.getInt("study_group_id"));
        s.setUserId(rs.getInt("user_id"));
        s.setTitle(rs.getString("title"));
        s.setContent(rs.getString("content"));
        s.setPosted(rs.getTimestamp("posted"));
        s.setCreation(rs.getTimestamp("creation"));
        s.setFbUserId(rs.getLong("fb_user_id"));
        s.setFbUsername(rs.getString("fb_username"));
        s.setLogin(rs.getString("login"));
    }
    
    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }
    
    @Override
    protected StudyGroupPost newInstance() {
        return new StudyGroupPost();
    }
}
