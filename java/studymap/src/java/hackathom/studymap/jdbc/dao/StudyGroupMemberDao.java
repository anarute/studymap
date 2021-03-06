package hackathom.studymap.jdbc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import hackathon.studymap.jdbc.model.StudyGroupMember;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyGroupMemberDao extends Dao<StudyGroupMember> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("studyGroupMemeberId", Integer.class, "study_group_memeber_id", java.sql.Types.INTEGER);
        chavePrimariaDescritor.setAutogerada(true);
    }

    @Override
    public String getSelect() {
        return "SELECT S.study_group_memeber_id,S.study_group_id,S.user_id\n"
             + "     , U.login,U.fb_user_id,U.fb_username,U.creation\n"
             + "FROM study_group_member S left join users U on S.user_id=U.user_id";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO study_group_member (study_group_id,user_id) VALUES (?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE study_group_member SET study_group_id=?,user_id=? WHERE study_group_memeber_id=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM study_group_member WHERE study_group_memeber_id=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, StudyGroupMember s) throws SQLException {
        int idx = 1;
//		stmt.setInt(idx++, s.getStudyGroupMemeberId());
        stmt.setInt(idx++, s.getStudyGroupId());
        stmt.setInt(idx++, s.getUserId());
    }

    @Override
    protected void setValues(StudyGroupMember s, ResultSet rs) throws SQLException {
        s.setStudyGroupMemeberId(rs.getInt("study_group_memeber_id"));
        s.setStudyGroupId(rs.getInt("study_group_id"));
        s.setUserId(rs.getInt("user_id"));
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
    protected StudyGroupMember newInstance() {
        return new StudyGroupMember();
    }
}