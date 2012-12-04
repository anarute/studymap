package hackathom.studymap.jdbc.dao;
import br.com.jcomputacao.dao.ChavePrimariaDescritor;
import br.com.jcomputacao.dao.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hackathon.studymap.jdbc.model.StudyGroupSchedule;

public class StudyGroupScheduleDao extends Dao<StudyGroupSchedule> {

    private static final ChavePrimariaDescritor chavePrimariaDescritor;

    static {
        chavePrimariaDescritor = new ChavePrimariaDescritor();
        chavePrimariaDescritor.addCampo("studyGroupScheduleId", Integer.class, "study_group_schedule_id", java.sql.Types.INTEGER);
        chavePrimariaDescritor.setAutogerada(true);
    }


	@Override
	public String getSelect() {
		return "SELECT S.study_group_schedule_id,S.study_group_id,S.day_of_week,S.year,S.month,S.day_of_month,S.hour,S.minute FROM study_group_schedule S";
	}

	@Override
	public String getInsert() {
		return "INSERT INTO study_group_schedule (study_group_schedule_id,study_group_id,day_of_week,year,month,day_of_month,hour,minute) VALUES (?,?,?,?,?,?,?,?)";
	}

	@Override
	public String getUpdate() {
		return "UPDATE study_group_schedule SET study_group_schedule_id=?,study_group_id=?,day_of_week=?,year=?,month=?,day_of_month=?,hour=?,minute=?";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM study_group_schedule WHERE ID=?";
	}


	@Override
	protected void prepareUpdate(PreparedStatement stmt, StudyGroupSchedule s) throws SQLException {
		int idx = 1;
		stmt.setInt(idx++, s.getStudyGroupScheduleId());
		stmt.setInt(idx++, s.getStudyGroupId());
		setNullSafe(stmt, s.getDayOfWeek(), idx++);
		setNullSafe(stmt, s.getYear(), idx++);
		setNullSafe(stmt, s.getMonth(), idx++);
		setNullSafe(stmt, s.getDayOfMonth(), idx++);
		stmt.setInt(idx++, s.getHour());
		stmt.setInt(idx++, s.getMinute());
	}


	@Override
	protected void setValues(StudyGroupSchedule s, ResultSet rs) throws SQLException {
		s.setStudyGroupScheduleId(rs.getInt("study_group_schedule_id"));
		s.setStudyGroupId(rs.getInt("study_group_id"));
		s.setDayOfWeek(getIntOrNull(rs,"day_of_week"));
		s.setYear(getIntOrNull(rs,"year"));
		s.setMonth(getIntOrNull(rs,"month"));
		s.setDayOfMonth(getIntOrNull(rs,"day_of_month"));
		s.setHour(rs.getInt("hour"));
		s.setMinute(rs.getInt("minute"));
	}

    @Override
    protected ChavePrimariaDescritor getChavePrimariaDescritor() {
        return chavePrimariaDescritor;
    }

    @Override
    protected StudyGroupSchedule newInstance() {
        return new StudyGroupSchedule();
    }
}