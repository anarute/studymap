package hackathom.studymap.jdbc.dao;
import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hackathon.studymap.jdbc.model.StudySubject;

public class StudySubjectDao extends Dao<StudySubject> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("studySubjectId", Integer.class, "study_subject_id", java.sql.Types.INTEGER);
        chavePrimariaDescritor.setAutogerada(true);
    }


	@Override
	public String getSelect() {
		return "SELECT S.study_subject_id,S.study_main_subject_id,S.description FROM study_subject S";
	}

	@Override
	public String getInsert() {
		return "INSERT INTO study_subject (study_subject_id,study_main_subject_id,description) VALUES (?,?,?)";
	}

	@Override
	public String getUpdate() {
		return "UPDATE study_subject SET study_subject_id=?,study_main_subject_id=?,description=?";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM study_subject WHERE ID=?";
	}


	@Override
	protected void prepareUpdate(PreparedStatement stmt, StudySubject s) throws SQLException {
		int idx = 1;
		stmt.setInt(idx++, s.getStudySubjectId());
		stmt.setInt(idx++, s.getStudyMainSubjectId());
		stmt.setString(idx++, s.getDescription());
	}


	@Override
	protected void setValues(StudySubject s, ResultSet rs) throws SQLException {
		s.setStudySubjectId(rs.getInt("study_subject_id"));
		s.setStudyMainSubjectId(rs.getInt("study_main_subject_id"));
		s.setDescription(rs.getString("description"));
	}

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected StudySubject newInstance() {
        return new StudySubject();
    }
}