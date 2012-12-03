package hackathom.studymap.jdbc.dao;
import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hackathon.studymap.jdbc.model.StudyMainSubject;

public class StudyMainSubjectDao extends Dao<StudyMainSubject> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("cargoId", Integer.class, "cargo_id", java.sql.Types.INTEGER);
        chavePrimariaDescritor.setAutogerada(true);
    }


	@Override
	public String getSelect() {
		return "SELECT S.study_main_subject_id,S.study_area_id,S.description FROM study_main_subject S";
	}

	@Override
	public String getInsert() {
		return "INSERT INTO study_main_subject (study_main_subject_id,study_area_id,description) VALUES (?,?,?)";
	}

	@Override
	public String getUpdate() {
		return "UPDATE study_main_subject SET study_main_subject_id=?,study_area_id=?,description=?";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM study_main_subject WHERE ID=?";
	}


	@Override
	protected void prepareUpdate(PreparedStatement stmt, StudyMainSubject s) throws SQLException {
		int idx = 1;
		stmt.setInt(idx++, s.getStudyMainSubjectId());
		stmt.setInt(idx++, s.getStudyAreaId());
		stmt.setString(idx++, s.getDescription());
	}


	@Override
	protected void setValues(StudyMainSubject s, ResultSet rs) throws SQLException {
		s.setStudyMainSubjectId(rs.getInt("study_main_subject_id"));
		s.setStudyAreaId(rs.getInt("study_area_id"));
		s.setDescription(rs.getString("description"));
	}

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected StudyMainSubject newInstance() {
        return new StudyMainSubject();
    }
}