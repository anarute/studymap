package hackathom.studymap.jsp.controller;

import hackathon.studymap.jdbc.model.StudyGroupMember;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import br.com.jcomputacao.util.web.HttpServletHelper;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Murilo
 */
@WebServlet(name = "study_group_memberServlet", urlPatterns = {"/s/study_group_member"})
public class StudyGroupMemberController extends HttpServletHelper {

    private List<String> validate(StudyGroupMember studyGroupMember) {
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
            String studyGroupMemeberId = request.getParameter("study_group_memeber_id");
            String studyGroupId = request.getParameter("study_group_id");
            String userId = request.getParameter("user_id");

            StudyGroupMember studyGroupMember = null;

            studyGroupMember.setStudyGroupMemeberId(convertToInt(studyGroupMemeberId, request));
            studyGroupMember.setStudyGroupId(convertToInt(studyGroupId, request));
            studyGroupMember.setUserId(convertToInt(userId, request));
            List<String> msgList = validate(studyGroupMember);
            if (msgList.isEmpty()) {
                msg.append(");studyGroupMember created with ID=");
                request.setAttribute("message", msg);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyGroupMember/list.jsp");
                dispatcher.forward(request, response);
            } else {
                for (String m : msgList) {
                    if (msg.length() > 0) {
                        msg.append("</br>");
                    }
                    msg.append(m);
                }
                request.setAttribute("message", msg.toString());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/studyGroupMember/edit.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ParseException ex) {
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
        return "StudyGroupMember controller servlet";
    }// </editor-fold>
}