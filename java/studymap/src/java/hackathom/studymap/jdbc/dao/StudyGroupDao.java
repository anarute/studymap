package hackathom.studymap.jdbc.dao;
import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hackathon.studymap.jdbc.model.StudyGroup;

public class StudyGroupDao extends Dao<StudyGroup> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("cargoId", Integer.class, "cargo_id", java.sql.Types.INTEGER);
        chavePrimariaDescritor.setAutogerada(true);
    }


	@Override
	public String getSelect() {
		return "SELECT S.study_group_id,S.owner_id,S.study_subject_id,S.description,S.longitude,S.latitude FROM study_group S";
	}

	@Override
	public String getInsert() {
		return "INSERT INTO study_group (study_group_id,owner_id,study_subject_id,description,longitude,latitude) VALUES (?,?,?,?,?,?)";
	}

	@Override
	public String getUpdate() {
		return "UPDATE study_group SET study_group_id=?,owner_id=?,study_subject_id=?,description=?,longitude=?,latitude=?";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM study_group WHERE ID=?";
	}


	@Override
	protected void prepareUpdate(PreparedStatement stmt, StudyGroup s) throws SQLException {
		int idx = 1;
		stmt.setInt(idx++, s.getStudyGroupId());
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