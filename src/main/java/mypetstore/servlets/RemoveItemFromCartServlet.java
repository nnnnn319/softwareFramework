package mypetstore.servlets;

import mypetstore.domain.Cart;
import mypetstore.domain.Diary;
import mypetstore.domain.Item;
import mypetstore.service.CartService;
import mypetstore.service.DiaryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RemoveItemFromCartServlet extends HttpServlet {
    private static final String REMOVE_ITEM = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/error.jsp";
    private CartService cartService;
    private String workingItemId;
    private DiaryService diaryService = new DiaryService();
    Cart cart;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");
        HttpSession session = request.getSession();
        cart = (Cart) session.getAttribute("cart");
        Item item = cart.removeItemById(workingItemId);
        cartService = new CartService();
        cartService.RemoveCart(workingItemId);
        if(item == null){
            session.setAttribute("message","no goods");
            request.getRequestDispatcher(ERROR);
        }
        else {
            String username_diary = (String) session.getAttribute("username");
            if(username_diary!=null) {
                Date currentTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time_diary = formatter.format(currentTime);
                String operation_diary = "remove from cart";
                Diary diary = new Diary();
                diary.setUsername(username_diary);
                diary.setDate(time_diary);
                diary.setOperation(operation_diary);
                diaryService.insertDiary(diary);
            }
            request.getRequestDispatcher(REMOVE_ITEM).forward(request,response);
        }
    }
}
