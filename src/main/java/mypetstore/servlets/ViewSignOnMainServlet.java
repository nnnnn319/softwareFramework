package mypetstore.servlets;

import mypetstore.domain.Account;
import mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class ViewSignOnMainServlet extends HttpServlet {
    private AccountService accountService = new AccountService();
    private static final String VIEW_SIGN_ON_MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String SIGN_ON = "/WEB-INF/jsp/account/SignonForm.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        Cookie[] t = request.getCookies();
//        System.out.println("ttrrr"+t[0].getValue());
//        System.out.println("11"+request.getParameter("identify"));
//        System.out.println("22"+request.getParameter("code_user"));
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        System.out.println(username);
        Account account = accountService.getAccount(username,password);
        String code = request.getParameter("code_user");
        System.out.println(code);
        if(code.equals("0") || code.equals("2")){
            String message = "验证码不正确";
//            System.out.println(message);
            session.setAttribute("message",message);
            request.getRequestDispatcher(SIGN_ON).forward(request,response);
        }
        else if(account==null){
            String message = "用户名或密码不正确，请重新输入";
//            System.out.println(message);
            session.setAttribute("message",message);
            request.getRequestDispatcher(SIGN_ON).forward(request,response);
        }
        else{
            session.setAttribute("account",account);
            session.setAttribute("username",username);
            request.getRequestDispatcher(VIEW_SIGN_ON_MAIN).forward(request,response);
        }


    }
}
