package hackathom.studymap.jsp.controller;

import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.util.StringUtil;
import br.com.jcomputacao.util.web.HttpServletHelper;
import hackathom.studymap.jdbc.dao.StudyGroupPostDao;
import hackathon.studymap.jdbc.model.StudyGroupPost;
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
@WebServlet(name = "study_group_postServlet", urlPatterns = {"/studyGroupPost/salvar"})
public class StudyGroupPostController extends HttpServletHelper  {
    
    private StudyGroupPost model;
    
    public StudyGroupPost getModel() {
        if(model==null) {
            model = new StudyGroupPost();
        }
        return model;
    }
        
    public boolean load(Integer studyAreaId) throws DaoException {
        StudyGroupPostDao d = new StudyGroupPostDao();
        model = d.buscar(studyAreaId);
        return model != null;
    }
    
    public List<StudyGroupPost> getList() throws DaoException {
        StudyGroupPostDao dao = new StudyGroupPostDao();
        List<StudyGroupPost> list = dao.listar();
        return list;
    }

    private List<String> validate(StudyGroupPost studyGroupPost) {
        List<String> list = new ArrayList<String>();
        return list;
    }    /**
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
            String studyGroupPostId = request.getParameter("study_group_post_id");
            String studyGroupId = request.getParameter("study_group_id");
            String userId = request.getParameter("user_id");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String posted = request.getParameter("posted");

            StudyGroupPost studyGroupPost = new StudyGroupPost();

            studyGroupPost.setStudyGroupPostId(convertToInt(studyGroupPostId, request));
            studyGroupPost.setStudyGroupId(convertToInt(studyGroupId, request));
            studyGroupPost.setStudyGroupPostId(convertToInt(userId, request));
            studyGroupPost.setTitle(title);
            studyGroupPost.setContent(content);
            studyGroupPost.setPosted(convertToDate(posted, request));
            List<String> msgList = validate(studyGroupPost);
            if (msgList.isEmpty()) {
                StudyGroupPostDao d = new StudyGroupPostDao();
                if (StringUtil.isNull(studyGroupId)) {
                    d.salvar(studyGroupPost);
                } else {
                    d.alterar(studyGroupPost);
                }
                msg.append(");studyGroupPost created with ID=");
                request.setAttribute("message", msg);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyGroupPost/list.jsp");
                dispatcher.forward(request, response);
            } else {
                for(String m:msgList) {
                    if(msg.length()>0) {
                        msg.append("</br>");
                    }
                    msg.append(m);
                }
                request.setAttribute("message", msg.toString());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyGroupPost/edit.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyGroupPost/edit.jsp");
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
        return "StudyGroupPost controller servlet";
    }// </editor-fold>
}