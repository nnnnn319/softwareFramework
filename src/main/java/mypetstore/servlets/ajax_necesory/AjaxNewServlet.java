package mypetstore.servlets.ajax_necesory;

import mypetstore.domain.Account;
import mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxNewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String u = request.getParameter("username");
        System.out.println(u);
        AccountService accountService = new AccountService();
        Account account = accountService.getAccount(u);
        if(account != null){
            response.getWriter().print("ok");
        }
        else{
            response.getWriter().print("no");
        }

    }
}
