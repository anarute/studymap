package hackathom.studymap.jsp.controller;

import hackathon.studymap.jdbc.model.User;
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
@WebServlet(name = "userServlet", urlPatterns = {"/s/user"})
public class UserController extends HttpServletHelper  {

    private List<String> validate(User user) {
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
            String userId = request.getParameter("user_id");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String birthday = request.getParameter("birthday");
            String posts = request.getParameter("posts");
            String gold = request.getParameter("gold");

            User user = null;

            user.setUserId(convertToInt(userId, request));
            user.setLogin(login);
            user.setPassword(password);
            user.setName(name);
            user.setBirthday(convertToDateOrNull(birthday, request));
            user.setPosts(convertToIntegerOrNull(posts, request));
            user.setGold(convertToDoubleOrNull(gold, request));
            List<String> msgList = validate(user);
            if (msgList.isEmpty()) {
                msg.append(");user created with ID=");
                request.setAttribute("message", msg);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/user/list.jsp");
                dispatcher.forward(request, response);
            } else {
                for(String m:msgList) {
                    if(msg.length()>0) {
                        msg.append("</br>");
                    }
                    msg.append(m);
                }
                request.setAttribute("message", msg.toString());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/user/edit.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/s/user/edit.jsp");
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
        return "User controller servlet";
    }// </editor-fold>
}