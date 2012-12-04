package hackathom.studymap.jsp.controller;

import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.util.StringUtil;
import br.com.jcomputacao.util.web.HttpServletHelper;
import hackathom.studymap.jdbc.dao.StudySubjectDao;
import hackathon.studymap.jdbc.model.StudySubject;
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
@WebServlet(name = "study_subjectServlet", urlPatterns = {"/studySubjectController/salvar"})
public class StudySubjectController extends HttpServletHelper {
    
    private StudySubject model;
    
    public StudySubject getModel() {
        if(model==null) {
            model = new StudySubject();
        }
        return model;
    }
        
    public boolean load(Integer studyAreaId) throws DaoException {
        StudySubjectDao d = new StudySubjectDao();
        model = d.buscar(studyAreaId);
        return model != null;
    }
    
    public List<StudySubject> getList() throws DaoException {
        StudySubjectDao dao = new StudySubjectDao();
        List<StudySubject> list = dao.listar();
        return list;
    }

    private List<String> validate(StudySubject studySubject) {
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
            String studySubjectId = request.getParameter("study_subject_id");
            String studyMainSubjectId = request.getParameter("study_main_subject_id");
            String description = request.getParameter("description");

            StudySubject studySubject = new StudySubject();

            studySubject.setStudySubjectId(convertToInt(studySubjectId, request));
            studySubject.setStudyMainSubjectId(convertToInt(studyMainSubjectId, request));
            studySubject.setDescription(description);
            List<String> msgList = validate(studySubject);
            if (msgList.isEmpty()) {
                StudySubjectDao d = new StudySubjectDao();
                if (StringUtil.isNull(studySubjectId)) {
                    d.salvar(studySubject);
                } else {
                    d.alterar(studySubject);
                }
                msg.append(");studySubject created with ID=");
                request.setAttribute("message", msg);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studySubject/list.jsp");
                dispatcher.forward(request, response);
            } else {
                for (String m : msgList) {
                    if (msg.length() > 0) {
                        msg.append("</br>");
                    }
                    msg.append(m);
                }
                request.setAttribute("message", msg.toString());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studySubject/edit.jsp");
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
        return "StudySubject controller servlet";
    }// </editor-fold>
}