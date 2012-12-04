package hackathom.studymap.jsp.controller;

import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.util.StringUtil;
import br.com.jcomputacao.util.web.HttpServletHelper;
import hackathom.studymap.jdbc.dao.StudyAreaDao;
import hackathon.studymap.jdbc.model.StudyArea;
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
@WebServlet(name = "study_areaServlet", urlPatterns = {"/studyArea/salvar"})
public class StudyAreaController extends HttpServletHelper {
    
    private StudyArea model;
    
    public StudyArea getModel() {
        if(model==null) {
            model = new StudyArea();
        }
        return model;
    }
    
    public boolean load(Integer studyAreaId) throws DaoException {
        StudyAreaDao d = new StudyAreaDao();
        model = d.buscar(studyAreaId);
        return model != null;
    }

    public List<StudyArea> getList() throws DaoException {
        StudyAreaDao d = new StudyAreaDao();
        List<StudyArea> rs = d.listar();
        return rs;
    }

    private List<String> validate(StudyArea studyArea) {
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
            String studyAreaId = request.getParameter("study_area_id");
            String description = request.getParameter("description");

            StudyArea studyArea = new StudyArea();

            studyArea.setStudyAreaId(convertToInt(studyAreaId, request));
            studyArea.setDescription(description);
            List<String> msgList = validate(studyArea);
            if (msgList.isEmpty()) {
                StudyAreaDao d = new StudyAreaDao();
                if (StringUtil.isNotNull(studyAreaId)) {
                    d.alterar(studyArea);
                } else {
                    d.salvar(studyArea);
                }
                msg.append(");studyArea created with ID=");
                request.setAttribute("message", msg);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/studyArea/list.jsp");
                dispatcher.forward(request, response);
            } else {
                for (String m : msgList) {
                    if (msg.length() > 0) {
                        msg.append("</br>");
                    }
                    msg.append(m);
                }
                request.setAttribute("message", msg.toString());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/studyArea/edit.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/studyArea/edit.jsp");
            dispatcher.forward(request, response);
        } catch (DaoException ex) {
            ex.printStackTrace(System.err);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/studyArea/edit.jsp");
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
        return "StudyArea controller servlet";
    }// </editor-fold>
}