package hackathom.studymap.jsp.controller;

import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.util.StringUtil;
import br.com.jcomputacao.util.web.HttpServletHelper;
import hackathom.studymap.jdbc.dao.StudyMainSubjectDao;
import hackathon.studymap.jdbc.model.StudyMainSubject;
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
@WebServlet(name = "study_main_subjectServlet", urlPatterns = {"/studyMainSubject/save"})
public class StudyMainSubjectController extends HttpServletHelper {
    
    private StudyMainSubject model;

    public StudyMainSubject getModel() {
        if(model==null) {
            model = new StudyMainSubject();
        }
        return model;
    }
        
    public boolean load(Integer studyAreaId) throws DaoException {
        StudyMainSubjectDao d = new StudyMainSubjectDao();
        model = d.buscar(studyAreaId);
        return model != null;
    }
    
    public List<StudyMainSubject> getList() throws DaoException {
        StudyMainSubjectDao dao = new StudyMainSubjectDao();
        List<StudyMainSubject> list = dao.listar();
        return list;
    }
    
    private List<String> validate(StudyMainSubject studyMainSubject) {
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
            String studyMainSubjectId = request.getParameter("study_main_subject_id");
            String studyAreaId = request.getParameter("study_area_id");
            String description = request.getParameter("description");

            StudyMainSubject studyMainSubject = new StudyMainSubject();

            studyMainSubject.setStudyMainSubjectId(convertToInt(studyMainSubjectId, request));
            studyMainSubject.setStudyMainSubjectId(convertToInt(studyAreaId, request));
            studyMainSubject.setDescription(description);
            List<String> msgList = validate(studyMainSubject);
            if (msgList.isEmpty()) {
                StudyMainSubjectDao d = new StudyMainSubjectDao();
                if (StringUtil.isNull(studyMainSubjectId)) {
                    d.salvar(studyMainSubject);
                } else {
                    d.alterar(studyMainSubject);
                }
                msg.append(");studyMainSubject created with ID=");
                request.setAttribute("message", msg);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyMainSubject/list.jsp");
                dispatcher.forward(request, response);
            } else {
                for (String m : msgList) {
                    if (msg.length() > 0) {
                        msg.append("</br>");
                    }
                    msg.append(m);
                }
                request.setAttribute("message", msg.toString());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyMainSubject/edit.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyMainSubject/edit.jsp");
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
        return "StudyMainSubject controller servlet";
    }// </editor-fold>
}