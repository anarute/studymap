package hackathom.studymap.jdbc.dao;
import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hackathon.studymap.jdbc.model.StudyArea;

public class StudyAreaDao extends Dao<StudyArea> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("studyAreaId", Integer.class, "study_area_id", java.sql.Types.INTEGER);
        chavePrimariaDescritor.setAutogerada(true);
    }


	@Override
	public String getSelect() {
		return "SELECT S.study_area_id,S.description FROM study_area S";
	}

	@Override
	public String getInsert() {
		return "INSERT INTO study_area (study_area_id,description) VALUES (?,?)";
	}

	@Override
	public String getUpdate() {
		return "UPDATE study_area SET study_area_id=?,description=?";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM study_area WHERE ID=?";
	}


	@Override
	protected void prepareUpdate(PreparedStatement stmt, StudyArea s) throws SQLException {
		int idx = 1;
		stmt.setInt(idx++, s.getStudyAreaId());
		stmt.setString(idx++, s.getDescription());
	}


	@Override
	protected void setValues(StudyArea s, ResultSet rs) throws SQLException {
		s.setStudyAreaId(rs.getInt("study_area_id"));
		s.setDescription(rs.getString("description"));
	}

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected StudyArea newInstance() {
        return new StudyArea();
    }
}