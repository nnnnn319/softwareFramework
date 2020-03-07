package mypetstore.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GotoRegisterServlet extends HttpServlet {
    private static final String GOTO_REGISTER = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> languagePre = new ArrayList<String>();
        List<String> favCategory = new ArrayList<String>();
        languagePre.add("english");
        languagePre.add("chinese");
        languagePre.add("japanese");
        favCategory.add("CATS");
        favCategory.add("FISH");
        favCategory.add("DOGS");
        favCategory.add("REPTILES");
        favCategory.add("BIRDS");
        HttpSession session = request.getSession();
        session.setAttribute("languagePre",languagePre);
        session.setAttribute("favCategory",favCategory);
        request.getRequestDispatcher(GOTO_REGISTER).forward(request,response);
    }
}
