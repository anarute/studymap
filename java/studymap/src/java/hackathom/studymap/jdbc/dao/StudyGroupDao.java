package hackathom.studymap.jdbc.dao;

import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import hackathon.studymap.jdbc.model.StudyGroup;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyGroupDao extends Dao<StudyGroup> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("studyGroupId", Integer.class, "study_group_id", java.sql.Types.INTEGER);
        chavePrimariaDescritor.setAutogerada(true);
    }

    @Override
    public String getSelect() {
        return "SELECT S.study_group_id,S.owner_id,S.study_subject_id,S.description,S.longitude,S.latitude\n"
                + "      , T.description subject_description, M.description main_subject_description, A.description area_description\n"
                + "FROM study_group S INNER JOIN study_subject T on S.study_subject_id=T.study_subject_id\n"
                + "                   INNER JOIN study_main_subject M on T.study_main_subject_id=M.study_main_subject_id\n"
                + "                   INNER JOIN study_area A on M.study_area_id=A.study_area_id";
    }

    @Override
    public String getInsert() {
        return "INSERT INTO study_group (owner_id,study_subject_id,description,longitude,latitude) VALUES (?,?,?,?,?)";
    }

    @Override
    public String getUpdate() {
        return "UPDATE study_group SET owner_id=?,study_subject_id=?,description=?,longitude=?,latitude=? WHERE study_group_id=?";
    }

    @Override
    public String getDelete() {
        return "DELETE FROM study_group WHERE study_group_id=?";
    }

    @Override
    protected void prepareUpdate(PreparedStatement stmt, StudyGroup s) throws SQLException {
        int idx = 1;
//        stmt.setInt(idx++, s.getStudyGroupId());
        stmt.setInt(idx++, s.getOwnerId());
        stmt.setInt(idx++, s.getStudySubjectId());
        stmt.setString(idx++, s.getDescription());
        stmt.setDouble(idx++, s.getLongitude());
        stmt.setDouble(idx++, s.getLatitude());
    }

    @Override
    protected void setValues(StudyGroup s, ResultSet rs) throws SQLException {
        s.setStudyGroupId(rs.getInt("study_group_id"));
        s.setOwnerId(rs.getInt("owner_id"));
        s.setStudySubjectId(rs.getInt("study_subject_id"));
        s.setDescription(rs.getString("description"));
        s.setLongitude(rs.getDouble("longitude"));
        s.setLatitude(rs.getDouble("latitude"));
        s.setArea(rs.getString("area_description"));
        s.setSubject(rs.getString("subject_description"));
        s.setMainSubject(rs.getString("main_subject_description"));
    }

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected StudyGroup newInstance() {
        return new StudyGroup();
    }
}