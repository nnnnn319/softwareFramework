package mypetstore.servlets;

import mypetstore.domain.Account;
import mypetstore.domain.Diary;
import mypetstore.service.AccountService;
import mypetstore.service.CategoryService;
import mypetstore.service.DiaryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterOkServlet extends HttpServlet {
    private static final String REGISTER_TO_LOGIN = "/WEB-INF/jsp/account/SignonForm.jsp";
    AccountService accountService = new AccountService();
    DiaryService diaryService = new DiaryService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = new Account();
        account.setUsername(request.getParameter("username"));
        account.setPassword(request.getParameter("password"));
        account.setFirstName(request.getParameter("account.firstName"));
        account.setLastName(request.getParameter("account.lastName"));
        account.setEmail(request.getParameter("account.email"));
        account.setPhone(request.getParameter("account.phone"));
        account.setAddress1(request.getParameter("account.address1"));
        account.setAddress2(request.getParameter("account.address2"));
        account.setCity(request.getParameter("account.city"));
        account.setState(request.getParameter("account.state"));
        account.setZip(request.getParameter("account.zip"));
        account.setCountry(request.getParameter("account.country"));
        System.out.println(request.getParameter("account.country"));
        account.setLanguagePreference(request.getParameter("account.languagePreference"));
        account.setFavouriteCategoryId(request.getParameter("account.favouriteCategoryId"));
        if(request.getParameter("account.listOption") != null){
            System.out.println(request.getParameter("account.listOption"));
            account.setListOption(true);
        }
        else {
            account.setListOption(false);
        }
        if(request.getParameter("account.bannerOption") != null){
            System.out.println(request.getParameter("account.bannerOption"));
            account.setBannerOption(true);
        }
        else {
            account.setBannerOption(false);
        }
        accountService.insertAccount(account);
        HttpSession session = request.getSession();
        String username_diary = (String) session.getAttribute("username");
        if(username_diary!=null) {
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time_diary = formatter.format(currentTime);
            String operation_diary = "Register";
            Diary diary = new Diary();
            diary.setUsername(username_diary);
            diary.setDate(time_diary);
            diary.setOperation(operation_diary);
            diaryService.insertDiary(diary);
        }
        request.getRequestDispatcher(REGISTER_TO_LOGIN).forward(request,response);
    }
}
