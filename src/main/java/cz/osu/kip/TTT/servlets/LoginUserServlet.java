package cz.osu.kip.TTT.servlets;

import cz.osu.kip.TTT.services.LoginUserService;
import cz.osu.kip.TTT.services.RegisterUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginUserServlet", urlPatterns = {"/web/loginUser"})
public class LoginUserServlet extends HttpServlet {
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nick_mail = request.getParameter("nick-mail");
        String pw = request.getParameter("pw");

        if (nick_mail == null) System.out.println("Nick did not pass."); //alert nick
        else if (pw == null) System.out.println("mail did not pass."); //alert nick
        else {
            LoginUserService lus = (LoginUserService) request.getSession().getAttribute("loginUserService");
            lus.logUserIn(nick_mail, pw);
            response.sendRedirect("index.jsp");
        }
    }
}
