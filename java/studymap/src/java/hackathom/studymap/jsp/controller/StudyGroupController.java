package hackathom.studymap.jsp.controller;

import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.util.StringUtil;
import hackathon.studymap.jdbc.model.StudyGroup;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import br.com.jcomputacao.util.web.HttpServletHelper;
import hackathom.studymap.jdbc.dao.StudyGroupDao;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Murilo
 */
@WebServlet(name = "study_groupServlet", urlPatterns = {"/studyGroupController/salvar"})
public class StudyGroupController extends HttpServletHelper {

    private StudyGroup model;

    public List<StudyGroup> getList() throws DaoException {
        StudyGroupDao d = new StudyGroupDao();
        List<StudyGroup> rs = d.listar();
        return rs;
    }

    public StudyGroup getModel() {
        if (model == null) {
            model = new StudyGroup();
        }
        return model;
    }

    public boolean load(Integer studyAreaId) throws DaoException {
        StudyGroupDao d = new StudyGroupDao();
        model = d.buscar(studyAreaId);
        return model != null;
    }

    private List<String> validate(StudyGroup studyGroup) {
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
            String studyGroupId = request.getParameter("study_group_id");
            String ownerId = request.getParameter("owner_id");
            String studySubjectId = request.getParameter("study_subject_id");
            String description = request.getParameter("description");
            String longitude = request.getParameter("longitude");
            String latitude = request.getParameter("latitude");

            StudyGroup studyGroup = new StudyGroup();

            studyGroup.setStudyGroupId(convertToInt(studyGroupId, request));
            studyGroup.setOwnerId(convertToInt(ownerId, request));
            studyGroup.setStudySubjectId(convertToInt(studySubjectId, request));
            studyGroup.setDescription(description);
            studyGroup.setLongitude(convertToDouble(longitude, request));
            studyGroup.setLatitude(convertToDouble(latitude, request));
            List<String> msgList = validate(studyGroup);
            if (msgList.isEmpty()) {
                StudyGroupDao d = new StudyGroupDao();
                if (StringUtil.isNull(studyGroupId)) {
                    d.salvar(studyGroup);
                } else {
                    d.alterar(studyGroup);
                }
                msg.append(");studyGroup created with ID=");
                request.setAttribute("message", msg);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyGroup/list.jsp");
                dispatcher.forward(request, response);
            } else {
                for (String m : msgList) {
                    if (msg.length() > 0) {
                        msg.append("</br>");
                    }
                    msg.append(m);
                }
                request.setAttribute("message", msg.toString());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyGroup/edit.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyGroup/edit.jsp");
            dispatcher.forward(request, response);
        } catch (DaoException ex) {
            ex.printStackTrace(System.err);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyGroup/edit.jsp");
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
        return "StudyGroup controller servlet";
    }// </editor-fold>
}