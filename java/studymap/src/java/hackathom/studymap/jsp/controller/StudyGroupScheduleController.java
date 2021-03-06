package hackathom.studymap.jsp.controller;

import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.util.StringUtil;
import br.com.jcomputacao.util.web.HttpServletHelper;
import hackathom.studymap.jdbc.dao.StudyGroupScheduleDao;
import hackathon.studymap.jdbc.model.StudyGroupSchedule;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Murilo
 */
@WebServlet(name = "study_group_scheduleServlet", urlPatterns = {"/studyGroupScheduleController/salvar"})
public class StudyGroupScheduleController extends HttpServletHelper {
    
    private StudyGroupSchedule model;
    
    public StudyGroupSchedule getModel() {
        if(model==null) {
            model = new StudyGroupSchedule();
        }
        return model;
    }
        
    public boolean load(Integer studyAreaId) throws DaoException {
        StudyGroupScheduleDao d = new StudyGroupScheduleDao();
        model = d.buscar(studyAreaId);
        return model != null;
    }
    
    public List<StudyGroupSchedule> getList() throws DaoException {
        StudyGroupScheduleDao dao = new StudyGroupScheduleDao();
        List<StudyGroupSchedule> list = dao.listar();
        return list;
    }
    
    public List<StudyGroupSchedule> getListByStudyGroup(Integer studyGroup) throws DaoException {
        StudyGroupScheduleDao dao = new StudyGroupScheduleDao();
        List<StudyGroupSchedule> list = dao.listar("WHERE S.study_group_id=?", new String[]{studyGroup.toString()});
        return list;
    }

    private List<String> validate(StudyGroupSchedule studyGroupSchedule) {
        List<String> list = new ArrayList<String>();
        return list;
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            StringBuilder msg = new StringBuilder();
            String studyGroupScheduleId = request.getParameter("study_group_schedule_id");
            String studyGroupId = request.getParameter("study_group_id");
            String dayOfWeek = request.getParameter("day_of_week");
            String year = request.getParameter("year");
            String month = request.getParameter("month");
            String dayOfMonth = request.getParameter("day_of_month");
            String hour = request.getParameter("hour");
            String minute = request.getParameter("minute");

            StudyGroupSchedule studyGroupSchedule = new StudyGroupSchedule();

            studyGroupSchedule.setStudyGroupScheduleId(convertToInt(studyGroupScheduleId, request));
            studyGroupSchedule.setStudyGroupId(convertToInt(studyGroupId, request));
            studyGroupSchedule.setDayOfWeek(convertToIntegerOrNull(dayOfWeek, request));
            studyGroupSchedule.setYear(convertToIntegerOrNull(year, request));
            studyGroupSchedule.setMonth(convertToIntegerOrNull(month, request));
            studyGroupSchedule.setDayOfMonth(convertToIntegerOrNull(dayOfMonth, request));
            studyGroupSchedule.setHour(convertToInt(hour, request));
            studyGroupSchedule.setMinute(convertToInt(minute, request));
            List<String> msgList = validate(studyGroupSchedule);
            if (msgList.isEmpty()) {
                StudyGroupScheduleDao d = new StudyGroupScheduleDao();
                if (StringUtil.isNull(studyGroupId)) {
                    d.salvar(studyGroupSchedule);
                } else {
                    d.alterar(studyGroupSchedule);
                }
                msg.append(");studyGroupSchedule created with ID=");
                request.setAttribute("message", msg);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyGroupSchedule/list.jsp");
                dispatcher.forward(request, response);
            } else {
                for (String m : msgList) {
                    if (msg.length() > 0) {
                        msg.append("</br>");
                    }
                    msg.append(m);
                }
                request.setAttribute("message", msg.toString());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyGroupSchedule/edit.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyGroupSchedule/edit.jsp");
            dispatcher.forward(request, response);
        } catch (DaoException ex) {
            ex.printStackTrace(System.err);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyGroupMember/edit.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "StudyGroupSchedule controller servlet";
    }// </editor-fold>
}